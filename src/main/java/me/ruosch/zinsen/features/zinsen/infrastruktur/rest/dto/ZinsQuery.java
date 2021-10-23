package me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZinsQuery {

    private String produkt;
    private int laufzeit;
    private float zins;

}
