package me.ruosch.zinsen.features.zinsen.infrastruktur.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class ZinsenResource {

    private ZinsenApplicationService zinsenApplicationService;

    @GetMapping
    public ResponseEntity<List<ZinsQuery>> getAllZinsen() {
        List<ZinsQuery> zinsList = zinsenApplicationService.listAll();
        return ResponseEntity.ok(zinsList);
    }

    @PostMapping    public ResponseEntity<String> createZins(ZinsQuery zinsQuery) {
        log.info("create zinsen {} ", zinsQuery.toString());        zinsenApplicationService.create(zinsQuery);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

