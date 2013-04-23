package de.htwg.seapal.maps.controllers;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;

import play.*;
import play.mvc.*;

import de.htwg.seapal.maps.views.html.*;
import de.htwg.seapal.maps.views.web.hooks.TableHook;

public class PlayMapsController extends Controller {
  
	@Inject
	private IMapsController mapsController;
	
	@Inject
	private Set<TableHook> tableHooks;
	
	
	/*@Inject(optional=true)
	public void setTableHooks(Set<TableHook> tableHooks){
		if(tableHooks != null){
			this.tableHooks = tableHooks;
		}
	}*/
	
    public Result index() {
        //return ok(index.render("Index page of MAPS"));
    	
    	return ok(index.render(mapsController.getTestString(), tableHooks));
    }
  
    public Result test() {
    	
        return ok(index.render("HELP PAGE", tableHooks));
    }
}
