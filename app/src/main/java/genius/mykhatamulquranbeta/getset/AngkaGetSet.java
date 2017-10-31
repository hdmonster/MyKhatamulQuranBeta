package genius.mykhatamulquranbeta.getset;

import java.io.Serializable;

/**
 * Created by ALFI_PC on 29/10/2017.
 */

public class AngkaGetSet implements Serializable {
    public AngkaGetSet(int angka, String huruf) {
        this.angka = angka;
        this.huruf = huruf;
    }

    public int getAngka() {
        return angka;
    }

    public void setAngka(int angka) {
        this.angka = angka;
    }

    private int angka;
    private String huruf;

    public String getHuruf() {
        return huruf;
    }

    public void setHuruf(String huruf) {
        this.huruf = huruf;
    }
}
