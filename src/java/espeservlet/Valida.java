package espeservlet;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sis.business.GlobalBusiness;
import sis.funciones.Funciones;
import sis.modelo.VarGlobales;

public class Valida extends HttpServlet {

 protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   response.setContentType("text/html;charset=UTF-8");
   PrintWriter out = response.getWriter();
   try 
   {  String usuario = "";
      String clave = "";
      String centrodp = "";
      String cantreg = "10";
      String cantregrep = "30";
      GlobalBusiness listaPac = new GlobalBusiness();
      Funciones fecha = new Funciones();
      if (request.getParameter("usuario")!= null)  usuario=request.getParameter("usuario"); 
      if (request.getParameter("clave") != null)   clave =request.getParameter("clave"); 
      String[] validacion = new String[2];
      validacion = listaPac.validarUsuario(usuario, clave);
      
      System.out.println(usuario + "--" + fecha.getFecha() + ":" + fecha.getHora() + "--" + request.getRemoteAddr() + "--" + request.getHeader("USER-AGENT"));
      if (validacion[0].equals("1")) {
        HttpSession sesionOk = request.getSession();
        VarGlobales captura = new VarGlobales();
        captura.setUsuario(usuario);
        captura.setCantRegistros(cantregrep);
        captura.setIdUsuario(validacion[1]);
        captura.setRuta("/opt/boletos/");
        sesionOk.setAttribute("varGbean", captura);
        response.sendRedirect("/boletos/boletos/inicio.jsp");
      } 
      else {
        System.out.println("Error de acceso:" + usuario + "-" + centrodp);
        response.sendRedirect("/boletos/admin/login.jsp");
      } 
   } 
   finally 
   {
      out.close();
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
