package me.ruosch.zinsen.features.zinsen.infrastruktur.repository;

import me.ruosch.zinsen.features.zinsen.domain.Zins;

public interface CalculateRepository {
    void calculate(Zins zins);
}
