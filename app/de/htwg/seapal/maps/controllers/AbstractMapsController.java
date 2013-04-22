package de.htwg.seapal.maps.controllers;

import java.awt.Point;

import de.htwg.seapal.boat.controllers.IBoatController;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.person.controllers.IPersonController;
import de.htwg.seapal.common.observer.Observable;

public class AbstractMapsController extends Observable implements IMapsController {

	protected IMaps maps;
	protected IBoatController boatController;
	protected IPersonController personController;
	protected IMapsDatabase mapsDatabase;
	
	@Override
	public void addWaypoint(Point position) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setBoatPosition(Point position) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getTestString() {
		return "Yeeehaaaw!";
	}
}
