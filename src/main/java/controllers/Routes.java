package controllers;

import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

public static void main(String[] args) {
    System.out.println("Iniciando servidor");

    POIController poi = new POIController();
    HomeController home = new HomeController();
    HistorialController historial = new HistorialController();
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    ReporteFechaController reportef = new ReporteFechaController();
    ReporteUsuarioController reporteu = new ReporteUsuarioController();
    port(8081);
    staticFileLocation("/public");
    
    get("/", home::mostrar, engine);
    get("/index.html", (request, response) -> {
      response.redirect("/");
      return null;
    });

    get("/Acciones", poi::opciones, engine);
    post("/Valido", poi::valido, engine);
    get("/Invalido", poi::invalido , engine);
    
    //Busqueda
    post("/getDetalles", poi::generarDetalles, engine);
    get("/busquedaPOI", poi::buscar, engine);
    get("/paginaBusqueda", poi::busqueda, engine);
    //Acciones
    get("/configurarAcciones", poi::nuevaAccion, engine);
    //Historial
    get("/historialBusquedas", historial::historial, engine);
    post("/buscarEnHistorial", historial::buscarEnHistorial, engine);
    get("/generarHistorial", historial::generarHistorial, engine);
    //ReporteFechas
    get("/reportarFecha", reportef::reporteFecha, engine );
    post("/buscarPorFecha", reportef::buscarEnHistorialFechas, engine );
   // get("/generarReporte", reportef::generarReporte, engine);
    
    //ReporteUsuarios
    get("/reportarUsuario", reporteu::reporteUsuario, engine);
    post("/buscarPorUsuario", reporteu::buscarEnHistorialUsuario, engine);
    //get("/generarReporteUsuario", reporteu::generarReporte, engine);
    //Distancia
    get("/calcularDistancia", poi::calcularDistancia , engine);
    get("/calcularDistanciaAPOI", poi::calcularDistanciaAPOI , engine);
    post("/POIs/calcularDistancia", poi::calcularDistanciaEntre2PoisDados , engine);
    post("/POIs/calcularDistanciaAPOI", poi::calculoDeDistanciaAPOI , engine);
    //post("/Valido", poi::valido, engine);
    //registrar usuario
    get("/registroUsuario", poi::registroUsuario, engine);
    post("/registro", poi::registro, engine);
    get("/modificarUser", poi::modificarUser, engine);
    get("/modificarUsuario", poi::modificarUsuario, engine);
    post("/actualizarUsuario", poi::actualizarUsuario, engine);
    get("/InvalidoSesion", poi::invalidaSesion, engine);
    //get("/Invalido", poi::invalido , engine);
    get("/POIs/resultadoDistancia", poi::resultadoDistancia, engine);
    //Disponibilidad
    get("/verificarDisponibilidadDePOI", poi::disponible , engine);
    post("/POIs/calcularDisponibilidad", poi::calcularDisponibilidad , engine);
    get("/POIs/resultadoDisponibilidad", poi::resultadoDisponibilidad , engine);
    
    //detalles
    
    post("/generarDetalles",poi::generarDetalles,engine);
  }
}
