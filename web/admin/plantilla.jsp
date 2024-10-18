<!DOCTYPE html>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@page import="java.util.ArrayList" %>
<%@page import="sis.modelo.VarGlobales" %>
<%@ page session="true" %>
<% VarGlobales varGbean = new VarGlobales();
   HttpSession sesionOk = request.getSession();
   if (sesionOk.getAttribute("varGbean") == null){ %>
<jsp:forward page="/admin/login.jsp"><jsp:param name="error" value="Es obligatorio identificarse"/></jsp:forward>
<% } else {  varGbean = (VarGlobales)sesionOk.getAttribute("varGbean"); }  %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="shortcut icon" href="../recursos/img/favicon1.ico"/>

<title>Boletos</title>
<style type="text/css">
.Leftmenu {
    COLOR: #666; 
    FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif; 
    FONT-SIZE: 10px; 
    TEXT-DECORATION: none;  
}
#coolmenu{background-color: #f0f0f0 ;}  /** color del fondo de los menus **/
#coolmenu a{
font: bold 12px Verdana;
padding: 2px;
padding-left: 4px;
display: block;
width: 100%;
color: #1D1D1D; /** color de las letras **/
}
html>body #coolmenu a{ width: auto; }
#coolmenu a:hover{
background-color:#E7EDDF ; /** color al cambiar el menu **/
color: #666;
}
</style>


</head>
<body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" >
<table class="colophon" width="100%" border="0" cellspacing="0" cellpadding="0"  >
<tr><td><div><span class="sist"><strong>Ticket drawing system</strong></span></div></td>  
    <td><div align="right">
            <a class="sist2" href="/boletos/boletos/inicio.jsp" >start</a>
            <a class="sist2" href="#" ><% out.write(varGbean.getUsuario()); %></a>
            <a class="sist2" href="/boletos/admin/cerrarsesion.jsp" >close</a>
         </div></td></tr>
</table>
               
               