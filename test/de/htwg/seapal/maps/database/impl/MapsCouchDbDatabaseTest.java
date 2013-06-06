package de.htwg.seapal.maps.database.impl;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import play.api.Application;
import play.api.DefaultApplication;
import play.api.Mode;
import play.api.Play;
import de.htwg.seapal.maps.app.module.MapsImplModule;
import de.htwg.seapal.maps.controllers.impl.MapsControllerTest;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;
import de.htwg.seapal.maps.models.impl.Maps;

public class MapsCouchDbDatabaseTest {

	private static final Injector INJECTOR = Guice.createInjector(new MapsImplModule());
	
	IMapsDatabase database;
	
	@BeforeClass
	public static void setup() {
		Application play = new DefaultApplication(new File("."),
				MapsControllerTest.class.getClassLoader(), null, Mode.Dev());
		Play.start(play);
	}
	
	@AfterClass
	public static void shutdown() {
		Play.stop();
	}

	@Before
	public void setUp() throws Exception {
		database = INJECTOR.getInstance(IMapsDatabase.class);
	}

	@After
	public void tearDown() throws Exception {
		database.close();
	}

	@Test
	public void saveAndLoadPosition() {
		String expected = "44.44,55.55";
		IMaps maps = new Maps();
		maps.setPositionState(MapsPositionState.FIXED);
		maps.setPosition(expected);
		database.save(maps);
		IMaps loadedMaps = database.load();
		
		assertNotNull(loadedMaps);
		assertEquals(expected, loadedMaps.getPosition());
	}
	
	@Test
	public void saveAndLoadPositionState() {
		MapsPositionState expected = MapsPositionState.FIXED;
		IMaps maps = new Maps();
		maps.setPositionState(expected);
		database.save(maps);
		IMaps loadedMaps = database.load();
		
		assertNotNull(loadedMaps);
		assertEquals(expected.toString(), loadedMaps.getPositionState().toString());
	}
	
	@Test
	public void saveAndLoadMenuPositionState() {
		MapsMenuPositionState expected = MapsMenuPositionState.RIGHT;
		IMaps maps = new Maps();
		maps.setMenuPositionState(expected);
		database.save(maps);
		IMaps loadedMaps = database.load();
		
		assertNotNull(loadedMaps);
		assertEquals(expected.toString(), loadedMaps.getMenuPositionState().toString());
	}
	
	@Test
	public void saveAndLoadMapsType() {
		MapsType expected = MapsType.SATELITE;
		IMaps maps = new Maps();
		maps.setType(expected);
		database.save(maps);
		IMaps loadedMaps = database.load();
		
		assertNotNull(loadedMaps);
		assertEquals(expected.toString(), loadedMaps.getType().toString());
	}
}
