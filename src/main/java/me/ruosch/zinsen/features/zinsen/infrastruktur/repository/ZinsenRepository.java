package me.ruosch.zinsen.features.zinsen.infrastruktur.repository;

import me.ruosch.zinsen.features.zinsen.domain.Zins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZinsenRepository extends JpaRepository<Zins, Long> {

    @Query(nativeQuery = true, value = "SELECT z1.oid, z1.entity_version, z1.kurs, z1.laufzeit, z1.produkt, z1.zuletzt_berechnet FROM zins as z1\n" +
            "FULL JOIN zins as z2 ON z1.oid = z2.oid \n" +
            "FULL JOIN zins as z3 ON z2.oid = z3.oid\n" +
            "FULL JOIN zins as z4 ON z3.oid = z4.oid\n" +
            "FULL JOIN zins as z5 ON z4.oid = z5.oid\n" +
            "FULL JOIN zins as z6 ON z5.oid = z6.oid\n" +
            "FULL JOIN zins as z7 ON z6.oid = z7.oid\n" +
            "FULL JOIN zins as z8 ON z7.oid = z8.oid\n" +
            "FULL JOIN zins as z9 ON z8.oid = z9.oid\n" +
            "FULL JOIN zins as z10 ON z9.oid = z10.oid")
    List<Zins> getDataWithLoad();


}
