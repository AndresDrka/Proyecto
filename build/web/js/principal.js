$(document).ready(function () {
  $('#producto').DataTable(
    {
      dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    }
  );
  
});

function editar() {
  swal("Correcto!", "La información ha sido editada!", "success")
}


function eliminar() {
  swal({
    title: "Esta seguro?",
    text: "Una vez borrado, perdera toda la información!",
    icon: "warning",
    buttons: true,
    dangerMode: true,
  })
  .then((willDelete) => {
    if (willDelete) {
      swal("Poof! La información ha sido borrada!", {
        icon: "success",
      });
    } else {
      swal("Tu información esta segura!");
    }
  });
}

function agregar(){

  var idproducto = document.getElementById('idproducto').value;
  var nombrepro= document.getElementById('nombre-producto').value;
  var Descripción= document.getElementById('Descripción-producto').value;
  var cantidad= document.getElementById('cantidad-producto').value;
  var fecha= document.getElementById('Fecha-producto').value;
  var estado= document.getElementById('Estado-producto').value;
  if(idproducto!="" || nombrepro!=""||Descripción!=""||cantidad!=""||fecha!=""||estado!=""){
    console.log(idproducto),
    console.log(nombrepro),
    console.log(Descripción),
    console.log(cantidad),
    console.log(fecha),
    console.log(estado)
    swal({
      title: "Agregado",
      text: "Producto agregado exitosamente!",
      icon: "success",
      button: "Aceptar!",
    });
      
  }else{
    swal({
      title: "Incorrecto",
      text: "Los datos no corresponden o hay campos vacíos!",
      icon: "error",
      button: "Aceptar!",
    });
      
  }

}

function registrarUsuario(){

  var idusuario = document.getElementById('idusuario').value;
  var nombre= document.getElementById('nombre').value;
  var apellido= document.getElementById('apellido').value;
  var cargo= document.getElementById('inputState').value;
  var fechaU= document.getElementById('Fecha').value;
  var estadoU= document.getElementById('Estado').value;
  if(idusuario !="" || nombre !=""||apellido !=""||cargo !=""||fechaU !=""||estadoU !=""){
    console.log(idusuario),
    console.log(nombre),
    console.log(apellido),
    console.log(cargo),
    console.log(fechaU),
    console.log(estadoU),
    alert('incorrecto'),
      swal({
          title: "Incorrecto",
          text: "Los datos no corresponden o hay campos vacíos!",
          icon: "error",
          button: "Aceptar!",
        });
  }else{
    alert('correcto'),
      swal({
          title: "Agregado",
          text: "Producto agregado exitosamente!",
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