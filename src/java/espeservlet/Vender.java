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
import sis.bpj.GlobalBusinessBpj;
import sis.business.GlobalBusiness;
import sis.modelo.BoletoList;
import sis.modelo.Boletos;
import sis.modelo.VarGlobales;
import sis.modelo.Ventab;

public class Vender extends HttpServlet {


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

            String boleto =request.getParameter("boleto");  String nboleto = request.getParameter("nboleto");
            String cedula = request.getParameter("cedula");  String telefono = request.getParameter("telefono");
            String nombre = request.getParameter("nombre");  String direccion = request.getParameter("direccion");
            String email = request.getParameter("email"); 
            
            BoletoList b=new BoletoList();
            b=listIbn.getBoletosfs(nboleto,boleto);
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            String horacompra=dtf.format(LocalDateTime.now());   
            
            
            String json1 = "\"venta\":{\"idr_bloque\":"+b.getIdbloque()+",\"nboleto\":"+nboleto+"," + "\"boleto\":\""+boleto+"\",\"hashold\":\""+b.getHashb()+"\","
            + " \"rutaold\":\""+b.getRutab()+"\",\"cedula\":\""+cedula+"\",\"nombre\":\""+nombre+"\", \"telefono\":\""+telefono+"\","
            + "\"direccion\":\""+direccion+"\",\"email\":\""+email+"\",\"Fecha Compra\":\""+horacompra+"\",\"usuario\":\""+varGbean.getIdUsuario()+"\" ,  xxxxxxxxxx   }";
            
            List lb3 = listaPac.getBloque();            
            int length = lb3.size();
           
            if(lb3.size()==0  ){
                if(blockChain.size()==0  ){
                    listaPac.guardarBloqueGenesis();                               
                }  
            }
           
            Ventab vb= new Ventab();            
            vb.setNboleto(nboleto); vb.setIdr_bloque(b.getIdbloque());
            vb.setBoleto(boleto);   vb.setHashold(b.getHashb());
            vb.setCedula(cedula);    vb.setRutaold(b.getRutab());
            vb.setNombre(nombre);    vb.setTelefono(telefono);
            vb.setEmail(email);       vb.setDireccion(direccion);
            vb.setFcompra(horacompra);
             
            String jsonarchivo=listaPac.venderPdfs(vb,varGbean.getRuta());
            json1=json1.replaceAll("xxxxxxxxxx", jsonarchivo);
             
            //out.write("<br> paso1:"+json1);    
            listaPac.guardarBloque(json1);             
            //out.write("<br> paso1:"+blockChain.size());                
             
            response.sendRedirect("/boletos/boletos/comprar.jsp?boleto="+boleto);
            
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
