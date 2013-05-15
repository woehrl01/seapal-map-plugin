package de.htwg.seapal.maps.controllers;

import java.awt.Point;
import java.rmi.RemoteException;

import de.htwg.seapal.common.observer.IObservable;

public interface IMapsController extends IObservable {
	
	/**
	 * Simple test method just for verification that the controller is injected and works.
	 * TODO: remove this method
	 * @return Returns a test string.
	 */
	String getTestString() throws RemoteException;
	
	/**
	 * Adds a waypoint at the specified position.
	 * @param position The waypoint position.
	 */
	void addWaypoint(Point position) throws RemoteException;
	
	/**
	 * Sets the position of the displayed boat.
	 * @param position The boat position.
	 */
	void setBoatPosition(Point position) throws RemoteException;
	
	boolean getMenuVisible() throws RemoteException;
	
	void showMenu() throws RemoteException;
	
	void hideMenu() throws RemoteException;
}
