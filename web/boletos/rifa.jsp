<%@ include file="../admin/plantilla.jsp" %>
<%@page import="java.util.Random" %>
<%@page import="sis.modelo.BoletoList " %>
<%@page import="sis.modelo.Boletos " %>
<%@page import="sis.modelo.Rifa " %>
<%@page import="sis.modelo.Ventab " %>
<%@page import="sis.bpj.GlobalBusinessBpj " %>
<%@page import="java.util.List"%>
<% GlobalBusinessBpj listIbn=new GlobalBusinessBpj();
String boleto = request.getParameter("boleto");
List listId=listIbn.getSorteoBoletos(boleto);
Boletos bv=listIbn.getBoleto(boleto);
List listvb=listIbn.getListaVentab(boleto);



Random rand = new Random(); 
int upperbound = bv.getNumero_boleto();
int int_random = rand.nextInt(upperbound); 
if(int_random==0 ) int_random = rand.nextInt(upperbound); 
%>
<link rel="stylesheet" href="../recursos/js/tabla/theme.default.css">
<!-- load jQuery and tablesorter scripts -->
<script type="text/javascript" src="../recursos/js/tabla/jquery-latest.min.js"></script>
<script type="text/javascript" src="../recursos/js/tabla/jquery.tablesorter.js"></script>
<script type="text/javascript" src="../recursos/js/tabla/jquery.tablesorter.widgets.js"></script>
<script type="text/javascript" src="../recursos/js/funciones.js"></script>
<script language="JavaScript">
$(function() {
  $("#myTable").tablesorter( {widgets:['zebra']} );
});
</script>
<script language="JavaScript">
function rifa(){
  var premiosx = $( "#premiosx" ).val();  
  submitFORM('/boletos/Sorteo',{'boleto':<%=bv.getCod_boleto() %>,'premiosx':premiosx }, 'POST');
}
</script>

<div align="center">
<a href="inicio.jsp"  class="myButton">Go back</a>
</div>
<div align="center">
<fieldset class="titu2" >
<legend>Ticket details</legend>
<table width="90%" border="0" align="center">
<tr><td><strong>Ticket: </strong> <%=bv.getCod_boleto() %></td>
    <td><strong>Draw date: </strong><%=bv.getFrifa() %>-<%=bv.getHora_rifa() %></td>
    <td><strong>Awards: </strong><%=bv.getPremios() %></td></tr>
<tr><td><strong>Price: </strong><%=bv.getValor() %></td>
    <td><strong>Titles: </strong><%=bv.getTitulo()  %></td>
    <td><strong>Place: </strong><%=bv.getLugar() %></td></tr>
<tr><td colspan="5"><strong>Note: </strong><%=bv.getNotas() %></td></tr>
</table>
</fieldset>

<link rel="stylesheet" href="../recursos/css/estilos.css">

<fieldset class="titu2" >
<legend>Draw ticket</legend>
<table width="90%" border="0" align="center">
<tr><td><strong>Total tickets</strong></td>   
    <td><%= bv.getNumero_boleto()  %></td></tr>
<tr><td><strong>Awards</strong></td>   
    <td><input type="text" name="premiosx" id="premiosx" ></td></tr>
<tr><td><strong></strong></td>
    <td><a href="#" class="myButton" onclick="rifa()">Raffle</a></td></tr>
</table>
</fieldset>

<table width="100%" id="myTable" class="tablesorter-blue" >
<thead> 
<tr class="gradient">
  <th class="gridTab">Fecha</th>
  <th class="gridTab">Boleto</th> 
  <th class="gridTab">Premio</th></tr></thead> 
<tbody id="_editable_table">   
<% for(int j=0;j<listId.size();j++)
{  Rifa b = (Rifa)listId.get(j); %>
<tr>
  <td><%= b.getFecha() %></td>
  <td><%= b.getNboleto() %></td>
  <td><%= b.getPremio() %></td></tr>
<% } %>
</tbody>
</table>
</div>