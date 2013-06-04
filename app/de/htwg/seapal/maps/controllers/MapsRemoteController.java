package de.htwg.seapal.maps.controllers;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import play.Logger;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.htwg.seapal.boat.controllers.IBoatController;
import de.htwg.seapal.common.observer.Observable;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsFactory;
import de.htwg.seapal.person.controllers.IPersonController;

/**
 * The controller of the maps component.
 * @author Benjamin
 */
@Singleton
public class MapsRemoteController extends Observable implements IMapsRemoteController {

	private IMaps maps;
	private IMapsDatabase database;
	private IBoatController boatController;
	private IPersonController personController;
	private MapsFactory mapsFactory;
	
	@Inject
	public MapsRemoteController(Injector inject, MapsFactory mapsFactory, IMapsDatabase db, IBoatController boatController, IPersonController personController) {
		this.database = db;
		this.mapsFactory = mapsFactory;
		this.maps = db.load();
		
		if (this.maps == null) {
			this.maps = this.mapsFactory.create();
			Logger.info("New MapsModel injected.");
		}
		
		this.boatController = boatController;
		this.personController = personController;
		
		try {
			Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			IMapsRemoteController stub = (IMapsRemoteController)UnicastRemoteObject.exportObject(inject.getInstance(MapsRemoteController.class), 0);
			reg.rebind("de.htwg.seapal.maps", stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getTestString() {
		return "Yeeehaaaw!";
	}
	
	@Override
	public void addWaypoint(Point position) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setBoatPosition(Point position) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean getMenuVisible() {
		return maps.getMenuVisible();
	}

	@Override
	public void showMenu() {
		maps.showMenu();
		database.save(maps);
		notifyObservers();
	}

	@Override
	public void hideMenu() {
		maps.hideMenu();
		database.save(maps);
		notifyObservers();
	}
}
