package me.ruosch.zinsen.features.zinsen.infrastruktur.rest;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.TelemetryContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsCreate;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsQuery;
import me.ruosch.zinsen.features.zinsen.service.ZinsenApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/zinsen", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ZinsenResource {

    private TelemetryClient telemetryClient;
    private ZinsenApplicationService zinsenApplicationService;

    @GetMapping
    public ResponseEntity<List<ZinsQuery>> getAllZinsen() {
        telemetryClient.trackEvent("Zinsen werden abgefragt");
        List<ZinsQuery> zinsList = zinsenApplicationService.listAll();
        return ResponseEntity.ok(zinsList);
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<ZinsQuery> calculateZins(@PathVariable long id) {
        String logValue = "Zins mit der ID " + id + " wird berechnet ";
        telemetryClient.trackEvent(logValue);
        log.info(logValue);
        ZinsQuery zinsQuery = zinsenApplicationService.calculate(id);
        return ResponseEntity.ok(zinsQuery);
    }

    @PostMapping
    public ResponseEntity<String> createZins(ZinsCreate zinsCreate) {
        String logValue = "create zinsen " + zinsCreate.toString() + "";
        telemetryClient.trackEvent(logValue);
        log.info(logValue);
        zinsenApplicationService.create(zinsCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<String> deleteZins(@PathVariable long id) {
        String logValue = "Zins " + id + " wird gelöscht";
        telemetryClient.trackEvent(logValue);
        log.info(logValue);
        zinsenApplicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = {"/simulation/{durchlaeufe}"})
    public ResponseEntity<Integer> starteSimulation(@PathVariable long durchlaeufe) {
        String logValue = "Starte Zinsen abfrage mit " + durchlaeufe + " durchläufe";
        telemetryClient.trackEvent(logValue);
        log.info(logValue);
        int count = zinsenApplicationService.simulate(durchlaeufe);
        return ResponseEntity.ok(count);
    }
}

