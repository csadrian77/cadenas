
package sis.modelo;

import java.io.Serializable;

public class Boletos  implements Serializable {
        
  private String cod_boleto;
  private int numero_boleto;
  private String titulo;
  private String premios;
  private String frifa;
  private String hora_rifa;
  private String valor;
  private String lugar;
  private String notas;
  private String usuario;
  

    public String getCod_boleto() {
        return cod_boleto;
    }

    public void setCod_boleto(String cod_boleto) {
        this.cod_boleto = cod_boleto;
    }

    public int getNumero_boleto() {
        return numero_boleto;
    }

    public void setNumero_boleto(int numero_boleto) {
        this.numero_boleto = numero_boleto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPremios() {
        return premios;
    }

    public void setPremios(String premios) {
        this.premios = premios;
    }

    public String getFrifa() {
        return frifa;
    }

    public void setFrifa(String frifa) {
        this.frifa = frifa;
    }

    public String getHora_rifa() {
        return hora_rifa;
    }

    public void setHora_rifa(String hora_rifa) {
        this.hora_rifa = hora_rifa;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}



/**
 * bloque
 * fechacrecion
 * nonce
 * hash_anterior
 * hash_actual
 * 
 * idsorteo
 * estado
 * usr
 * 
 * datos
 *    datos: (numero_boleto, validacion, ruta, premios, fecha_rifa,hora_rifa, lugar,valor)
 *    comprador (nombre,telefono,direccion)(encriptado)
 *    valor       
 *    premio:1-2-3-4)
 * 
*/
