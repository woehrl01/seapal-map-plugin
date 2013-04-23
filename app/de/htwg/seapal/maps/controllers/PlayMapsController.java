package de.htwg.seapal.maps.controllers;

import com.google.inject.Inject;

import play.*;
import play.mvc.*;

import de.htwg.seapal.maps.views.html.*;

public class PlayMapsController extends Controller {
  
	@Inject
	private IMapsController mapsController;
	
    public Result index() {
        //return ok(index.render("Index page of MAPS"));
    	return ok(index.render(mapsController.getTestString()));
    }
  
    public Result test() {
        return ok(index.render("HELP PAGE"));
    }
}
