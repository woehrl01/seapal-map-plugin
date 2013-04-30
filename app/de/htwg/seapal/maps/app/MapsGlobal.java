package de.htwg.seapal.maps.app;

import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.JsonNode;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Akka;
import play.libs.Json;
import scala.concurrent.duration.Duration;

import akka.actor.Props;
import akka.actor.UntypedActor;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.boat.app.BoatMockModule;
import de.htwg.seapal.common.modules.ReflectionModule;
import de.htwg.seapal.maps.app.module.MapsImplModule;
import de.htwg.seapal.maps.models.SeapalWebSockets;
import de.htwg.seapal.person.app.PersonDemoImplModule;

public class MapsGlobal extends GlobalSettings {

	private static Injector INJECTOR;

	public static Injector createInjector() {
		return Guice.createInjector(new ReflectionModule(),
				new MapsImplModule(), new PersonDemoImplModule(),
				new BoatMockModule());
	}

	@Override
	public <A> A getControllerInstance(Class<A> controllerClass)
			throws Exception {
		if (INJECTOR == null) {
			INJECTOR = createInjector();
		}

		return INJECTOR.getInstance(controllerClass);
	}

	@Override
	public void onStart(Application app) {
		String message = "{\"message\": \"Hello this is a message. A little message. The message is made by MapsGlobal/onStart\"}";
		
		Akka.system()
				.scheduler()
				.schedule(Duration.create(0, TimeUnit.MILLISECONDS),
						Duration.create(30, TimeUnit.SECONDS),
						Akka.system().actorOf(new Props(TestActor.class)),
						message, 
						Akka.system().dispatcher());

		Logger.info("Maps app has started");
	}

	public static class TestActor extends UntypedActor {

		@Override
		public void onReceive(Object message) throws Exception {
			SeapalWebSockets.sendJsonObjectToClient( Json.parse( (String) message ) );
		}

	}

	@Override
	public void onStop(Application app) {
		Logger.info("Maps app shutdown...");
	}
}
