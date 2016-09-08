package controllers;

import org.mockito.exceptions.misusing.MissingMethodInvocationException;

import bases.RepoTerminales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

  public ModelAndView mostrar(Request request, Response response) {
	  RepoTerminales.GetInstancia().setBooleanAdmin(false);
	 // Conexion miconex = new Conexion();
    return new ModelAndView(null, "home.hbs");
  }

}