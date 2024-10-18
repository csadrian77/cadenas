
package sis.modelo;

import java.io.Serializable;

public class Ventab  implements Serializable {
  private String idr_bloque;
  private String nboleto;
  private int intidr_bloque;
  private int intnboleto;
  private String boleto;
  private String hashold;
  private String rutaold;
  private String cedula;
  private String nombre;
  private String telefono;
  private String direccion;
  private String email;
  private String hashnew;
  private String rutanew;   
  private String usuario;
  private String fcompra;

    public int getIntidr_bloque() {
        return intidr_bloque;
    }

    public void setIntidr_bloque(int intidr_bloque) {
        this.intidr_bloque = intidr_bloque;
    }

    public int getIntnboleto() {
        return intnboleto;
    }

    public void setIntnboleto(int intnboleto) {
        this.intnboleto = intnboleto;
    }

  
    public String getFcompra() {
        return fcompra;
    }

    public void setFcompra(String fcompra) {
        this.fcompra = fcompra;
    }

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

    public String getHashold() {
        return hashold;
    }

    public void setHashold(String hashold) {
        this.hashold = hashold;
    }

    public String getRutaold() {
        return rutaold;
    }

    public void setRutaold(String rutaold) {
        this.rutaold = rutaold;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashnew() {
        return hashnew;
    }

    public void setHashnew(String hashnew) {
        this.hashnew = hashnew;
    }

    public String getRutanew() {
        return rutanew;
    }

    public void setRutanew(String rutanew) {
        this.rutanew = rutanew;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
  
}
