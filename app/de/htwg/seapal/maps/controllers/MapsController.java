package de.htwg.seapal.maps.controllers;

import java.awt.Point;

import play.Logger;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.htwg.seapal.boat.controllers.IBoatController;
import de.htwg.seapal.common.observer.Observable;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.person.controllers.IPersonController;

/**
 * The controller of the maps component.
 * @author Benjamin
 */
@Singleton
public class MapsController extends Observable implements IMapsController {

	private IMaps maps;
	private IMapsDatabase database;
	private IBoatController boatController;
	private IPersonController personController;
	
	@Inject
	public MapsController(Injector injector, IMapsDatabase db, IBoatController boatController, IPersonController personController) {
		this.database = db;
		this.maps = db.load();
		
		if (this.maps == null) {
			this.maps = injector.getInstance(IMaps.class);
			Logger.info("New MapsModel injected.");
		}
		
		this.boatController = boatController;
		this.personController = personController;
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
