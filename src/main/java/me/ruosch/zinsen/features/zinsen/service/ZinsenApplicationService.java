package me.ruosch.zinsen.features.zinsen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ruosch.zinsen.features.zinsen.domain.Produkt;
import me.ruosch.zinsen.features.zinsen.domain.Zins;
import me.ruosch.zinsen.features.zinsen.infrastruktur.repository.ZinsenReporitory;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsCreate;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsQuery;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class ZinsenApplicationService {

    private ZinsenReporitory zinsenReporitory;

    public List<ZinsQuery> listAll() {
        List<Zins> zinsList = this.zinsenReporitory.findAll();

        return zinsList.stream().map(z -> {
            ZinsQuery zinsQuery = new ZinsQuery();
            zinsQuery.setId(z.getOid());
            zinsQuery.setZins(z.getKurs());
            zinsQuery.setLaufzeit(z.getLaufzeit());
            zinsQuery.setProdukt(z.getProdukt().toString());

            return zinsQuery;
        }).collect(Collectors.toList());
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
            zinsenReporitory.save(zins);
        }
    }
}
