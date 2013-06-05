package de.htwg.seapal.maps.controllers.impl;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;
import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.api.Application;
import play.api.DefaultApplication;
import play.api.Mode;
import play.api.Play;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.maps.app.module.MapsImplModule;
import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.seapal.maps.models.MapsPositionState;

public class MapsControllerTest {

	private final static Injector INJECTOR = Guice.createInjector(new MapsImplModule());
	
	IMapsController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = INJECTOR.getInstance(IMapsController.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void modifyPosition() throws IllegalStateException, RemoteException {
		Point expected = new Point(99, 99);
		
		controller.setPositionState(MapsPositionState.FIXED);
		controller.setPosition(expected);
		
		assertEquals(expected, controller.getPosition());
	}
	
	@Test
	public void verifyShowMenu() throws RemoteException {
		controller.showMenu();
		
		assertEquals(true, controller.getMenuVisible());
	}

	@Test
	public void verifyHideMenu() throws RemoteException {
		controller.hideMenu();
		
		assertEquals(false, controller.getMenuVisible());
	}
}