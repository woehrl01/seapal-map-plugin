package de.htwg.seapal.maps.controllers;

import java.awt.Point;
import java.rmi.RemoteException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.Routes;
import play.api.templates.Html;
import play.data.Form;
import play.data.format.Formatters;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.Logger;
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
    
    public Result seamapSettingsAsJson() throws RemoteException{
    	IMaps mapsSettings = mapsController.getMapsSettings();
    	ObjectNode result = Json.newObject();
    	result.put("id", mapsSettings.getId());
	    result.put("menuVisible", mapsSettings.getMenuVisible());
	    result.put("menuPositionState", mapsSettings.getMenuPositionState().toString());
	    result.put("positionState", mapsSettings.getPositionState().toString());
	    result.put("type", mapsSettings.getType().toString());
	    ObjectNode pos = Json.newObject();
	    pos.put("x", mapsSettings.getPosition().x);
	    pos.put("y", mapsSettings.getPosition().y);
	    result.put("position", pos);
	    return ok(result);
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
    	
    	return ok(seamapsettings.render(mapsSettings, mapsForm.fill((Maps) mapsSettings), menuPositionStates, positionStates, mapsTypes));
    }
    
    public Result saveSeamapSettings() throws RemoteException {
    	
    	Logger.info("Call: saveSeamapSettings");
    	Form<Maps> filledForm = mapsForm.bindFromRequest();
    	if (filledForm.hasErrors()) {
    		Logger.error(filledForm.errorsAsJson().toString());
    	}
    	
    	//save settings here
    	Maps mapsData = filledForm.get();
    	mapsController.setMapsSettings(mapsData);
    	
    	//return ok(index.render(filledForm.get().getPositionState().toString(), htmlRenderHooks));
    	return redirect(de.htwg.seapal.maps.controllers.routes.PlayMapsController.seamapSettings());
    }
    
    /**
     * REST API to set the settings value.
     * <p>
     * curl -X POST 127.0.0.1:9000/seamapsettings/json -H "application/json" -d '{\"menuVisible\":true,\"positionState\":\"FIXED\"}'
     * </p>
     * @return The result as Json.
     * @throws RemoteException RMI exception
     */
    public Result saveSeamapSettingsFromJson() throws RemoteException {
    	boolean hasErrors = false;
    	JsonNode json = request().body().asJson();
    	ObjectNode result = Json.newObject();
    	
    	JsonNode menuVisible = json.findPath("menuVisible");
    	if (!menuVisible.isMissingNode()) {
    		if (menuVisible.getBooleanValue()) {
    			mapsController.showMenu();
    		} else {
    			mapsController.hideMenu();
    		}
    	}
    	
    	JsonNode menuPositionState = json.findPath("menuPositionState");
    	
    	if (!menuPositionState.isMissingNode()) {
    		MapsMenuPositionState state = MapsMenuPositionState.valueOf(menuPositionState.getTextValue());
    		if (state != null) {
    			mapsController.setMenuPositionState(state);
    		} else {
    			hasErrors = true;
    		}
    	}
    	
    	JsonNode positionState = json.findPath("positionState");
    	if (!positionState.isMissingNode()) {
    		MapsPositionState state = MapsPositionState.valueOf(positionState.getTextValue());
    		if (state != null) {
    			mapsController.setPositionState(state);
    		} else {
    			hasErrors = true;
    		}
    	}
    	
    	JsonNode type = json.findPath("type");
    	if (!type.isMissingNode()) {
    		MapsType t = MapsType.valueOf(type.getTextValue());
    		if (t != null) {
    			Logger.info("SET TYPE: "+ t.toString());
    			mapsController.setType(t);
    		} else {
    			hasErrors = true;
    		}
    	}
    	
    	JsonNode position = json.findPath("position");
    	if (!position.isMissingNode()) {
    		Point newPos = new Point();
    		newPos.x = position.findPath("x").getIntValue();
    		newPos.y = position.findPath("x").getIntValue();
    		mapsController.setPosition(newPos);
    	}
    	
    	if(hasErrors) {
    	    result.put("status", "error");
    	    return badRequest(result);
    	} else {
    	    result.put("status", "ok");
    	    return ok(result);
    	}
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
	    	de.htwg.seapal.maps.controllers.routes.javascript.PlayMapsController.seamapSettingsAsJson(),
	    	de.htwg.seapal.maps.controllers.routes.javascript.PlayMapsController.saveSeamapSettingsFromJson(),
	    	de.htwg.seapal.maps.controllers.routes.javascript.PlayMapsController.saveSeamapSettings()
	      )
	    );
	}
}
