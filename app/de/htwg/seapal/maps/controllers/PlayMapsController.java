package de.htwg.seapal.maps.controllers;

import java.util.List;
import java.util.Set;

import play.Routes;
import play.api.templates.Html;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.inject.Inject;

import de.htwg.seapal.common.plugin.HookHandler;
import de.htwg.seapal.common.plugin.HookRegistry;
import de.htwg.seapal.maps.views.web.hooks.HtmlRenderHook;
import de.htwg.seapal.maps.views.web.hooks.MenuBarHook;
import de.htwg.seapal.maps.views.html.web.*;

public class PlayMapsController extends Controller {
  
	@Inject
	private IMapsController mapsController;
	
	@Inject
	private Set<MenuBarHook> menuBarHooks;
	
	@Inject
	private Set<HtmlRenderHook> htmlRenderHooks;
	
	@Inject(optional = true)
	private HookRegistry hookRegistry;
	
    public Result index() {
    	return ok(index.render(mapsController.getTestString(), htmlRenderHooks));
    }
  
    public Result test() {
    	return ok(index.render("TEST PAGE", htmlRenderHooks));
    }
    
    public Result seamap(){
    	Set<HookHandler<Html, Object>> hooks = hookRegistry.getHooks("menu.show", Html.class, Object.class);

    	
    	
		return ok(seamap.render("Seamaps", hooks));
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
