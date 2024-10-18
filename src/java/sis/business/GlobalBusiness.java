package sis.business;

import cadenab.Block;
import cadenab.BlockChain;
import cadenab.Constants;
import cadenab.Miner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import sis.funciones.Jdbc_sqls;
import sis.modelo.Bloque;
import sis.modelo.BoletoList;
import sis.modelo.Boletos;
import sis.modelo.Rifa;
import sis.modelo.Ventab;

public class GlobalBusiness {
    
    
  public int guardarBloqueGenesis()
  {  System.out.println("llego a guardar:" );  
    int resultado = 0;
    try
    {
        Jdbc_sqls ing_boleto = new Jdbc_sqls();
        BlockChain blockChain = new BlockChain();  
        Miner miner = new Miner();  
        //Nosotros hemos creado la génesis o bloque 0,   
        // pasaremos el id, la cadena de transacción y el hash anterior  
        //como este es el primer bloque, tenemos que proporcionar manualmente el hash anterior     
        String valores ="{\"idbloque\":0," + "\"previousHash\":\"0000000000000000000000000000000000000000000000000000000000000000\"," + "\"actualhash\":\"0000000000000000000000000000000000000000000000000000000000000000\",\"timeStamp\":0,\"nonce\":0,\"dato\":\"inicio de bloque\" }";
        Block block0 = new Block(0, valores,Constants.GENESIS_PREV_HASH);    
        //el minero tomará la transacción y extraerá el bloque
        //para encontrar el valor hash y el minero agregará el bloque a Blockchain
        miner.mine(block0, blockChain);                   
  
        String tabla = "bloque";
        String campos = "bloque ";       
        resultado = ing_boleto.insertarDatos(tabla, campos,valores  );
        ing_boleto.cerrarCon();
    }
    catch (Exception e)
    {
        System.out.println("Error GlobalBusiness.guardarBloqueGenesis: no se pueden ingresar los datos:" );
        e.printStackTrace();
    }
    return resultado;
  }    
  
  public int guardarBloque(String json1)
  {
    int resultado = 0;
    try
    {
        Jdbc_sqls ing_boleto = new Jdbc_sqls();
        GlobalBusiness gb = new GlobalBusiness(); 
        BlockChain blockChain = new BlockChain();  
        Miner miner = new Miner();  
        
        blockChain=gb.LeerBloque();           
        Block blockn = new Block(blockChain.size(),json1,blockChain.getBlockChain().get(blockChain.size()-1).getHash());  
        miner.mine(blockn, blockChain);  
        
        int idbloque =blockChain.getBlockChain().get(blockChain.size()-1).getId();
        String previousHash = blockChain.getBlockChain().get(blockChain.size()-1).getPreviousHash();
        String actualhash = blockChain.getBlockChain().get(blockChain.size()-1).getHash();
        long timeStamp = blockChain.getBlockChain().get(blockChain.size()-1).getTimeStamp();
        int nonce = blockChain.getBlockChain().get(blockChain.size()-1).getNonce();
        String tabla = "bloque";
        String campos = "bloque ";   
        String cabecera ="{\"idbloque\":"+idbloque+", \"previousHash\":\""+previousHash+"\"," + "\"actualhash\":\""+actualhash+"\",\"timeStamp\":"+timeStamp+",\"nonce\":"+nonce+","+json1+" }";
       
        //System.out.println("nuevob:"+cabecera);        
        
        resultado = ing_boleto.insertarDatos(tabla, campos,cabecera );      
               
        ing_boleto.cerrarCon();
    }
    catch (Exception e)
    {
      System.out.println("Error GlobalBusiness.guardarBloque: no se pueden ingresar los datos:" );
      e.printStackTrace();
    }
    return resultado;
  }      
  
  
  
  public String generarPdfs(Boletos b, String ruta)
  { String resultado = "[";
    String archivo = "";
    try
    {
        int cantidad=b.getNumero_boleto();
        String idtabla="t1";
        ruta=ruta+idtabla;
        int cantidad_ceros = 5;
        String formatted = "";
 
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }         
        
        for(int i=1; i<=cantidad; i++)
        {   //System.out.println("i:"+i);
            formatted = String.format("%0" + cantidad_ceros + "d",i );
            try (PDDocument doc = new PDDocument()) 
            {   PDPage myPage = new PDPage(PDRectangle.A6);
                doc.addPage(myPage);
                try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) 
                {   cont.beginText();
                    cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                    cont.setLeading(14.5f);
                    cont.newLineAtOffset(25, 350);
                    String line0 = "BOLETO DE RIFA No. "+formatted;    cont.showText(line0); cont.newLine();
                    String line1 = b.getTitulo();                      cont.showText(line1); cont.newLine();
                    String line2 = "Fecha: "+b.getFrifa()+ " hora: "+b.getHora_rifa(); cont.showText(line2);  cont.newLine();
                    String line3 = "Valor: "+b.getValor();             cont.showText(line3);  cont.newLine();
                    String line4 = "Premios: "+b.getPremios();         cont.showText(line4);  cont.newLine();
                    String line5 = "Lugar: "+b.getLugar();             cont.showText(line5);  cont.newLine();
                    String line6 = "Nota: "+b.getNotas();              cont.showText(line6);  cont.newLine();
                    cont.endText();
                }
                archivo=ruta+"/boleto-"+b.getCod_boleto()+"-"+formatted+".pdf";
                String nomarchivo="boleto-"+b.getCod_boleto()+"-"+formatted+".pdf";
                doc.save(archivo); 
                
                resultado=resultado+ "{\"nboleto\":"+i+", \"boleto\":\""+b.getCod_boleto()+"\"," +
                  " \"hashb\":\""+archivoHash(archivo)+"\", \"rutab\":\""+nomarchivo+"\"},";      
               // System.out.println("resultado:"+resultado );
            }
        }      
        resultado=resultado+ "]";
        resultado=resultado.replaceAll("},]", "}]");        
    }
    catch (Exception e)
    {   System.out.println("Error GlobalBusiness.generarPdfs:" );
        e.printStackTrace();
    }
    return resultado;
  }    
  
  public String archivoHash(String archivo)
  {  String resultado="";     
     resultado=generaHash(archivo,"SHA-256") ;
     return resultado;
  }   
  
   public String generaHash(String rutaArchivo,String algoritmo)
   {String hash="";
   
    //declarar funcion de resumen
      try{
      MessageDigest messageDigest = MessageDigest.getInstance(algoritmo); // Inicializa con algoritmo seleccionado
     
      //leer fichero byte a byte 
         try{
            InputStream archivo = new FileInputStream( rutaArchivo );
            byte[] buffer = new byte[1];
            int fin_archivo = -1;
            int caracter;
       
            caracter = archivo.read(buffer);
       
            while( caracter != fin_archivo ) {
         
               messageDigest.update(buffer); // Pasa texto claro a la función resumen
               caracter = archivo.read(buffer);
            }   
       
            archivo.close();//cerramos el archivo
            byte[] resumen = messageDigest.digest(); // Genera el resumen 
            
            //Pasar los resumenes a hexadecimal
            hash = "";
            for (int i = 0; i < resumen.length; i++)
            {
               hash += Integer.toHexString((resumen[i] >> 4) & 0xf);
               hash += Integer.toHexString(resumen[i] & 0xf);
            }
          //  System.out.println("Resumen "+algoritmo+" " + hash);
         }
         //lectura de los datos del fichero
         catch(java.io.FileNotFoundException fnfe) {
         //manejar excepcion archivo no encontrado
         }
         catch(java.io.IOException ioe) {
         //manejar excepcion archivo
         }
      
      }   
      //declarar funciones resumen
      catch(java.security.NoSuchAlgorithmException nsae) {
      //manejar excepcion algorito seleccionado erroneo
      }
        return hash;//regresamos el resumen
      
   }

  public String venderPdfs(Ventab b, String ruta)
  { String resultado = "";
    String archivo = "";
    try
    {       
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }         
        
        File file = new File(ruta+"t1/"+b.getRutaold()); 
        PDDocument document = PDDocument.load(file); 
        System.out.println("PDF loaded"); 
        //Retrieving the page
        PDPage page = (PDPage)document.getPages().get( 0 );

        PDPageContentStream content = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

        content.beginText();      
        //Setting the font to the Content stream  
        content.setFont(PDType1Font.TIMES_ROMAN, 12);  

        //Setting the position for the line (float x, float y), (0,0) = lower left corner
        content.setLeading(14.5f);
        content.newLineAtOffset(25, 200);
        String text = "Cedula: "+b.getCedula();
        String text1 = "Nombre: "+b.getNombre();
        String text2 = "Telefono: "+b.getTelefono();
        String text3 = "Direccion: "+b.getDireccion();
        String text4 = "Email: "+b.getEmail();
        String text5 = "Fecha de compra: "+b.getFcompra();
        //Adding text in the form of string 
        content.showText(text);   content.newLine();
        content.showText(text1);  content.newLine();
        content.showText(text2);  content.newLine();
        content.showText(text3);  content.newLine();
        content.showText(text4);  content.newLine();
        content.showText(text5);  content.endText();
     
        System.out.println("Text added"); 

        content.close();
        document.save(ruta+"vb/"+b.getRutaold());
        document.close();        
        
        archivo=ruta+"vb/"+b.getRutaold();        
        resultado=resultado+ "\"hashnew\":\""+archivoHash(archivo)+"\" ";   
        
    }
    catch (Exception e)
    {   System.out.println("Error GlobalBusiness.generarPdfs:" );
        e.printStackTrace();
    }
    return resultado;
  }    
  
  

  
  
   
   
  public BlockChain LeerBloque()
  {   Jdbc_sqls ing_boleto = new Jdbc_sqls();
      BlockChain blockChain = new BlockChain();  
      String resultado = "";
      int idbloque =0;
      String previousHash = "";
      String actualhash = "";
      String timeStamp = "";
      int nonce = 0;
      try
      {
          JsonParser parser = new JsonParser();
          List listId=getBloque();
          for(int j=0;j<getBloque().size();j++) 
          {
              Bloque b = (Bloque)listId.get(j);
              resultado = b.getBloque();    
           
              // Obtain Array
              JsonArray gsonArr = parser.parse("["+resultado+"]").getAsJsonArray();          
              for (JsonElement obj : gsonArr) {
                 // Object of array
                 JsonObject gsonObj = obj.getAsJsonObject();
                 // Primitives elements of object
                 idbloque = gsonObj.get("idbloque").getAsInt();
                 previousHash = gsonObj.get("previousHash").getAsString();
                 actualhash = gsonObj.get("actualhash").getAsString();
                 timeStamp = gsonObj.get("timeStamp").getAsString();
                 nonce = gsonObj.get("nonce").getAsInt();
                 //System.out.println("previousHash"+previousHash);
                
                 long ltimeStamp=Long.parseLong(timeStamp);  
                 Block block1 = new Block(idbloque,resultado,previousHash,ltimeStamp,nonce,actualhash);  
                 blockChain.addBlock(block1);
              } 
          }    
                
          ing_boleto.cerrarCon();
      }
      catch (Exception e)
      {  System.out.println("Error GlobalBusiness.LeerBloque:" );
         e.printStackTrace();
      }
      return blockChain;
  }      
    
   public ArrayList<BoletoList> getListarBoletosfs(String codBoleto) 
  {  ArrayList<BoletoList> list = new ArrayList<BoletoList>();
     try 
     {  Jdbc_sqls conexion = new Jdbc_sqls();
        Connection con = conexion.getConnection();
        String consulta = "select json_array_elements( bloque->'dato'->'boletosf' )->>'nboleto' as nboleto " +
            ",json_array_elements( bloque->'dato'->'boletosf' )->>'hashb' as hashb " +
            ",json_array_elements( bloque->'dato'->'boletosf' )->>'rutab' as rutab " +
            ",json_array_elements( bloque->'dato'->'boletosf' )->>'boleto' as boleto " +    
            "from bloque where bloque->'dato'->>'boletosf' is not  null " +
            "and bloque->'dato'->>'cod_boleto'='"+codBoleto+"' ";
        //System.out.println(consulta);
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) 
        {  BoletoList b = new BoletoList();
           b.setNboleto(rs.getString(1));
           b.setHashb(rs.getString(2));
           b.setRutab(rs.getString(3));
           b.setBoleto(rs.getString(4));
           list.add(b);
        } 
        ps.close();
        rs.close();
        conexion.cerrarCon();
     } 
     catch (SQLException e) 
     {  System.out.println("Error GlobalBusiness.getListarBoletos:");
        e.printStackTrace();
     } 
     return list;
   } 
  
   public BoletoList getBoletosfs(String nboleto, String boleto) 
  {  BoletoList b = new BoletoList();
     try 
     {  Jdbc_sqls conexion = new Jdbc_sqls();
        Connection con = conexion.getConnection();
        String consulta = "select rutab,boleto,hashb,idbloque from vlistaboleto where boleto='"+boleto+"' and nboleto='"+nboleto+"' ";
//System.out.println(consulta);
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) 
        {   b.setRutab(rs.getString(1));
            b.setBoleto(rs.getString(2));
            b.setHashb(rs.getString(3));
            b.setIdbloque(rs.getString(4));
        } 
        ps.close();
        rs.close();
        conexion.cerrarCon();
     } 
     catch (SQLException e) 
     {  System.out.println("Error GlobalBusiness.getBoletosfs:");
        e.printStackTrace();
     } 
     return b;
   }    
   
  public ArrayList<Bloque> getBloque() 
  {  ArrayList<Bloque> list = new ArrayList<Bloque>();
     try 
     {  Jdbc_sqls conexion = new Jdbc_sqls();
        Connection con = conexion.getConnection();
        String consulta = "select idb,bloque from Bloque order by idb ";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) 
        {  Bloque b = new Bloque();
           b.setIdb(rs.getString(1));
           b.setBloque(rs.getString(2));
           //System.out.println("Ingero al bloque:"+b.getIdb());
           list.add(b);
        } 
        ps.close();
        rs.close();
        conexion.cerrarCon();
     } 
     catch (SQLException e) 
     {  System.out.println("Error GlobalBusiness.getBloque:");
        e.printStackTrace();
     } 
     return list;
   }
    
  public String[] validarUsuario(String usuario, String clave) {
    ResultSet rs = null;
    String[] datos = new String[2];
    String bs_usuarioid = "";
    String bs_centro = "";
    String bs_nick = "";
    String bs_clave = "";
    String bs_permiso = "";
    datos[0] = "0";
    try {
      Jdbc_sqls ing = new Jdbc_sqls();
      Connection con = ing.getConnection();
       System.out.println("usuario:" );
      if (con != null) {
          
        Statement st = con.createStatement();
        rs = st.executeQuery("select idu,pms_centro,usr_nick,usr_clave,pms_permiso from validarpermiso where idmr=0 and pms_permiso=1 ");
        for (int i = 1; rs.next(); i++) {
          bs_usuarioid = rs.getString("idu");
          bs_centro = rs.getString("pms_centro");
          bs_nick = rs.getString("usr_nick");
          bs_clave = rs.getString("usr_clave");
          bs_permiso = rs.getString("pms_permiso");
          
          System.out.println("usuario:" + bs_usuarioid +  "--bs_nick:" + bs_nick);
          if (bs_nick.equals(usuario) && bs_clave.equals(clave) && bs_permiso.equals("1") ) {
            datos[0] = "1";
            datos[1] = bs_usuarioid;
          } 
        } 
        st.close();
        rs.close();
      } else {
        System.out.println("Error GlobalBusiness.validarUsuario: no hay conexiones activas");
      } 
      ing.cerrarCon();
    } catch (SQLException e) {
      System.out.println("Error GlobalBusiness.validarUsuario: no se pueden mostrar los datos");
      e.printStackTrace();
    } 
    return datos;
  }
  
  
  public ArrayList<Boletos> getListaBoletos(String id_usr) 
  {  ArrayList<Boletos> list = new ArrayList<Boletos>();
     try 
     {  
        Jdbc_sqls conexion = new Jdbc_sqls();
        JsonParser parser = new JsonParser();
        Connection con = conexion.getConnection();
        String consulta = "select bloque->>'dato' as boleto from bloque " +
        "where  bloque->>'dato' is not null and bloque->'dato'->>'usuario'='"+id_usr+"'  ";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) 
        {  
         
        System.out.println("llego getListaBoletos------"+rs.getString(1) );
            
            // Obtain Array
            JsonArray gsonArr = parser.parse("["+rs.getString(1)+"]").getAsJsonArray();
            // for each element of array
            for (JsonElement obj : gsonArr) 
            {   // Object of array
                Boletos b = new Boletos();
                JsonObject gsonObj = obj.getAsJsonObject();
                // Primitives elements of object
                b.setCod_boleto(gsonObj.get("cod_boleto").getAsString());
                b.setNumero_boleto(gsonObj.get("numero_boleto").getAsInt());
                b.setTitulo(gsonObj.get("titulo").getAsString());
                b.setPremios(gsonObj.get("premios").getAsString());
                b.setFrifa(gsonObj.get("frifa").getAsString());
                b.setHora_rifa(gsonObj.get("hora_rifa").getAsString());
                b.setValor(gsonObj.get("valor").getAsString());
                b.setLugar(gsonObj.get("lugar").getAsString());
                b.setNotas(gsonObj.get("notas").getAsString());
                b.setUsuario(gsonObj.get("usuario").getAsString());
                list.add(b);  
            }   
          
        } 
        ps.close();
        rs.close();
        conexion.cerrarCon();  
     } 
     catch (SQLException e) 
     {  System.out.println("Error GlobalBusiness.getBoletos:");
        e.printStackTrace();
     } 
     return list;
   }
  
  
  public ArrayList<Rifa> getSorteoBoletos(String id_usr) 
  {  ArrayList<Rifa> list = new ArrayList<Rifa>();
     try 
     {  
        Jdbc_sqls conexion = new Jdbc_sqls();
        JsonParser parser = new JsonParser();
        Connection con = conexion.getConnection();
        String consulta = "select  bloque->'rifa'->>'nboleto' as nboleto," +
        "bloque->'rifa'->>'boleto' as boleto, bloque->'rifa'->>'premio' as premio," +
        "bloque->'rifa'->>'Fecha Compra' as fcompra from bloque " +
        "where  bloque->>'rifa' is not null " +
        "and bloque->'rifa'->>'boleto'='"+id_usr+"'  ";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) 
        {   Rifa b = new Rifa();
              b.setNboleto(rs.getString("nboleto"));
              b.setBoleto(rs.getString("boleto"));
              b.setPremio(rs.getString("premio"));
              b.setFecha(rs.getString("fcompra"));
            list.add(b);  
          
        } 
        ps.close();
        rs.close();
        conexion.cerrarCon();  
     } 
     catch (SQLException e) 
     {  System.out.println("Error GlobalBusiness.getBoletos:");
        e.printStackTrace();
     } 
     return list;
   }
  
  
  public ArrayList<Ventab> getListaVentab(String id) 
  {  ArrayList<Ventab> list = new ArrayList<Ventab>();
     try 
     {  Jdbc_sqls conexion = new Jdbc_sqls();
        Connection con = conexion.getConnection();
        String consulta = "select bloque->'venta'->>'idr_bloque' as idr_bloque " +
        ", bloque->'venta'->>'nboleto' as nboleto   , bloque->'venta'->>'boleto' as boleto " +
        ", bloque->'venta'->>'hashold' as hashold    , bloque->'venta'->>'rutaold' as rutaold " +
        ", bloque->'venta'->>'cedula' as cedula      , bloque->'venta'->>'nombre' as nombre " +
        ", bloque->'venta'->>'telefono' as telefono , bloque->'venta'->>'direccion' as direccion " +
        ", bloque->'venta'->>'email' as email        , bloque->'venta'->>'hashnew' as hashnew " +
        ", bloque->'venta'->>'usuario' as usuario    , bloque->'venta'->>'Fecha Compra' as fcompra " +
        " from bloque where bloque->'venta'->>'rutaold' is not  null " +
        " and bloque->'venta'->>'boleto'='"+id+"'  ";        
System.out.println(consulta);
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) 
        {   Ventab b = new Ventab();               
            b.setNboleto(rs.getString("nboleto")); b.setIdr_bloque(rs.getString("idr_bloque"));
            b.setBoleto(rs.getString("boleto"));   b.setHashold(rs.getString("hashold"));         
            b.setCedula(rs.getString("cedula"));    b.setRutaold(rs.getString("rutaold"));
            b.setNombre(rs.getString("nombre"));   b.setTelefono(rs.getString("telefono"));
            b.setEmail(rs.getString("email"));      b.setDireccion(rs.getString("direccion"));
            b.setHashnew(rs.getString("hashnew"));   b.setUsuario(rs.getString("usuario"));
            b.setFcompra(rs.getString("fcompra")); 
            list.add(b);  
         } 
        ps.close(); rs.close();
        conexion.cerrarCon();  
     } 
     catch (SQLException e) 
     {  System.out.println("Error GlobalBusiness.getBoletos:");
        e.printStackTrace();
     } 
     return list;
   }
  
  
  public Boletos getBoleto(String cod_boleto) 
  {  Boletos b = new Boletos();
     try 
     {  
        Jdbc_sqls conexion = new Jdbc_sqls();
        JsonParser parser = new JsonParser();
        Connection con = conexion.getConnection();
        String consulta = "select bloque->>'dato' as boleto from bloque " +
        "where  bloque->>'dato' is not null and bloque->'dato'->>'cod_boleto'='"+cod_boleto+"'  ";
        //System.out.println(" consulta------"+consulta );
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) 
        {           
        System.out.println("llego getListaBoletos------"+rs.getString(1) );
            // Obtain Array
            JsonArray gsonArr = parser.parse("["+rs.getString(1)+"]").getAsJsonArray();
            // for each element of array
            for (JsonElement obj : gsonArr) 
            {   // Object of array
                JsonObject gsonObj = obj.getAsJsonObject();
                // Primitives elements of object
                System.out.println("\nllego getListaBoletos------"+gsonObj.get("titulo").getAsString() );
                
                
                b.setCod_boleto(gsonObj.get("cod_boleto").getAsString());
                b.setNumero_boleto(gsonObj.get("numero_boleto").getAsInt());
                b.setTitulo(gsonObj.get("titulo").getAsString());
                b.setPremios(gsonObj.get("premios").getAsString());
                b.setFrifa(gsonObj.get("frifa").getAsString());
                b.setHora_rifa(gsonObj.get("hora_rifa").getAsString());
                b.setValor(gsonObj.get("valor").getAsString());
                b.setLugar(gsonObj.get("lugar").getAsString());
                b.setNotas(gsonObj.get("notas").getAsString());
                b.setUsuario(gsonObj.get("usuario").getAsString());
            }   
          
        } 
        ps.close();
        rs.close();
        conexion.cerrarCon();  
     } 
     catch (SQLException e) 
     {  System.out.println("Error GlobalBusiness.getBoletos:");
        e.printStackTrace();
     } 
     return b;
   }
  
/*
  public ArrayList<Boletos> getBoletos() 
  {  ArrayList<Boletos> list = new ArrayList<Boletos>();
     try 
     {  Jdbc_sqls conexion = new Jdbc_sqls();
        Connection con = conexion.getConnection();
        String consulta = "select idb,codigo,fecha from boletos where aud_estado=1 order by fecha ";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) 
        {  Boletos b = new Boletos();
           b.setIdb(rs.getString(1));
           b.setCodigo(rs.getString(2));
           b.setFecha(rs.getString(3));
           list.add(b);
        } 
        ps.close();
        rs.close();
        conexion.cerrarCon();
     } 
     catch (SQLException e) 
     {  System.out.println("Error GlobalBusiness.getBoletos:");
        e.printStackTrace();
     } 
     return list;
   }
  */

}
/*

select bloque->'dato'->'cod_boleto'
,json_array_elements( bloque->'dato'->'boletosf' )->'boleto'
,json_array_elements( bloque->'dato'->'boletosf' )->'nboleto'
from bloque
where bloque->'dato'->>'boletosf' is not  null
and bloque->'dato'->>'cod_boleto'='20230918141917'
*/