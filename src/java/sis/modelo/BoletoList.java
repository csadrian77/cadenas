package sis.modelo;

import java.io.Serializable;

public class BoletoList implements Serializable {
    private String nboleto;
    private String boleto;        
    private String hashb;
    private String rutab  ;
    private String idbloque  ;

    public String getIdbloque() {
        return idbloque;
    }

    public void setIdbloque(String idbloque) {
        this.idbloque = idbloque;
    }

    public String getNboleto() {
        return nboleto;
    }

    public void setNboleto(String nboleto) {
        this.nboleto = nboleto;
    }

    public String getBoleto() {
        return boleto;
    }

    public void setBoleto(String boleto) {
        this.boleto = boleto;
    }

    public String getHashb() {
        return hashb;
    }

    public void setHashb(String hashb) {
        this.hashb = hashb;
    }

    public String getRutab() {
        return rutab;
    }

    public void setRutab(String rutab) {
        this.rutab = rutab;
    }
    
}
