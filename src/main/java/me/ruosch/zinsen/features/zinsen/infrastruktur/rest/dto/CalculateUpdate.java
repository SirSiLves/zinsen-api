package me.ruosch.zinsen.features.zinsen.infrastruktur.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.ruosch.zinsen.features.zinsen.domain.Laufzeit;
import me.ruosch.zinsen.features.zinsen.domain.Produkt;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateUpdate {
    private Produkt produkt;
    private Laufzeit laufzeit;
}
