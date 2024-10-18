package espeservlet;

import cadenab.BlockChain;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sis.business.GlobalBusiness;
import sis.modelo.Boletos;
import sis.modelo.VarGlobales;

public class GuardaBoleto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) 
        {
            GlobalBusiness listaPac = new GlobalBusiness();
            BlockChain blockChain = new BlockChain();  
            VarGlobales varGbean = new VarGlobales();
            HttpSession sesionOk = request.getSession();
            if (sesionOk.getAttribute("varGbean") == null)
            {
                String redirectURL = "../admin/login.jsp";
                response.sendRedirect(redirectURL);
            }
            else
            {
                varGbean = (VarGlobales)sesionOk.getAttribute("varGbean");
            }
            
            String titulo =request.getParameter("titulo");String numero_boleto = request.getParameter("numero_boleto");
            String frifa = request.getParameter("frifa");  String premios = request.getParameter("premios");
            String valor = request.getParameter("valor");  String hora_rifa = request.getParameter("hora_rifa");
            String lugar = request.getParameter("lugar");  String notas = request.getParameter("notas");    
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String cod_boleto=dtf.format(LocalDateTime.now());            

            String json1 = "\"dato\":{\"cod_boleto\":"+cod_boleto+",\"numero_boleto\":"+numero_boleto+"," + "\"titulo\":\""+titulo+"\",\"premios\":\""+premios+"\","
                    + " \"frifa\":\""+frifa+"\"," + "\"hora_rifa\":\""+hora_rifa+"\",\"valor\":\""+valor+"\", \"lugar\":\""+lugar+"\","
                    + "\"notas\":\""+notas+"\",\"usuario\":\""+varGbean.getIdUsuario()+"\",\"boletosf\":xxxxxxxxxx   }";
            
            //out.write("<br/>json1: "+json1);             
            
            List lb3 = listaPac.getBloque();            
            int length = lb3.size();
            //out.write("<br> lista:"+length);             
            //out.write("<br> tamaño:"+blockChain.size()  );  
            
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
             
             response.sendRedirect("/boletos/boletos/inicio.jsp");
              

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

}
