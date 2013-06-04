package de.htwg.seapal.maps.database.impl;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.api.Application;
import play.api.DefaultApplication;
import play.api.Mode;
import play.api.Play;

import de.htwg.seapal.maps.controllers.impl.MapsControllerTest;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;

public class MapsEbeanDatabaseTest {

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
		database = new MapsEbeanDatabase();
	}

	@After
	public void tearDown() throws Exception {
		database.close();
	}

	@Test
	public void loadModifyAndSave() {
		Point expected = new Point(99,99);
		
		IMaps maps = database.load();
		maps.setPosition(expected);
		database.save(maps);
		
		assertEquals(expected, database.load().getPosition());
	}
}
