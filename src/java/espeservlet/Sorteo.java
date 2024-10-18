package espeservlet;

import cadenab.BlockChain;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sis.bpj.GlobalBusinessBpj;
import sis.business.GlobalBusiness;
import sis.modelo.Boletos;
import sis.modelo.Rifa;
import sis.modelo.VarGlobales;

public class Sorteo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            GlobalBusiness listaPac = new GlobalBusiness();
            GlobalBusinessBpj listIbn=new GlobalBusinessBpj();
            BlockChain blockChain = new BlockChain();  
            VarGlobales varGbean = new VarGlobales();
            HttpSession sesionOk = request.getSession();
            if (sesionOk.getAttribute("varGbean") == null)
            {   String redirectURL = "../admin/login.jsp";
                response.sendRedirect(redirectURL);
            }
            else
            {   varGbean = (VarGlobales)sesionOk.getAttribute("varGbean");
            }

            String boleto =request.getParameter("boleto");  
            String premiosx =request.getParameter("premiosx");  
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            String horacompra=dtf.format(LocalDateTime.now());   
            
            Boletos bv=listIbn.getBoleto(boleto);
            List listvb=listIbn.getListaVentab(boleto);

            Random rand = new Random(); 
            int upperbound = bv.getNumero_boleto();
            int int_random = rand.nextInt(upperbound); 
            if(int_random==0 ) int_random = rand.nextInt(upperbound);      
            
            List listId=listIbn.getSorteoBoletos(boleto);
            for(int j=0;j<listId.size();j++)
            {  Rifa b = (Rifa)listId.get(j);
               if(int_random==Integer.parseInt(b.getNboleto()) ) {
                   int_random = rand.nextInt(upperbound);
               }                  
            }
            
            
            
            String json1 = "\"rifa\":{\"nboleto\":"+int_random+"," + "\"boleto\":\""+boleto+"\","
            + " \"premio\":\""+premiosx+"\",\"Fecha Compra\":\""+horacompra+"\",\"usuario\":\""+varGbean.getIdUsuario()+"\"   }";
            
            List lb3 = listaPac.getBloque();            
            int length = lb3.size();
           
            if(lb3.size()==0  ){
                if(blockChain.size()==0  ){
                    listaPac.guardarBloqueGenesis();                               
                }  
            }

            listaPac.guardarBloque(json1);             
             
            response.sendRedirect("/boletos/boletos/rifa.jsp?boleto="+boleto);
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
