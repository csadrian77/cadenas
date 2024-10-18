package sis.funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Jdbc_sqls {
  private Connection con = null;
  
  public Jdbc_sqls() {
    try {
      Context initContext = new InitialContext();
      DataSource dataSource = (DataSource)initContext.lookup("java:comp/env/jdbc/DBleo");
      this.con = dataSource.getConnection();
    } catch (NamingException ne) {
      System.err.println( ne.getMessage() );
      System.out.println("Debido a un problema con naming no se puede mostar los datos en este momento");
      System.out.println("Mensaje de error ==> " + ne.getLocalizedMessage());
      System.out.println("Causa del Error ==> " + ne.getCause());
    } catch (SQLException sqle) {
       System.err.println( sqle.getMessage() );
 
      for (SQLException tempException = sqle; null != tempException; tempException = tempException.getNextException()) {
        System.out.println("A ocurrido el siguiente error en la base de datos");
        System.out.println("Mensaje de Error ==> " + sqle.getLocalizedMessage());
        System.out.println("Causa del Error ==> " + sqle.getCause());
        System.out.println("Estado del SQL ==> " + sqle.getSQLState());
        System.out.println("Vendor codigo de error==> " + sqle.getErrorCode());
      } 
    } 
  }
  
  public Connection getConnection() {
    try {
      if (this.con == null)
        System.out.println("Error getConnection: no hay conexiones activas"); 
    } catch (Exception e) {
      System.out.println("Error getConnection: no se pueden mostrar los datos");
      e.printStackTrace();
    } 
    return this.con;
  }
  
  public int actualizarDatos(String tabla, String campos, String condicion, String usuario, String centro) throws SQLException {
    int resultado = 0;
    String consulta = "";
    if (this.con != null) {
      Funciones fechas = new Funciones();
      String dia = fechas.getFecha();
      String minutos = fechas.getHorass();
      consulta = "UPDATE " + tabla + " SET " + campos + " ,aud_fecha='" + dia + "',aud_hora='" + minutos + "',aud_usuario='" + usuario + "',aud_centro='" + centro + "' WHERE " + condicion;
      System.out.println(consulta);
      Statement st = this.con.createStatement(1005, 1008);
      resultado = st.executeUpdate(consulta);
      st.close();
    } else {
      System.out.println("Error actualizarDatos: no hay conexiones activas");
    } 
    funcionCommit();
    return resultado;
  }
  
  
  public int insertarDatos(String tabla, String campos, String valores) throws SQLException {
    String consulta = "";
    int resultado = 0;
    if (this.con != null) {
      consulta = "INSERT INTO " + tabla + "(" + campos + ") VALUES ('" + valores + "')";
      //System.out.println(consulta);
      Statement st = this.con.createStatement(1005, 1008);
      resultado = st.executeUpdate(consulta);
      st.close();
    } else {
      System.out.println("Error insertarDatos: no hay conexiones activas");
    } 
    funcionCommit();
    return resultado;
  }
  
  public int eliminarFisico(String tabla, String condicion) throws SQLException {
    String consulta = "";
    int resultado = 0;
    if (this.con != null) {
      consulta = "DELETE FROM " + tabla + " where " + condicion;
      System.out.println(consulta);
      Statement st = this.con.createStatement(1005, 1008);
      resultado = st.executeUpdate(consulta);
      st.close();
    } else {
      System.out.println("Error eliminarFisico: no hay conexiones activas");
    } 
    funcionCommit();
    return resultado;
  }
  
  public void funcionCommit() {
    try {
      if (this.con != null) {
        Statement st = this.con.createStatement();
        st.execute("commit");
        st.close();
      } else {
        System.out.println("Error funcionCommit: no hay conexiones activas");
      } 
    } catch (Exception e) {
      System.out.println("Error funcionCommit: no se pueden guardar los datos");
    } 
  }
  
  public void cerrarCon() {
    try {
      if (this.con != null)
        this.con.close(); 
      this.con = null;
    } catch (Exception e) {
      System.out.println("\nError al cerrar la conexion");
    } 
  }
}