package me.ruosch.zinsen.features.zinsen.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.ruosch.zinsen.shared.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class Zins extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Produkt produkt;

    private float kurs;

    @Enumerated(EnumType.STRING)
    private Laufzeit laufzeit;

    public static Zins makeSaron() {
        Zins zins = new Zins();
        zins.produkt = Produkt.SARON;
        zins.laufzeit = Laufzeit.INDEFINITE;

        return zins;
    }

    public static Zins makeFest(Laufzeit laufzeit) {
        Zins zins = new Zins();
        zins.produkt = Produkt.FEST;
        zins.laufzeit = laufzeit;

        return zins;
    }

    public static Zins makeFest(int laufzeit) {
        Zins zins = new Zins();
        zins.produkt = Produkt.FEST;

        switch(laufzeit){
            case 1:
                zins.laufzeit = Laufzeit.ONE;
                break;
            case 2:
                zins.laufzeit = Laufzeit.TWO;
                break;
            case 3:
                zins.laufzeit = Laufzeit.THREE;
                break;
            case 4:
                zins.laufzeit = Laufzeit.FOUR;
                break;
            case 5:
                zins.laufzeit = Laufzeit.FIVE;
                break;
            case 6:
                zins.laufzeit = Laufzeit.SIX;
                break;
            case 7:
                zins.laufzeit = Laufzeit.SEVEN;
                break;
            case 8:
                zins.laufzeit = Laufzeit.EIGHT;
                break;
            case 9:
                zins.laufzeit = Laufzeit.NINE;
                break;
            case 10:
                zins.laufzeit = Laufzeit.TEN;
                break;
            default:
                break;
        }

        return zins;
    }

    public void changeKurs(float kurs) {
        this.kurs = kurs;
    }

    public Laufzeit getTypeLaufzeit() {
        return laufzeit;
    }

    public int getLaufzeit() {
        switch(this.laufzeit){
            case ONE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            default:
                return 0;
        }
    }

}
