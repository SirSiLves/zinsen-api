package me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZinsQuery {

    private Long id;
    private String produkt;
    private int laufzeit;
    private float zins;
    private LocalDateTime zuletztBerechnet;

}
