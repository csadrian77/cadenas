<%@ include file="../admin/plantilla.jsp" %>
<%@page import="sis.modelo.Boletos" %>
<%@page import="sis.bpj.GlobalBusinessBpj" %>
<%@page import="java.util.List"%>
<% GlobalBusinessBpj listIbn=new GlobalBusinessBpj();
List listId=listIbn.getListaBoletos(varGbean.getIdUsuario());
//varGbean.getIdUsuario()
%>
<link rel="stylesheet" href="../recursos/css/estilos.css">
<script type="text/javascript" src="../recursos/js/jquery-1.12.4.min.js"></script>
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
function bboleto(boleto,nboleto){
    submitFORM('comprar.jsp',{'boleto':boleto }, 'POST');
}
function rifa(boleto){
    submitFORM('rifa.jsp',{'boleto':boleto }, 'POST');
}
</script>


<div align="right"><a href="crear.jsp" class="myButton">create</a>&nbsp;&nbsp;</div>
<table width="100%" id="myTable" class="tablesorter-blue" >
<thead> 
<tr class="gradient">
  <th class="gridTab">No</th>
  <th class="gridTab">Ticket</th>
  <th class="gridTab">Generated</th>
  <th class="gridTab">Draw date</th>
  <th class="gridTab">Price</th>
  <th class="gridTab">Detail</th>
  <th class="gridTab">Administrator</th></tr></thead> 
<tbody id="_editable_table">   
<% 
for(int j=0;j<listId.size();j++)
{  Boletos b = (Boletos)listId.get(j); %>
<tr  >
  <td><%= j+1 %></td>
  <td><%= b.getCod_boleto() %></td>
  <td><%= b.getNumero_boleto() %></td>
  <td><%= b.getFrifa() %>-<%= b.getHora_rifa() %></td>
  <td><%= b.getValor() %></td>
  <td><div>titulo:<%= b.getTitulo() %></div><div>Premios:<%= b.getPremios() %></div></td>
  <td><a href="#" class="myButton" onclick="javascript:bboleto(<%=b.getCod_boleto() %>)">buy</a>&nbsp;
      <a href="#" class="myButton" onclick="rifa(<%=b.getCod_boleto() %>)">raffle</a></td></tr>
<% } %>
</tbody>
</table>