package de.htwg.seapal.maps.app;

import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.changes.ChangesCommand;
import org.ektorp.changes.ChangesFeed;
import org.ektorp.changes.DocumentChange;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import play.Logger;
import play.libs.Akka;
import play.libs.Json;
import scala.concurrent.duration.Duration;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.actor.UntypedActor;
import de.htwg.seapal.maps.models.SeapalWebSockets;

public class CouchDBActor extends UntypedActor {

	static Cancellable instance;
	
	@Override
	public void onReceive(Object message) throws Exception {
		HttpClient httpClient = new StdHttpClient.Builder().build();
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		
		CouchDbConnector db = dbInstance.createConnector("positions", true);

		ChangesCommand cmd = new ChangesCommand.Builder().includeDocs(true).build();

		ChangesFeed feed = db.changesFeed(cmd);
		// !instance.isCancelled() && 
		while (feed.isAlive()) {
		    DocumentChange change = feed.next();
		    Logger.info(change.getDocAsNode().toString());
		    SeapalWebSockets.sendJsonObjectToClient( change.getDocAsNode() );
		}
	}
	
	public static void startUp(){
		if(instance == null || instance.isCancelled()){
			instance = Akka.system().scheduler().scheduleOnce(Duration.create(50, TimeUnit.MILLISECONDS),
						Akka.system().actorOf(new Props(CouchDBActor.class)),
						"startup", 
						Akka.system().dispatcher());
		}
	}
	
	public static void stop(){
		instance.cancel();
	}

}
