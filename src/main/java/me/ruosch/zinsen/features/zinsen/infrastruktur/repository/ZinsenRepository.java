package me.ruosch.zinsen.features.zinsen.infrastruktur.repository;

import me.ruosch.zinsen.features.zinsen.domain.Zins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZinsenRepository extends JpaRepository<Zins, Long> {

}
