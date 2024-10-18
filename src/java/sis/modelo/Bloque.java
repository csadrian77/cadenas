
package sis.modelo;

import java.io.Serializable;


public class Bloque implements Serializable {
    private String idb;
    private String bloque;

    public String getIdb() {
        return idb;
    }

    public void setIdb(String idb) {
        this.idb = idb;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

}
