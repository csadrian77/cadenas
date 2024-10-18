<%@ include file="../admin/plantilla.jsp" %>
<%@page import="sis.modelo.BoletoList " %>
<%@page import="sis.modelo.Boletos " %>
<%@page import="sis.modelo.Ventab " %>
<%@page import="sis.bpj.GlobalBusinessBpj " %>
<%@page import="java.util.List"%>
<% GlobalBusinessBpj listIbn=new GlobalBusinessBpj();
String boleto = request.getParameter("boleto");
List listId=listIbn.getListarBoletosfs(boleto);
Boletos bv=listIbn.getBoleto(boleto);
List listvb=listIbn.getListaVentab(boleto);
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
    submitFORM('/boletos/Readfile',{'boleto':boleto,'nboleto':nboleto }, 'POST');
}

function bboletovd(boleto,nboleto){
    submitFORM('/boletos/Readfilevb',{'boleto':boleto,'nboleto':nboleto }, 'POST');
}

function comprar(idj,boleto,nboleto){
    var cedula = $( "#cedula"+idj ).val();
    var nombre = $( "#nombre"+idj ).val();
    var telefono = $( "#telefono"+idj ).val();
    var direccion = $( "#direccion"+idj ).val();
    var email = $( "#email"+idj ).val();
    //alert('cedula:'+cedula+'-nombre:'+nombre+'-telefono:'+telefono+'-direccion:'+direccion+'-email'+email);
    submitFORM('/boletos/Vender',{'boleto':boleto,'nboleto':nboleto,
        'cedula':cedula,'nombre':nombre,'telefono':telefono,'direccion':direccion,'email':email 
    }, 'POST');
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

<div align="center">
<table width="100%" id="myTable" class="tablesorter-blue" >
<thead> 
<tr class="gradient">
  <th class="gridTab">Ticket</th>
  <th class="gridTab">Sold</th>
  <th class="gridTab">Detail</th>
  <th class="gridTab">Admin</th></tr></thead> 
<tbody id="_editable_table">   
<% String vendido="";  String btnventa=""; String detalle=""; 
for(int j=0;j<listId.size();j++)
{  BoletoList b = (BoletoList)listId.get(j);
   vendido="";          
   btnventa="<a href='#' class='myButton' onclick=\"javascript:comprar("+j+",'"+b.getBoleto() +"','"+b.getNboleto()+"')\">Sell</a>";
   detalle="<input type='text' name='cedula' id='cedula"+j+"' size='10' placeholder='identification'>"+
            "<input type='text' name='nombre' id='nombre"+j+"' placeholder='Names'>"+
            "<input type='text' name='telefono' id='telefono"+j+"'  size='8' placeholder='Phone'>"+
            "<input type='text' name='direccion' id='direccion"+j+"' placeholder='Address'>"+
            "<input type='text' name='email' id='email"+j+"' placeholder='Email'></td>";      
   for(int k=0;k<listvb.size();k++)
   {  Ventab v = (Ventab)listvb.get(k);
      int bboleto=Integer.parseInt(b.getNboleto());  
      int vboleto=Integer.parseInt(v.getNboleto());  
//System.out.println("+++++paso:"+v.getNboleto()+"---"+v.getNboleto());     
      if(bboleto==vboleto )
      {
//System.out.println("---paso:"+v.getNboleto()+"---"+v.getNboleto());   
          vendido=" <a href='#' onclick=\"javascript:bboletovd('"+b.getBoleto()+"','"+b.getNboleto()+"')\" download >boleto - "+v.getNboleto()+"</a> ";  
          btnventa=""; 
          detalle="<table width='100%' border='0'>"+
            "<tr><td>Cedula:"+v.getCedula()+"</td>"+
            "    <td>Nombre:"+v.getNombre()+"</td>"+
            "    <td>Telefono:"+v.getTelefono()+"</td>"+
            "    <td>Direccion:"+v.getDireccion()+"</td>"+
            "    <td>Email:"+v.getEmail()+"</td>"+
            "  </tr></table>";
            break;
      } 
   } 
%>
<tr>
    <td><a href="#" onclick="javascript:bboleto('<%=b.getBoleto() %>','<%=b.getNboleto() %>')" download >boleto - <%= b.getNboleto() %></a></td>
    <td><%=vendido %></td>
    <td><%=detalle %></td>
    <td><%=btnventa %></td>
</tr>
<% } %>
</tbody>
</table>
</div>