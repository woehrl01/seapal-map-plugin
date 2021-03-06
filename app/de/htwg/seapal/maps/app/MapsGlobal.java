package de.htwg.seapal.maps.app;


import java.text.ParseException;
import java.util.Locale;
import java.util.Set;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.api.PlayException;
import play.api.templates.Html;
import scala.collection.mutable.StringBuilder;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.common.modules.ReflectionModule;
import de.htwg.seapal.common.plugin.HookHandler;
import de.htwg.seapal.common.plugin.HookRegistry;
import de.htwg.seapal.common.plugin.Initializable;
import de.htwg.seapal.maps.app.module.MapsImplModule;
import de.htwg.seapal.maps.app.module.MapsTuiModule;
import de.htwg.seapal.maps.models.MapsPositionState;
import play.data.format.*;

public class MapsGlobal extends GlobalSettings{

	private static Injector INJECTOR = createInjector();
	private static HookRegistry registry = INJECTOR.getInstance(HookRegistry.class);
	
	
	public static Injector createInjector() {
		return Guice.createInjector(
				new MapsImplModule(),
				//new PersonDemoImplModule(),
				//new BoatMockModule(),
				//new TripDemoImplModule(),
				new MapsTuiModule());
	}
	
	@Override
	public <A> A getControllerInstance(Class<A> controllerClass)
			throws Exception {

		return INJECTOR.getInstance(controllerClass);
	}

	@Override
	public void onStart(Application app) {
		String message = "{\"message\": \"Hello this is a message. A little message. The message is made by MapsGlobal/onStart\"}";
		
		TestActor.startUp(message);
		//CouchDBActor.startUp();
		
		initComponents();

		Logger.info("Maps app has started");
		
    	// Register Formater
		Formatters.register(MapsPositionState.class, new Formatters.SimpleFormatter<MapsPositionState>() {
			
			@Override
			public MapsPositionState parse(String input, Locale l) throws ParseException {
				
				Logger.info("formater parsed");
				return MapsPositionState.valueOf(input);
			}

			@Override
			public String print(MapsPositionState input, Locale l) {
				Logger.info("outprinter");
				return input.toString();
			}
		});
	}
	
	private void initComponents() {
		AppInitilizer starter = INJECTOR.getInstance(AppInitilizer.class);
		starter.init();
	}

	@Override
	public void onStop(Application app) {
		Logger.info("Maps app shutdown...");
	}
}
