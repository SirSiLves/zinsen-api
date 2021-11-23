package me.ruosch.zinsen.features.zinsen.infrastruktur.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsCreate;
import me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto.ZinsQuery;
import me.ruosch.zinsen.features.zinsen.service.ZinsenApplicationService;
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

    private ZinsenApplicationService zinsenApplicationService;

    @GetMapping
    public ResponseEntity<List<ZinsQuery>> getAllZinsen() {
        List<ZinsQuery> zinsList = zinsenApplicationService.listAll();
        return ResponseEntity.ok(zinsList);
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<ZinsQuery> calculateZins(@PathVariable long id) {
        log.info("Zins mit der ID {} wird berechnet ", id);
        ZinsQuery zinsQuery = zinsenApplicationService.calculate(id);
        return ResponseEntity.ok(zinsQuery);
    }

    @PostMapping
    public ResponseEntity<String> createZins(ZinsCreate zinsCreate) {
        log.info("create zinsen {} ", zinsCreate.toString());
        zinsenApplicationService.create(zinsCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<String> deleteZins(@PathVariable long id) {
        log.info("Zins {} wird gelöscht ", id);
        zinsenApplicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = {"/simulation/{durchlaeufe}"})
    public ResponseEntity<Integer> starteSimulation(@PathVariable long durchlaeufe) {
        log.info("Starte Zinsen abfrage mit {} durchläufe", durchlaeufe);
        int count = zinsenApplicationService.simulate(durchlaeufe);
        return ResponseEntity.ok(count);
    }
}

