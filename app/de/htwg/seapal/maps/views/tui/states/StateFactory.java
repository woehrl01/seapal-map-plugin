package de.htwg.seapal.maps.views.tui.states;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import de.htwg.seapal.common.plugin.Plugin;

@Singleton
public class StateFactory {
	
	private Injector injector;
	
	@Inject
	public StateFactory(Injector injector){
		this.injector = injector;
	}
	
	public InMenuState createInMenuState(){
		return injector.getInstance(InMenuState.class);
	}
	
	public InPluginState createInPluginState(Plugin plugin){
		InPluginState state = new InPluginState(plugin);
		injector.injectMembers(state);
		return state;
	}
	

}
