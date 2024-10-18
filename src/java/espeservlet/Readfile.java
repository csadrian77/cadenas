package espeservlet;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sis.bpj.GlobalBusinessBpj;
import sis.modelo.BoletoList;
import sis.modelo.VarGlobales;


public class Readfile extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlstr = null;
        try 
        {   response.reset();
            VarGlobales varGbean = new VarGlobales();
            HttpSession sesionOk = request.getSession();
            if (sesionOk.getAttribute("varGbean") == null)
            {   String redirectURL = "../admin/login.jsp";
                response.sendRedirect(redirectURL);
            }
            else
            {   varGbean = (VarGlobales)sesionOk.getAttribute("varGbean");
            }
            

        
            GlobalBusinessBpj listIbn=new GlobalBusinessBpj();
            String nboleto = request.getParameter("nboleto");
            String boleto = request.getParameter("boleto");
            BoletoList b=new BoletoList();
            b=listIbn.getBoletosfs(nboleto,boleto);
            
            ServletOutputStream sOutStream = response.getOutputStream();
            //urlstr="boleto-20230919135035-00001.pdf";
            urlstr=b.getRutab(); 
            String inFile=varGbean.getRuta()+"t1/";
 System.out.println("------------------------llego" +inFile +"----"+ urlstr );           
            
            streamBinaryData( inFile, urlstr, sOutStream, response);
            
            
        } 
        catch (Exception e) 
        {   e.printStackTrace();
        }
    }

    private void streamBinaryData( String inFile, String file, ServletOutputStream outstr, HttpServletResponse resp) throws IOException{
        String ErrorStr = null;
        try 
        {   BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            //String inFile = "/opt/boletos/t1/" + file;
            inFile =inFile+ file;
            int tam = (int) new File(inFile).length();
            bis = new BufferedInputStream(new FileInputStream(inFile));
            try 
            {   //Asignamos el tipo de fichero zip
                resp.setContentType("application/x-zip-compressed"); //Cualquier mime type
                //Seteamos el tamaño de la respuesta
                resp.setContentLength(tam);
                resp.setHeader("Content-Disposition", "attachment;filename=\"" + file + "\"");
                bos = new BufferedOutputStream(outstr);
                // Bucle para leer de un fichero y escribir en el otro.
                byte[] array = new byte[1000];
                int leidos = bis.read(array);
                while (leidos > 0) 
                {   bos.write(array, 0, leidos);
                    leidos = bis.read(array);
                }
            } 
            catch (Exception e) 
            {   e.printStackTrace();
                ErrorStr = "Error Streaming the Data";
                outstr.print(ErrorStr);
            } 
            finally 
            {   if (bis != null) {    bis.close();   }
                if (bos != null) {    bos.close();  }
                if (outstr != null) { outstr.flush();  outstr.close(); }
            }
        } 
        catch (Exception e) {
            resp.sendRedirect("fileNotFound.jsp");
        }
    }
  }