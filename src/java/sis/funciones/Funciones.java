package sis.funciones;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Funciones {
  public String getFecha() {
    Date fechaActual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    String cadenaFecha = formato.format(fechaActual);
    return cadenaFecha;
  }
  
  public String getAnio() {
    Texto conv = new Texto();
    Calendar ahoraCal = Calendar.getInstance();
    String anio = "2004";
    try {
      anio = Texto.enteroAcadena(ahoraCal.get(1));
    } catch (IOException ex) {
      Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return anio;
  }
  
  public String getMes() {
    Texto conv = new Texto();
    String mess = "";
    Date fecha = new Date();
    int mes = 0;
    mes = fecha.getMonth();
    try {
      mes++;
      mess = Texto.enteroAcadena(mes);
    } catch (IOException ex) {
      Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return mess;
  }
  
  public String getHora() {
    Date fechaActual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("HHmm");
    String cadenaFecha = formato.format(fechaActual);
    return cadenaFecha;
  }
  
  public String getHorass() {
    Date fechaActual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("HHmmss");
    String cadenaFecha = formato.format(fechaActual);
    return cadenaFecha;
  }
  
  public int redondear(float flotante) {
    int intfinal = 0;
    intfinal = Math.round(flotante);
    return intfinal;
  }
  
  public int redondearMax(float flotante) {
    int intfinal = 0;
    intfinal = (int)Math.ceil(flotante);
    return intfinal;
  }
  
  private static boolean isNumeric(String cadena) {
    try {
      Integer.parseInt(cadena);
      return true;
    } catch (NumberFormatException numberFormatException) {
      return false;
    } 
  }
  
  public String validarId(String Idcodigo, String tipo) {
    String resulado = "";
    boolean esnumero = isNumeric(Idcodigo);
    if (esnumero == true) {
      if (Idcodigo.length() == 10 && "C".equals(tipo) == true) {
        resulado = "1";
      } else {
        resulado = "0";
      } 
      if ("P".equals(tipo) == true)
        resulado = "1"; 
    } else {
      resulado = "0";
    } 
    return resulado;
  }
  
  public String subcadena(String cadena, int corte160) {
    String resultado = "";
    int valor = 0;
    String temp = "";
    if (cadena == null) {
      resultado = "1";
      valor = 0;
    } else {
      temp = cadena;
      valor = cadena.length();
    } 
    if (valor >= corte160) {
      resultado = temp.substring(0, corte160) + "...";
    } else if (temp.length() < corte160 && temp.length() > 1) {
      resultado = temp.substring(0, temp.length());
    } 
    if (temp.length() < 1)
      resultado = ""; 
    resultado = resultado.replaceAll("\r\n", "");
    return resultado;
  }
  
  public String pintaColor(String word, String worddb) {
    int j = 0;
    String linea = worddb.trim();
    String[] campos = linea.split("\\s+");
    while (j < campos.length) {
      word = buscacolor(word, campos[j]);
      j++;
    } 
    return word;
  }
  
  public String buscacolor(String word, String keyword) {
    Pattern p = Pattern.compile(".*(" + keyword + ").*", 2);
    Matcher m = p.matcher(word);
    if (m.matches()) {
      String replaceWord = m.group(1);
      word = word.replaceAll(replaceWord, "<span <span style=\"color:#6A6A00\">" + replaceWord + "</span>");
    } 
    return word;
  }
  
  public void ordena(List<?> lista, final String propiedad) {
    Collections.sort(lista, new Comparator() {
          public int compare(Object obj1, Object obj2) {
            Class<?> clase = obj1.getClass();
            String getter = "get" + Character.toUpperCase(propiedad.charAt(0)) + propiedad.substring(1);
            try {
              Method getPropiedad = clase.getMethod(getter, new Class[0]);
              Object propiedad1 = getPropiedad.invoke(obj1, new Object[0]);
              Object propiedad2 = getPropiedad.invoke(obj2, new Object[0]);
              if (propiedad1 instanceof Comparable && propiedad2 instanceof Comparable) {
                Comparable<Comparable> prop1 = (Comparable)propiedad1;
                Comparable prop2 = (Comparable)propiedad2;
                return prop1.compareTo(prop2);
              } 
              if (propiedad1.equals(propiedad2))
                return 0; 
              return 1;
            } catch (Exception e) {
              e.printStackTrace();
              return 0;
            } 
          }
        });
  }
}