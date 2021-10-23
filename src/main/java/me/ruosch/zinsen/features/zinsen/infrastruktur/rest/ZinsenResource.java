package me.ruosch.zinsen.features.zinsen.infrastruktur.rest;

import lombok.AllArgsConstructor;
import me.ruosch.zinsen.features.zinsen.domain.Zinsen;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZinsenResource {


    @GetMapping(value = { "/zinsen"})
    public ResponseEntity<List<Zinsen>> getAllZinsen() {
        return ResponseEntity.ok(null);
    }
}
