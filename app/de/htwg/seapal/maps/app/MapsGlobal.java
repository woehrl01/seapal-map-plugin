package de.htwg.seapal.maps.app;


import java.util.Set;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.api.templates.Html;
import scala.collection.mutable.StringBuilder;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.boat.app.BoatMockModule;
import de.htwg.seapal.common.modules.ReflectionModule;
import de.htwg.seapal.common.plugin.HookHandler;
import de.htwg.seapal.common.plugin.HookRegistry;
import de.htwg.seapal.common.plugin.Initializable;
import de.htwg.seapal.maps.app.module.MapsImplModule;
import de.htwg.seapal.person.app.PersonDemoImplModule;
import de.htwg.seapal.trip.app.TripDemoImplModule;

public class MapsGlobal extends GlobalSettings{

	private static Injector INJECTOR = createInjector();
	private static HookRegistry registry = INJECTOR.getInstance(HookRegistry.class);
	
	
	public static Injector createInjector() {
		return Guice.createInjector(
				new MapsImplModule(), new PersonDemoImplModule(),
				new BoatMockModule(), new TripDemoImplModule());
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
		CouchDBActor.startUp();
		
		initComponents();

		Logger.info("Maps app has started");
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
