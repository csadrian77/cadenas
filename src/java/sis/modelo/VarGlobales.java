package sis.modelo;


import java.io.Serializable;

public class VarGlobales implements Serializable {
  private String usuario;
  private String idUsuario;
  private String idCentro;
  private String idNombreCentro;
  private String ruta;
  private String cantRegistros;
  

  public String getCantRegistros() {
    return this.cantRegistros;
  }
  
  public void setCantRegistros(String cantRegistros) {
    this.cantRegistros = cantRegistros;
  }
  
  public String getIdCentro() {
    return this.idCentro;
  }
  
  public void setIdCentro(String idCentro) {
    this.idCentro = idCentro;
  }
  
  public String getIdNombreCentro() {
    return this.idNombreCentro;
  }
  
  public void setIdNombreCentro(String idNombreCentro) {
    this.idNombreCentro = idNombreCentro;
  }
  
  public String getIdUsuario() {
    return this.idUsuario;
  }
  
  public void setIdUsuario(String idUsuario) {
    this.idUsuario = idUsuario;
  }
  
  public String getRuta() {
    return this.ruta;
  }
  
  public void setRuta(String ruta) {
    this.ruta = ruta;
  }
  
  public String getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }
}