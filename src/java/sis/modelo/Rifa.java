package sis.modelo;

import java.io.Serializable;

public class Rifa implements Serializable {
  private String idr_bloque; 
  private String nboleto;  
  private String boleto;
  private String fecha;           
  private String premio; 
       

    public String getIdr_bloque() {
        return idr_bloque;
    }

    public void setIdr_bloque(String idr_bloque) {
        this.idr_bloque = idr_bloque;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

          
          
}
