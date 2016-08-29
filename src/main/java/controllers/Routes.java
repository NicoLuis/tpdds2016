package controllers;

import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

public static void main(String[] args) {
    System.out.println("Iniciando servidor");

    POIController poi = new POIController();
    HomeController home = new HomeController();
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
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
    post("/busquedaPOI", poi::buscar, engine);
    get("/paginaBusqueda", poi::busqueda, engine);
    //Acciones
    get("/configurarAcciones", poi::nuevaAccion, engine);
    //Historial
    get("/historialBusquedas", poi::historial, engine);
    post("/buscarEnHistorial", poi::buscarEnHistorial, engine);
    get("/generarHistorial", poi::generarHistorial, engine);
    //Distancia
    get("/calcularDistancia", poi::calcularDistancia , engine);
    get("/calcularDistanciaAPOI", poi::calcularDistanciaAPOI , engine);
    post("/POIs/calcularDistancia", poi::calcularDistanciaEntre2PoisDados , engine);
    post("/POIs/calcularDistanciaAPOI", poi::calculoDeDistanciaAPOI , engine);
    get("/POIs/resultadoDistancia", poi::resultadoDistancia, engine);
    //Disponibilidad
    get("/verificarDisponibilidadDePOI", poi::disponible , engine);
    post("/POIs/calcularDisponibilidad", poi::calcularDisponibilidad , engine);
    get("/POIs/resultadoDisponibilidad", poi::resultadoDisponibilidad , engine);
  }


}
