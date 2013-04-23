package de.htwg.seapal.maps.app;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.boat.app.AppMockModule;
import de.htwg.seapal.maps.app.module.MapsBaseModule;
import de.htwg.seapal.maps.app.module.MapsImplModule;
import de.htwg.seapal.maps.app.module.MapsMockModule;
import de.htwg.seapal.maps.app.module.ReflectionModule;
import de.htwg.seapal.person.app.PersonDemoImplModule;
import de.htwg.seapal.person.app.PersonDemoMockModule;

import play.*;

public class MapsGlobal extends GlobalSettings {
	
	  private static final Injector INJECTOR = createInjector(); 

	  public static Injector createInjector() {
		  return Guice.createInjector(new ReflectionModule(), new MapsBaseModule(), new MapsImplModule(), new PersonDemoImplModule(), new AppMockModule());
      }
	  
      @Override
      public <A> A getControllerInstance(Class<A> controllerClass) throws Exception {
    	  return INJECTOR.getInstance(controllerClass);
      }
      
      @Override
	  public void onStart(Application app) {
	    Logger.info("Maps app has started");
	  }  
	  
	  @Override
	  public void onStop(Application app) {
	    Logger.info("Maps app shutdown...");
	  }  
}
