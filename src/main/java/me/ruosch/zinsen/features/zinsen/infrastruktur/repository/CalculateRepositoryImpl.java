package me.ruosch.zinsen.features.zinsen.infrastruktur.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ruosch.zinsen.features.zinsen.domain.Zins;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.CalculateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class CalculateRepositoryImpl implements CalculateRepository {

    @Value("${me.ruosch.calculate-url}")
    private String calculateUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void calculate(Zins zins) {

        String queryParam = "?period=\"" + zins.getTypeLaufzeit() + "\"&product=\"" + zins.getProdukt() + "\"";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(calculateUrl + queryParam, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            CalculateQuery calculateQuery = objectMapper.readValue(response.getBody(), CalculateQuery.class);

            zins.changeKurs((float) calculateQuery.getResult());

        } catch (Exception e) {
            log.error("Verbindung zur Calculation Function konnte nicht hergestellt werden. " + e.getMessage());
        }

    }
}
