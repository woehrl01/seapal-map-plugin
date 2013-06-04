package de.htwg.seapal.maps.controllers;

import java.awt.Point;
import java.rmi.RemoteException;

import play.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.seapal.common.observer.Observable;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsFactory;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;

/**
 * The controller of the maps component.
 * @author Benjamin
 */
@Singleton
public class MapsController extends Observable implements IMapsController {

	private IMapsDatabase database;
	private MapsFactory mapsFactory;
	
	@Inject
	public MapsController(MapsFactory mapsFactory, IMapsDatabase db) throws RemoteException {
		this.database = db;
		this.mapsFactory = mapsFactory;
	}
	

	@Override
	public boolean getMenuVisible() throws RemoteException {
		IMaps maps = database.load();
		return maps.getMenuVisible();
	}

	@Override
	public void showMenu() throws RemoteException {
		IMaps maps = database.load();
		maps.setMenuVisible(true);
		database.save(maps);
		notifyObservers();
	}

	@Override
	public void hideMenu() throws RemoteException {
		IMaps maps = database.load();
		maps.setMenuVisible(false);
		database.save(maps);
		notifyObservers();
	}


	@Override
	public MapsMenuPositionState getMenuPositionState() throws RemoteException{
		IMaps maps = database.load();
		return maps.getMenuPositionState();
	}


	@Override
	public void setMenuPositionState(MapsMenuPositionState menuPositionState)
			throws RemoteException {
		IMaps maps = database.load();
		maps.setMenuPositionState(menuPositionState);
		database.save(maps);
		notifyObservers();
	}


	@Override
	public MapsPositionState getPositionState() throws RemoteException {
		IMaps maps = database.load();
		return maps.getPositionState();
	}


	@Override
	public void setPositionState(MapsPositionState positionState)
			throws RemoteException {
		IMaps maps = database.load();
		maps.setPositionState(positionState);
		database.save(maps);
		notifyObservers();
	}


	@Override
	public Point getPosition() throws RemoteException {
		IMaps maps = database.load();
		return maps.getPosition();
	}


	@Override
	public void setPosition(Point position) throws IllegalStateException,
			RemoteException {
		IMaps maps = database.load();
		maps.setPosition(position);
		database.save(maps);
		notifyObservers();
	}


	@Override
	public MapsType getType() throws RemoteException {
		IMaps maps = database.load();
		return maps.getType();
	}


	@Override
	public void setType(MapsType type) throws RemoteException {
		IMaps maps = database.load();
		maps.setType(type);
		database.save(maps);
		notifyObservers();
	}
}
