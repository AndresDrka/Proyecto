
$(document).ready( function () {

    var chart = new CanvasJS.Chart("chartContainer", {
        theme: "light1", // "light2", "dark1", "dark2"
        animationEnabled: true, // change to true
        resize:true,		
        title: {
            text: "Producci\xf3n"
        },exportEnabled: true,
        data: [
            {
                // Change type to "bar", "area", "spline", "pie",etc.
                type: "spline",
                dataPoints: [
                    { label: "dia 1", y: 10 },
                    { label: "dia 5", y: 15 },
                    { label: "dia 10", y: 25 },
                    { label: "dia 15", y: 30 },
                    { label: "dia 20", y: 28 },
                    { label: "dia 25", y: 28 }
                ]
            }
        ]
    });
    chart.render();

    var wii = new CanvasJS.Chart("contenedor2", {
        theme: "dark1", // "light2", "dark1", "dark2"
        animationEnabled: true, // change to true	
        resize:true,	
        title: {
            text: "Inventario Productos"
        },exportEnabled: true,
        data: [
            {
                // Change type to "bar", "area", "spline", "pie",etc.
                type: "pie",
                dataPoints: [
                    { label: "Jalape\xF1o rojo salsa", y: 10 },
                    { label: "Jalape\xF1o rojo encurtido", y: 15 },
                    { label: "Jalape\xF1o amarillo salsa", y: 25 },
                    { label: "Jalape\xF1o amarillo salsa", y: 30 },
                    { label: "Jalape\xF1o verde salsa", y: 28 },
                    { label: "Jalape\xF1o verde encurtido", y: 28 }
                ]
            }
        ]
    });
    wii.render();
});


    

