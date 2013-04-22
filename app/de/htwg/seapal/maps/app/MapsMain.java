package de.htwg.seapal.maps.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.maps.views.tui.MapsTUI;
import de.htwg.seapal.person.app.PersonDemoImplModule;
import de.htwg.seapal.person.app.PersonDemoMockModule;
import de.htwg.seapal.boat.app.AppMockModule;

/**
 * The maps startup class.
 */ class MapsMain {

	/**
	 * The programs main.
	 * @param args The default program arguments.
	 * @throws IOException 
	 */
	 
	public static void main(String[] args) throws IOException {
		// Set up Google Guice Dependency Injector
		Injector injector = Guice.createInjector(new MapsMockModule(), new PersonDemoImplModule(), new AppMockModule());
		
		// Build up the application, resolving dependencies automatically by Guice
		MapsTUI tui = injector.getInstance( MapsTUI.class);
		
		tui.printTUI();

		// continue to read user input on the tui until the user decides to quit	
		InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		while (tui.processInputLine(br.readLine()));
		
	}

}
