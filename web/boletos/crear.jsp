<%@ include file="../admin/plantilla.jsp" %>
<link rel="stylesheet" href="../recursos/css/estilos.css">

<div align="center">
<a href="inicio.jsp"  class="myButton">go back</a>

<form name="frm_boleto" method="post" action="/boletos/GuardaBoleto" >
<table width="331" class="tblc" >
<tr><td colspan="2">Generate tickets</td></tr>
<tr><td width="139"><label for="numero_boleto">Number of tickets</label></td>
    <td width="176"><input type="number" name="numero_boleto" id="numero_boleto" min="1" max="10000" value="0"></td></tr>
<tr><td>Titles</td>
    <td><textarea name="titulo"></textarea></td></tr>
<tr><td>Awards</td>
    <td><textarea name="premios"></textarea></td></tr>
<tr><td>Draw date</td>
    <td><input type="date" name="frifa" value="2018-07-22" min="2018-01-01" max="2038-12-31"></td></tr>
<tr><td>Draw time </td>
    <td><input type="text" name="hora_rifa"></td></tr>
<tr><td>Place</td>
    <td><textarea name="lugar"></textarea></td></tr>
<tr><td>Price</td>
    <td><input type="text" name="valor"></td></tr>
<tr><td>Note</td>
    <td><textarea name="notas"></textarea></td></tr>
<tr><td colspan="2"><div align="center">
    <input type="submit" name="Submit" value="create"  class="myButton"></div></td></tr>
</table>
</form>





</div>