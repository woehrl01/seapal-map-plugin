package de.htwg.seapal.maps.app.module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.assistedinject.FactoryProvider;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

import de.htwg.seapal.maps.views.tui.states.InMenuState;
import de.htwg.seapal.maps.views.tui.states.InMenuStateFactory;
import de.htwg.seapal.maps.views.tui.states.InPluginState;
import de.htwg.seapal.maps.views.tui.states.InPluginStateFactory;
import de.htwg.seapal.boat.views.tui.BoatTUI;
import de.htwg.seapal.person.views.tui.PersonTUI;
import de.htwg.seapal.common.plugin.Plugin;
import de.htwg.seapal.common.views.tui.TuiState;

/**
 * Mock Google Guice module description of the maps module.
 * @author Benjamin
 */
public class MapsBaseModule extends AbstractModule {
	@Override
	protected void configure() {
		// TUI multibindings
		Multibinder<Plugin> plugins = Multibinder.newSetBinder(binder(), Plugin.class);
		//plugins.addBinding().to(BoatTUI.class);
		plugins.addBinding().to(PersonTUI.class);

	    bind(TuiState.class).annotatedWith(Names.named("Initial")).to(InMenuState.class);
	    	
	    install(new FactoryModuleBuilder()
	    	.implement(TuiState.class, InMenuState.class)
	    	.build(InMenuStateFactory.class));
	    
	    install(new FactoryModuleBuilder()
	    	.implement(TuiState.class, InPluginState.class)
	    	.build(InPluginStateFactory.class));   
	}
}
