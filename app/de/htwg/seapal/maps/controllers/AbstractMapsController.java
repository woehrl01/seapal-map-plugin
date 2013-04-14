package de.htwg.seapal.maps.controllers;

import java.awt.Point;

import de.htwg.seapal.boat.controllers.IBoatController;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.util.observer.Observable;

public class AbstractMapsController extends Observable implements
		IMapsController {

	protected IMaps maps;
	protected IBoatController boatController;
	
	@Override
	public void addWaypoint(Point position) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setBoatPosition(Point position) {
		// TODO Auto-generated method stub
	}
}
