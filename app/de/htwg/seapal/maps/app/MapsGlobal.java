package de.htwg.seapal.maps.app;


import play.Application;
import play.GlobalSettings;
import play.Logger;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.boat.app.BoatMockModule;
import de.htwg.seapal.common.modules.ReflectionModule;
import de.htwg.seapal.maps.app.module.MapsImplModule;
import de.htwg.seapal.person.app.PersonDemoImplModule;

public class MapsGlobal extends GlobalSettings {

	private static Injector INJECTOR = createInjector();

	public static Injector createInjector() {
		return Guice.createInjector(new ReflectionModule(),
				new MapsImplModule(), new PersonDemoImplModule(),
				new BoatMockModule());
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

		Logger.info("Maps app has started");
	}

	@Override
	public void onStop(Application app) {
		Logger.info("Maps app shutdown...");
	}
}
