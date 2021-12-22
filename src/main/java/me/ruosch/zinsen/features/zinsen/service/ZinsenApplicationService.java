package me.ruosch.zinsen.features.zinsen.service;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ruosch.zinsen.features.zinsen.domain.Produkt;
import me.ruosch.zinsen.features.zinsen.domain.Zins;
import me.ruosch.zinsen.features.zinsen.infrastruktur.repository.CalculateRepository;
import me.ruosch.zinsen.features.zinsen.infrastruktur.repository.ZinsenRepository;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsCreate;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ZinsenApplicationService {

    private ZinsenRepository zinsenRepository;
    private CalculateRepository calculateRepository;
    private HikariDataSource dataSource;

    public List<ZinsQuery> listAll() {
        List<Zins> zinsList = this.zinsenRepository.findAll();
        return zinsList.stream().map(this::mapZinsToZinsQuery).collect(Collectors.toList());
    }

    public void create(ZinsCreate zinsCreate) {
        Zins zins = null;

        if (zinsCreate.getProdukt().toUpperCase().equals(Produkt.SARON.toString())) {
            zins = Zins.makeSaron();
        }

        if (zinsCreate.getProdukt().toUpperCase().equals(Produkt.FEST.toString())) {
            zins = Zins.makeFest(zinsCreate.getLaufzeit());
        }

        if (zins != null) {
            zinsenRepository.save(zins);
        }
    }

    public ZinsQuery calculate(long id) {
        Optional<Zins> zinsById = zinsenRepository.findById(id);
        if (zinsById.isPresent()) {
            Zins z = zinsById.get();

            calculateRepository.calculate(z);

            zinsenRepository.save(z);

            return this.mapZinsToZinsQuery(z);
        }

        return null;
    }

    private ZinsQuery mapZinsToZinsQuery(Zins zins) {
        ZinsQuery zinsQuery = new ZinsQuery();
        zinsQuery.setId(zins.getOid());
        zinsQuery.setZins(zins.getKurs());
        zinsQuery.setLaufzeit(zins.getLaufzeit());
        zinsQuery.setProdukt(zins.getProdukt().toString());
        zinsQuery.setZuletztBerechnet(zins.getZuletztBerechnet());

        return zinsQuery;
    }

    public void delete(long id) {
        Optional<Zins> byId = zinsenRepository.findById(id);
        byId.ifPresent(zins -> this.zinsenRepository.delete(zins));
    }

    public Integer simulate(long durchlaeufe) {
        ArrayList<Thread> threadList = new ArrayList<>();
        AtomicInteger successCount = new AtomicInteger();

        // create threads
        for (int j = 0; j < durchlaeufe; j++) {
            threadList.add(new Thread(() -> {
                try {
                    List<Zins> all = zinsenRepository.getDataWithLoad();
                    for (Zins zins : all) {
                        Optional<Zins> byId = zinsenRepository.findById(zins.getOid());
                        if (byId.isPresent()) {
                            Zins dbZins = byId.get();
                        }
                    }
                    successCount.getAndIncrement();
                } catch (Exception e) {
                    // error occurred..
                }
            }));
        }

        // start all threads
        for (Thread t : threadList) {
            t.start();
        }

        // wait until all are finished
        threadList.forEach(t -> {
            try {
                t.join();
            } catch (Exception e) {
                // error occurred..
            }
        });

        return successCount.get();
    }

    public List<ZinsQuery> listDataWithLoad() {
        return this.zinsenRepository.getDataWithLoad().stream().map(this::mapZinsToZinsQuery).collect(Collectors.toList());
    }
}
