package de.htwg.seapal.maps.controllers;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.seapal.common.observer.Observable;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsFactory;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;
import de.htwg.seapal.maps.models.impl.Maps;

/**
 * The controller of the maps component.
 * @author Benjamin
 */
@Singleton
public class MapsController implements IMapsController {

	private IMapsDatabase database;
	private MapsFactory mapsFactory;
	
	@Inject
	public MapsController(MapsFactory mapsFactory, IMapsDatabase db) throws RemoteException {
		this.database = db;
		this.mapsFactory = mapsFactory;
		
		// RMI export
		Registry reg = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
		if ( reg == null){
			reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		
			IMapsController stub = (IMapsController)UnicastRemoteObject.exportObject(this, 0);
		
			reg.rebind("de.htwg.seapal.maps", stub);
		}
	}

	@Override
	public IMaps getMapsSettings() throws RemoteException {
		return loadFromDatabaseOrDefault();
	}
	
	@Override
	public void setMapsSettings(IMaps settings) throws RemoteException {
		database.save(settings);
	}

	@Override
	public boolean getMenuVisible() throws RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		return maps.getMenuVisible();
	}

	@Override
	public void showMenu() throws RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		maps.setMenuVisible(true);
		database.save(maps);
	}

	@Override
	public void hideMenu() throws RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		maps.setMenuVisible(false);
		database.save(maps);
	}


	@Override
	public MapsMenuPositionState getMenuPositionState() throws RemoteException{
		IMaps maps = loadFromDatabaseOrDefault();
		return maps.getMenuPositionState();
	}


	@Override
	public void setMenuPositionState(MapsMenuPositionState menuPositionState)
			throws RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		maps.setMenuPositionState(menuPositionState);
		database.save(maps);
	}


	@Override
	public MapsPositionState getPositionState() throws RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		return maps.getPositionState();
	}


	@Override
	public void setPositionState(MapsPositionState positionState)
			throws RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		maps.setPositionState(positionState);
		database.save(maps);
	}


	@Override
	public String getPosition() throws RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		return maps.getPosition();
	}


	@Override
	public void setPosition(String position) throws IllegalStateException,
			RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		maps.setPosition(position);
		database.save(maps);
	}


	@Override
	public MapsType getType() throws RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		return maps.getType();
	}


	@Override
	public void setType(MapsType type) throws RemoteException {
		IMaps maps = loadFromDatabaseOrDefault();
		maps.setType(type);
		database.save(maps);
	}
	
	private IMaps loadFromDatabaseOrDefault() {
		IMaps map = database.load();
		if(map == null){
			map = new Maps();
		}
		return map;
	}
}
