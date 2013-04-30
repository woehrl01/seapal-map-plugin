package de.htwg.seapal.maps.controllers;

import java.util.UUID;

import org.codehaus.jackson.JsonNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import de.htwg.seapal.maps.models.SeapalWebSockets;

public class SocketController extends Controller {
	
	public static Result index() {
		return ok(de.htwg.seapal.maps.views.html.websockets.render());
	}
	
	/**
	 * Sends a String to all registered Web Socket Clients.
	 * 
	 * @param message
	 * @return
	 */
	public static Result sendToClients( String message ) {	
		SeapalWebSockets.sendJsonObjectToClient( Json.parse( message ) );
		
		return ok();
	}
	
	public static WebSocket<JsonNode> open() {
		return new WebSocket<JsonNode>() {
			@Override
			public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) {

				try {
					SeapalWebSockets.register(UUID.randomUUID().toString(), in, out);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
	}
}
