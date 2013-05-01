package de.htwg.seapal.maps.app;

import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.JsonNode;

import play.libs.Akka;
import play.libs.Json;
import scala.concurrent.duration.Duration;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.actor.UntypedActor;
import de.htwg.seapal.maps.models.SeapalWebSockets;

public class TestActor extends UntypedActor {

	static Cancellable instance;
	
	@Override
	public void onReceive(Object message) throws Exception {
		JsonNode data = Json.parse( (String) message );
		
		SeapalWebSockets.sendJsonObjectToClient( data );
	}
	
	public static void startUp(String message){
		if(instance == null || instance.isCancelled()){
			instance = Akka.system().scheduler().schedule(Duration.create(0, TimeUnit.MILLISECONDS),
						Duration.create(5, TimeUnit.SECONDS),
						Akka.system().actorOf(new Props(TestActor.class)),
						message, 
						Akka.system().dispatcher());
		}
	}
	
	public static void stop(){
		instance.cancel();
	}

}
