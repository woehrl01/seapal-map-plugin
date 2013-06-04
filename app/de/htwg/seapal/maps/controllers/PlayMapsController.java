package de.htwg.seapal.maps.controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import play.Routes;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.$less$colon$less;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableView;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;
import views.html.defaultpages.error;

import com.google.inject.Inject;

import de.htwg.seapal.common.plugin.HookHandler;
import de.htwg.seapal.common.plugin.HookRegistry;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;
import de.htwg.seapal.maps.models.impl.Maps;
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
	
	Form<Maps> mapsForm = play.data.Form.form(Maps.class);
	
	@Inject(optional = true)
	private HookRegistry hookRegistry;
	
    public Result index() {
    	try {
			return ok(index.render(mapsController.getType().toString(), htmlRenderHooks));
		} catch (RemoteException e) {
			return badRequest(e.getMessage());
		}
    }
    
    public Result seamap(){
    	Set<HookHandler<Html, Object>> hooks = hookRegistry.getHooks("menu.show", Html.class, Object.class);

		return ok(seamap.render("Seamaps", hooks));
	}
    
    public Result seamapSettings() throws RemoteException{
    	IMaps mapsSettings = mapsController.getMapsSettings();
    	
    	List<String> menuPositionStates = new ArrayList<String>();
    	for(MapsMenuPositionState value : MapsMenuPositionState.values()) {
    		menuPositionStates.add(value.toString());
    	}
    	
    	List<String> positionStates = new ArrayList<String>();
    	for(MapsPositionState value : MapsPositionState.values()) {
    		positionStates.add(value.toString());
    	}
    	
    	List<String> mapsTypes = new ArrayList<String>();
    	for(MapsType value : MapsType.values()) {
    		mapsTypes.add(value.toString());
    	}
    	
    	return ok(seamapsettings.render(mapsSettings, mapsForm, menuPositionStates, positionStates, mapsTypes));
    }
    
    public Result saveSeamapSettings() {
    	Form<Maps> filledForm = mapsForm.bindFromRequest();
    	//return ok(seamapsettings.render(mapsSettings));
    	return ok();
    }
    
    public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	      Routes.javascriptRouter("jsRoutes",
	        // Routes
	    	// Application
	    	de.htwg.seapal.maps.controllers.routes.javascript.PlayMapsController.index(),
	    	de.htwg.seapal.maps.controllers.routes.javascript.PlayMapsController.seamap(),
	    	de.htwg.seapal.maps.controllers.routes.javascript.PlayMapsController.seamapSettings(),
	    	de.htwg.seapal.maps.controllers.routes.javascript.PlayMapsController.saveSeamapSettings()
	      )
	    );
	}
}
