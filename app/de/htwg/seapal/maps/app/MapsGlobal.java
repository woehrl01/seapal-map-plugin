package de.htwg.seapal.maps.app;


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

public class MapsGlobal extends GlobalSettings implements Initializable {

	private static Injector INJECTOR = createInjector();
	private static HookRegistry registry = INJECTOR.getInstance(HookRegistry.class);

	public static Injector createInjector() {
		return Guice.createInjector(new ReflectionModule(),
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
		
		initHooks(registry);

		Logger.info("Maps app has started");
	}
	
	@Override
	public void initHooks(HookRegistry registry) {
		registry.registerHook("menu.show", new HookHandler<Html, Object>(Html.class, Object.class){

			@Override
			public Html execute(Object nothing) {
				final StringBuilder builder = new StringBuilder();
				builder.append("test");

				return new Html(builder);
			}
		});
		
		registry.registerHook("menu.show", new HookHandler<Integer, Object>(Integer.class, Object.class){

			@Override
			public Integer execute(Object nothing) {


				return 34343;
			}
		});
		System.out.println("registered hooks");
	}


	@Override
	public void onStop(Application app) {
		Logger.info("Maps app shutdown...");
	}
}
