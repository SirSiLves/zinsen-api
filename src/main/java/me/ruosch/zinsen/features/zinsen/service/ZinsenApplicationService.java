package me.ruosch.zinsen.features.zinsen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ruosch.zinsen.features.zinsen.domain.Produkt;
import me.ruosch.zinsen.features.zinsen.domain.Zins;
import me.ruosch.zinsen.features.zinsen.infrastruktur.repository.CalculateRepository;
import me.ruosch.zinsen.features.zinsen.infrastruktur.repository.ZinsenRepository;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsCreate;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ZinsenApplicationService {

    private ZinsenRepository zinsenRepository;
    private CalculateRepository calculateRepository;

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

        return zinsQuery;
    }
}
