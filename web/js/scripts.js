function entrada(){
    var usuario = document.getElementById('usuario').value;
    var contraseña= document.getElementById('contraseña').value;
    if(usuario=='admin@correo.com' && contraseña =='123456'){
      //  alert('felicitaciones' );
      window.location.href='si/1-admin/html/index-admin.html';
    }else if(usuario== 'cliente@correo.com' && contraseña =='123456'){
        window.location.href='si/2-cliente/cliente.html'
    }else if(usuario=='jefepod@correo.com' && contraseña=='123456'){
        window.location.href='si/jefe de produccion/html/index-jefe.html'
    }
    
    else {
        swal({
            title: "Incorrecto",
            text: "Los datos no corresponden!",
            icon: "error",
            button: "Aceptar!",
          });
    }
}

function enviar(){
    var nombre = document.getElementById('nombreCom').value;
    var apellido= document.getElementById('apellidoCom').value;
    var correo= document.getElementById('correoCom').value;
    var asunto= document.getElementById('AsuntoCom').value;
    var comentario= document.getElementById('Comentario').value;
    if(nombre!=null || apellido!=null||correo!=null||asunto!=null||comentario!=null){
        swal({
            title: "Incorrecto",
            text: "Los datos no corresponden o hay campos vacíos!",
            icon: "error",
            button: "Aceptar!",
          });
    }else{
        swal({
            title: "Correcto",
            text: "El comentario ha sido enviado con exito!",
            icon: "success",
            button: "Aceptar!",
          });
    }
}

function registro(){

    var nombreReg = document.getElementById('nombreReg').value;
    var apellidoReg= document.getElementById('apellidoReg').value;
    var correoReg= document.getElementById('correoReg').value;
    var cedulaReg= document.getElementById('cedulaReg').value;
    var contraseñaReg= document.getElementById('contraseñaReg').value;
    var ConcontraseñaReg= document.getElementById('contraseñaConReg').value;
    if(nombreReg!=null || apellidoReg!=null||correoReg!=null||cedulaReg!=null||contraseñaReg!=null||ConcontraseñaReg!=null){
        swal({
            title: "Incorrecto",
            text: "Los datos no corresponden o hay campos vacíos!",
            icon: "error",
            button: "Aceptar!",
          });
    }else{
        swal({
            title: "Registrado",
            text: "Usuario registrado con exito!",
            icon: "success",
            button: "Aceptar!",
          });
    }

}

// validacion solo texto
function Solo_Texto(e){
    var code;
    if(!e) var e= window.event;
    if(e.keyCode) code=e.keyCode;
    else if(e.which)code=e.which;
    var character =String.fromCharCode(code);
    var AllowRegex = /^[\ba-zA-Z\s-]$/;
    if(AllowRegex.test(character)) return true;
    return false;
}

// validacion solo numero
function solonumeros(e){
    key=e.keyCode ||e.which;
    teclado=String.fromCharCode(key);
    numero="0123456789";
    especiales="8-37-38-46";//array
    teclado_especial=false;

    for( var i in especiales){
        if(key==especiales[i]){
            teclado_especial=true;
        }
    }
    if(numero.indexOf(teclado)==-1 && !teclado_especial){
        return false;
    }
}