package main.java.controllers;

import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;
import main.java.poi.*;

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
    get("/inicio", poi::arrancar , engine);
    post("/POIs/calcularDistancia", poi::calcularDistancia , engine);
    get("/POIs/Distancia", poi::distancia , engine);
    get("/POIs/Invalido", poi::invalido , engine);
  }


}
