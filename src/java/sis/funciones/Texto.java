package sis.funciones;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Texto {
  public static int cadenaAentero(String cadena) throws IOException {
    int numInt = 0;
    try {
      numInt = Integer.valueOf(cadena).intValue();
    } catch (NumberFormatException e) {
      System.out.println("Clase Texto-cadenaAentero: Error en conversion de datos");
    } 
    return numInt;
  }
  
  public int cadenaAentero1(String cadena) throws IOException {
    int numInt = 0;
    try {
      numInt = Integer.valueOf(cadena).intValue();
    } catch (NumberFormatException e) {
      System.out.println("Clase Texto-cadenaAentero: Error en conversion de datos");
      e.printStackTrace();
    } 
    return numInt;
  }
  
  public float cadenaAfloat(String cadena) throws IOException {
    float numInt = 0.0F;
    try {
      numInt = Float.valueOf(cadena).floatValue();
    } catch (NumberFormatException e) {
      System.out.print("");
    } 
    return numInt;
  }
  
  public Date cadenaAdate(String fecha) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date today = null;
    try {
      today = df.parse(fecha);
    } catch (ParseException e) {
      System.out.println("Clase Texto-cadenaAdate: Error en conversion de datos");
    } 
    return today;
  }
  
  public static String enteroAcadena(int cadena) throws IOException {
    String aString = "";
    try {
      aString = Integer.toString(cadena);
    } catch (NumberFormatException e) {
      System.out.println("Clase Texto-enteroAcadena: Error en conversion de datos");
      e.printStackTrace();
    } 
    return aString;
  }
  
  public static String doubleAcadena(int cadena) throws IOException {
    String aString = "";
    try {
      aString = Integer.toString(cadena);
    } catch (NumberFormatException e) {
      System.out.println("Clase Texto-enteroAcadena: Error en conversion de datos");
      e.printStackTrace();
    } 
    return aString;
  }
  
  public String dateAcadena(Date fecha) {
    String cadenaFecha = "";
    try {
      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      cadenaFecha = formato.format(fecha);
    } catch (Exception e) {
      System.out.println("Clase Texto-dateAcadena: Error en conversion de datos");
      e.printStackTrace();
    } 
    return cadenaFecha;
  }
  
  public String hex(byte[] array) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < array.length; i++)
      sb.append(Integer.toHexString(array[i] & 0xFF | 0x100).substring(1, 3)); 
    return sb.toString();
  }
  
  public String md5(String message) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      return hex(md.digest(message.getBytes("CP1252")));
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
    
    } catch (UnsupportedEncodingException unsupportedEncodingException) {}
    return null;
  }
}