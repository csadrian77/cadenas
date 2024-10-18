package cadenasrv;

import cadenab.BlockChain;
import cadenab.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sis.business.GlobalBusiness;
import sis.modelo.Boletos;
import sis.modelo.VarGlobales;

public class Medida extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Servlet Medida</title></head><body>");
            out.println("<h1>Servlet Medida at " + request.getContextPath() + "</h1></body></html>");

               System.out.println("Numero de ceros------------------------- ----------------------- "+Constants.DIFFICULTY);
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Date date = new Date();
                long startTime=0;
                startTime = System.nanoTime();
                System.out.println("inicio"+dateFormat.format(date)+"-----"+startTime);

                //testTimeout();      
                guardartest();

                
                long endTime=0;
                DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
                Date date1 = new Date();
                endTime = System.nanoTime();
                System.out.println("fin"+dateFormat1.format(date1)+"-----"+endTime);
                System.out.println("Duración: " + (endTime-startTime)/1e6 + " ms");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    public  void testTimeout() throws InterruptedException{
        System.out.println("Funcion tiempo");
        Thread.sleep(9000);
        System.out.println("Fin");
    }    

    protected void guardartest() {
        GlobalBusiness listaPac = new GlobalBusiness();
        BlockChain blockChain = new BlockChain();  
        VarGlobales varGbean = new VarGlobales();
           
        String titulo ="PRUEBA_titulo";String numero_boleto="1";
        String frifa = "PRUEBA_frifa"; String premios = "PRUEBA_premios";
        String valor = "PRUEBA_valor"; String hora_rifa="PRUEBA_hora_rifa";
        String lugar = "PRUEBA_lugar"; String notas = "PRUEBA_notas";    
            
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String cod_boleto=dtf.format(LocalDateTime.now());            

        String json1 = "\"dato\":{\"cod_boleto\":"+cod_boleto+",\"numero_boleto\":"+numero_boleto+"," + "\"titulo\":\""+titulo+"\",\"premios\":\""+premios+"\","
                    + " \"frifa\":\""+frifa+"\"," + "\"hora_rifa\":\""+hora_rifa+"\",\"valor\":\""+valor+"\", \"lugar\":\""+lugar+"\","
                    + "\"notas\":\""+notas+"\",\"usuario\":\"1\",\"boletosf\":xxxxxxxxxx   }";

        List lb3 = listaPac.getBloque();            
        int length = lb3.size();
            
        if(lb3.size()==0  ){
           if(blockChain.size()==0  ){
               listaPac.guardarBloqueGenesis();                               
           }  
        }
             
        Boletos b= new Boletos();
        b.setCod_boleto(cod_boleto);
        b.setTitulo(titulo); b.setNumero_boleto(Integer.parseInt(numero_boleto));
        b.setFrifa(frifa);    b.setPremios(premios);
        b.setValor(valor);    b.setHora_rifa(hora_rifa);
        b.setLugar(lugar);    b.setNotas(notas);            
             
        String jsonarchivo=listaPac.generarPdfs(b,varGbean.getRuta());
        json1=json1.replaceAll("xxxxxxxxxx", jsonarchivo);
             
        //out.write("<br> paso1:"+json1);    
        listaPac.guardarBloque(json1);             
        //out.write("<br> paso1:"+blockChain.size());                
        
    }
    
    
    
    
}
