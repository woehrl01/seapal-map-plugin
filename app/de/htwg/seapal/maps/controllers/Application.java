package de.htwg.seapal.maps.controllers;

import play.*;
import play.mvc.*;

import de.htwg.seapal.maps.views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Index page of MAPS"));
    }
  
}
