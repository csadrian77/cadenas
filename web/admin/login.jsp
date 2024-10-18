<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="shortcut icon" href="../recursos/img/favicon1.ico"/>
<link href="../recursos/css/estilos.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../recursos/js/md5.js"></script>
<title>Bienvenidos - Boletos</title>

<script type='text/javascript' language='javascript'>
function verificar() 
{  with (document.frm_alums_new)
   { clave.value=hex_md5(clave.value); 
     document.forms[0].submit();
   }
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
        
    <div id="contenedor">
       <div id="central">
          <div id="login">
            <form name="frm_alums_new" method="post" action="/boletos/Valida" >
            <pre><div class="titulo">Welcome</div></pre>
            <input name="usuario" value="admin" type="text" placeholder="user" />
            <input name="clave" type="password" placeholder="password" />
                 <div align="center">
                     <input type="button" name="Submit" value="send" onClick="verificar()" />
                 </div>
                 <input name="enviarFormulario" value="1" type="hidden" /><br>
            </form>    
          </div>
       </div>
    </div>    

</body>
</html>
