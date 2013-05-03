package de.htwg.seapal.maps.controllers;

import java.util.Set;

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
    	//web.index.render("HELP PAGE", tableHooks)
        return ok();
    }
}
