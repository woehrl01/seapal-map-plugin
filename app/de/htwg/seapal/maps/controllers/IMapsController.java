package de.htwg.seapal.maps.controllers;

import java.awt.Point;
import de.htwg.seapal.common.observer.IObservable;

public interface IMapsController extends IObservable {
	/**
	 * Adds a waypoint at the specified position.
	 * @param position The waypoint position.
	 */
	void addWaypoint(Point position);
	
	/**
	 * Sets the position of the displayed boat.
	 * @param position The boat position.
	 */
	void setBoatPosition(Point position);
}
