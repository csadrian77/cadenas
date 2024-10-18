//submitFORM('http://example.com/script.php', 'POST',{'name':'digital+inspiration', 'age':'100', 'sex','M'});
function submitFORM(path, params, method) 
{   method = method || "post"; 
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    //Move the submit function to another variable
    //so that it doesn't get overwritten.
    form._submit_function_ = form.submit;
    for(var key in params) 
    {   if(params.hasOwnProperty(key)) 
        {   var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);
            form.appendChild(hiddenField);
         }
    }
    document.body.appendChild(form);
    form._submit_function_();
    //form.submit();
}

// onkeypress="return SoloTextos(event)"
function SoloTextos(e)
{ key=(document.all) ? e.keyCode : e.which;
  if ( (key<32 || key >= 33) && (key<65 || key>90) && (key<97 || key>122)  && (key<239 || key>=240))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
  {  alert("Solo se pueden ingresar TEXTO");
      return false;
   }
   return true;
    
} 

 // onkeypress="return onkeyPresionaNumero(event)"
function onkeyPresionaNumero(e)
{  key=(document.all) ? e.keyCode : e.which;
   //if (key<46 || key==47 ||key>57  || key==127)
   //if(( key >= 97 && key <= 122) || (key >=65 && key <=90) || (key >=32 && key <=47) )
   if(( key >= 97 && key <= 122) || (key >=58 && key <=90) || (key >=32 && key <=47) )
   {  alert("Solo se pueden ingresar NUMEROS");
      return false;
   }
   return true; 
}

 // onkeypress="return onkeyBlanco(event)"
function onkeyBlanco(e)
{  key=(document.all) ? e.keyCode : e.which;
   //if (key<46 || key==47 ||key>57  || key==127)
   //if(( key >= 97 && key <= 122) || (key >=65 && key <=90) || (key >=32 && key <=47) )
   if( key ==32)
   {  alert("Hay un espacio en blanco");
      return false;
   }
   return true; 
}



 // onkeypress="return onkeyPresionaNumero(event)"
function onkeyPresionaDecimal(e)
{  key=(document.all) ? e.keyCode : e.which;
   //if (key<46 || key==47 ||key>57  || key==127)
   if(( key >= 97 && key <= 122) || (key >=65 && key <=90) || (key >=32 && key <=45) )
   {  alert("Solo se pueden ingresar NUMEROS");
      return false;
   }
   return true; 
}

// onkeypress="return quitarComilla(event)"
function quitarComilla(e)
{   key=(document.all) ? e.keyCode : e.which;
  if ((key==39)||(key==34))
   {  alert("No se pueden ingresar comillas, no se guardaran los datos");
      return false;
   }
   return true;
}

