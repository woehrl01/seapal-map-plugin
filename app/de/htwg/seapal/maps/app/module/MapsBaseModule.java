package de.htwg.seapal.maps.app.module;

import org.apache.commons.lang3.AnnotationUtils;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import de.htwg.seapal.common.plugin.HookRegistry;
import de.htwg.seapal.common.plugin.Initializable;
import de.htwg.seapal.maps.app.AppInitilizer;
import de.htwg.seapal.maps.app.MapsInitialize;
import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.seapal.maps.views.web.hooks.HtmlRenderHook;
import de.htwg.seapal.maps.views.web.hooks.MenuBarHook;
import de.htwg.seapal.maps.views.web.hooks.impl.ExampleHtmlRenderHook;
import de.htwg.seapal.maps.views.web.hooks.impl.ExampleMenuBarHook;

/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsBaseModule extends AbstractModule {
	@Override
	protected void configure() {	    
		configureHtmlRenderHooks();
		configureMenuBarHooks();
		
		bind(HookRegistry.class);
	    bind(AppInitilizer.class);
	    
	    bind(IMapsController.class).to(de.htwg.seapal.maps.controllers.MapsController.class);
	    
	    Multibinder<Initializable> hooks = Multibinder.newSetBinder(binder(), Initializable.class);
	    hooks.addBinding().to(MapsInitialize.class);
	    
	    // configure database configuration
		bind(String.class).annotatedWith(Names.named("databaseHost")).toInstance("lenny2.in.htwg-konstanz.de");
	    bind(Integer.class).annotatedWith(Names.named("databasePort")).toInstance(5984);
	    bind(String.class).annotatedWith(Names.named("databaseURL")).toInstance("http://lenny2.in.htwg-konstanz.de");
	}
	
	private void configureHtmlRenderHooks() {
		Multibinder<HtmlRenderHook> plugins = Multibinder.newSetBinder(binder(), HtmlRenderHook.class);
		plugins.addBinding().to(ExampleHtmlRenderHook.class);
	}
	
	private void configureMenuBarHooks() {
		Multibinder<de.htwg.seapal.maps.views.web.hooks.MenuBarHook> plugins = Multibinder.newSetBinder(binder(), de.htwg.seapal.maps.views.web.hooks.MenuBarHook.class);
		plugins.addBinding().to(ExampleMenuBarHook.class);
		//plugins.addBinding().to(TripMenuBarHook.class);
	}
	
	@Provides
    HttpClient getHttpClient(@Named("databaseHost") String databaseHost, @Named("databasePort") int databasePort) {
        return new StdHttpClient.Builder().host(databaseHost).port(databasePort).build();
    }

    @Provides
    CouchDbInstance getStdCouchDbInstance(HttpClient httpClient) {
        return new StdCouchDbInstance(httpClient);
    }
}
