package de.htwg.seapal.maps.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import play.api.Application;
import play.api.DefaultApplication;
import play.api.Mode;
import play.api.Play;

import com.google.inject.Injector;

import de.htwg.seapal.maps.views.tui.MapsTUI;

/**
 * The maps startup class.
 */ class MapsMain {

	/**
	 * The programs main.
	 * 
	 * @param args	The default program arguments.
	 * @throws IOException 
	 */
	 
	public static void main(String[] args) throws IOException {
		// Initialize Play Application to use the play environment functions...
		
		Application play = new DefaultApplication(new File("."), MapsMain.class.getClassLoader(), null, Mode.Prod());
		
		Play.start(play);

		try{
			
			Injector injector = MapsGlobal.createInjector();
			
			MapsTUI tui = injector.getInstance(MapsTUI.class);
			
			tui.printTUI();
	
			// continue to read user input on the tui until the user decides to quit	
			InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
	
			while (tui.processInputLine(br.readLine())){
				
			}
			
			System.out.println("exit.");
			
		}finally{
			Play.stop();
		}
	}

}
