package de.htwg.seapal.maps.controllers;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.RemoteException;


import de.htwg.seapal.common.observer.IObservable;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;

public interface IMapsController extends IObservable, Remote {
	
	/**
	 * Gets whether the menu is visible.
	 * @return TRUE, if the menu is visible
	 * @throws RemoteException
	 */
	boolean getMenuVisible() throws RemoteException;
	String getTestString() throws RemoteException;
	
	/**
	 * Hides the maps menu.
	 */
	void hideMenu() throws RemoteException;
	void addWaypoint(Point position) throws RemoteException;
	
	/**
	 * Shows the maps menu.
	 */
	void showMenu() throws RemoteException;
	void setBoatPosition(Point position) throws RemoteException;
	
	/**
	boolean getMenuVisible() throws RemoteException;
	 * Gets the maps position state.
	 * @return The maps position state.
	 */
	MapsMenuPositionState getMenuPositionState() throws RemoteException;
	
	/**
	void showMenu() throws RemoteException;
	 * Sets the position state of the menu.
	 * @param menuPositionState The menu position state.
	 */
	void setMenuPositionState(MapsMenuPositionState menuPositionState) throws RemoteException;
	
	/**
	 * Gets the position state of the map.
	 * @return The maps position state.
	 */
	MapsPositionState getPositionState() throws RemoteException;
	
	/**
	 * Sets the maps position state.
	 * @param positionState The maps position state.
	 */
	void setPositionState(MapsPositionState positionState) throws RemoteException;
	
	/**
	 * Gets the maps center position.
	 * @return
	 */
	Point getPosition() throws RemoteException;
	
	/**
	 * Sets the maps center position
	 * @param position The position where the maps center should be located.
	 * @throws IllegalStateException If getPositionState() is not MapsPositionState.FIXED.
	 */
	void setPosition(Point position) throws IllegalStateException, RemoteException;
	
	/**
	 * Gets the maps type.
	 * @return The current maps type.
	 */
	MapsType getType() throws RemoteException;
	
	/**
	 * Sets the preferred maps type.
	 * @param type The maps type.
	 */
	void setType(MapsType type) throws RemoteException;
}
