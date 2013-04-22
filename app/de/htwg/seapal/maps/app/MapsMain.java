package de.htwg.seapal.maps.app;

import java.io.File;
import java.util.Scanner;

import play.api.Application;
import play.api.DefaultApplication;
import play.api.Mode;
import play.api.Play;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.boat.app.AppMockModule;
import de.htwg.seapal.maps.views.tui.MapsTUI;
import de.htwg.seapal.person.app.PersonDemoMockModule;

/**
 * The maps startup class.
 */
public class MapsMain {

	/**
	 * The programs main.
	 * 
	 * @param args	The default program arguments.
	 */
	public static void main(String[] args) {
		// Initialize Play Application to use the play environment functions...
		Application play = new DefaultApplication(new File("."),
				MapsMain.class.getClassLoader(), null, Mode.Dev());
		Play.start(play);

		// Set up Google Guice Dependency Injector
		Injector injector = Guice.createInjector(new MapsMockModule(),
				new PersonDemoMockModule(), new AppMockModule());

		// Build up the application, resolving dependencies 
		// automatically by Guice
		MapsTUI tui = injector.getInstance(MapsTUI.class);

		tui.printTUI();

		// continue to read user input on the tui until the user decides to quit
		boolean active = true;
		Scanner scanner = new Scanner(System.in);

		while (active) {
			active = tui.processInputLine(scanner.next());
		}

		scanner.close();

		Play.stop();
	}

}
