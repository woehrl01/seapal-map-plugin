package de.htwg.seapal.maps.controllers;

import java.util.Set;

import play.Routes;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.inject.Inject;

import de.htwg.seapal.maps.views.web.hooks.MenuBarHook;
import de.htwg.seapal.maps.views.html.web.*;

public class PlayMapsController extends Controller {
  
	@Inject
	private IMapsController mapsController;
	
	@Inject
	private Set<MenuBarHook> tableHooks;
	
    public Result index() {
    	return ok(index.render(mapsController.getTestString(), tableHooks));
    }
  
    public Result test() {
    	return ok(index.render("TEST PAGE", tableHooks));
    }
    
    public Result seamap(){
		return ok(seamap.render());
	}
    
    public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	      Routes.javascriptRouter("jsRoutes",
	        // Routes
	    	// Application
	    	de.htwg.seapal.maps.controllers.routes.javascript.PlayMapsController.index(),
	    	de.htwg.seapal.maps.controllers.routes.javascript.PlayMapsController.test()
	      )
	    );
	}
}
