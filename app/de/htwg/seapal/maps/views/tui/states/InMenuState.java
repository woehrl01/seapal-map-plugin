package de.htwg.seapal.maps.views.tui.states;

import java.util.Set;

import com.google.inject.Inject;

import de.htwg.seapal.common.plugin.Plugin;
import de.htwg.seapal.common.views.tui.StateContext;
import de.htwg.seapal.common.views.tui.TuiState;
import de.htwg.seapal.maps.views.tui.MainMenuHook;


public class InMenuState implements TuiState {
	
	private Set<Plugin> plugins;
	private Set<MainMenuHook> hooks;
	
	private StateFactory stateFactory;
	
	@Inject
	public InMenuState(StateFactory stateFactory, Set<Plugin> plugins, Set<MainMenuHook> hooks){
		this.plugins = plugins;
		this.stateFactory = stateFactory;
		this.hooks = hooks;
	}

	@Override
	public void print() {
		System.out.println("Maps: ");
		System.out.print("q - Quit");

		for(MainMenuHook hook : hooks){
			hook.print("Quit");
		}
		
		System.out.println("");
		
		for (Plugin plugin : plugins) {
			System.out.print(plugin.getMenuKey() + " - ");
			System.out.print(plugin.getMenuEntry());
			for(MainMenuHook hook : hooks){
				hook.print(plugin.getMenuEntry());
			}
			System.out.println("");
		}
	}

	@Override
	public boolean process(StateContext context, String input) {
		if(input.equalsIgnoreCase("q")){
			System.out.println("Shuting down...");
			return false;
		}else{
			for (Plugin plugin : plugins) {
				if (input.toLowerCase().charAt(0) == plugin.getMenuKey()) {
					context.setState(stateFactory.createInPluginState(plugin));
					break;
				}
			}
		}
		return true;
	}

}
