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
    get("/POIs", poi::nuevo, engine);
    post("/busquedaPOI", poi::buscar, engine);
    get("/paginaBusqueda", poi::busqueda, engine);
    get("/configurarAcciones", poi::nuevaAccion, engine);
    get("/historialBusquedas", poi::historial, engine);
    post("/buscarEnHistorial", poi::buscarEnHistorial, engine);
    get("/gh", poi::generarHistorial, engine);
    get("/calcularDistancia", poi::calcularDistancia , engine);
    get("/calcularDistanciaAPOI", poi::calcularDistanciaAPOI , engine);
    post("/POIs/calcularDistancia", poi::calcularDistanciaEntre2PoisDados , engine);
    post("/POIs/calcularDistanciaAPOI", poi::calculoDeDistanciaAPOI , engine);
    post("/Valido", poi::valido, engine);
    get("/POIs/resultadoDistancia", poi::resultadoDistancia, engine);
    get("/POIs/Invalido", poi::invalido , engine);
    get("/verificarDisponibilidadDePOI", poi::disponible , engine);
    post("/POIs/calcularDisponibilidad", poi::calcularDisponibilidad , engine);
    get("/POIs/resultadoDisponibilidad", poi::resultadoDisponibilidad , engine);
  }


}
