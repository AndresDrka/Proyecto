$(document).ready(function () {
    $('.menu li:has(ul )').click(function (el) {
       

        if ($(this).hasClass('activado')) {
            $(this).toggleClass('activado');
            $(this).children('ul').slideUp();
        } else {
            $('.menu li ul').slideUp();
            $('.menu li').removeClass('activado');
            $(this).toggleClass('activado');
            $(this).children('ul').slideDown();

        }
    });

    $('.btn-menu').click(function () {
        $('.contenedor-menu .menu').slideToggle();

    });
    $(window).resize(function () {
        if ($(document).width() >768) {
            $('.contenedor-menu .menu').css({ 'dislay': 'block' });
        }
        if ($(document).width() < 768) {
            $('.contenedor-menu .menu').css({ 'dislay': 'none' });
            $('.menu li ul').slideUp();
            $('.menu li ').toggleClass('activado');
        }

    });
});