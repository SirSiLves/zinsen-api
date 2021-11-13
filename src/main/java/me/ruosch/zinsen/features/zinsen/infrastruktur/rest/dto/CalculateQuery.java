package me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto;

import lombok.*;
import me.ruosch.zinsen.features.zinsen.domain.Laufzeit;
import me.ruosch.zinsen.features.zinsen.domain.Produkt;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculateQuery {
    Produkt produkt;
    Laufzeit laufzeit;
    double result;
    int iterations;
}
