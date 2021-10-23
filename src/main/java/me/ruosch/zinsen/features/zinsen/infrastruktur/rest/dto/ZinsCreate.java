package me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZinsCreate {

    private String produkt;
    private int laufzeit;

}
