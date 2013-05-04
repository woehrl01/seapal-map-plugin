package de.htwg.seapal.maps.views.tui.hooks;

import de.devsurf.injection.guice.annotations.Bind;

@Bind(multiple=true)
public class CounterMainMenuHookImpl implements MainMenuHook {

	private int hookCount = 0;
	
	@Override
	public String getText(String entryName) {
		return "counter hook:"+(++hookCount);
	}

}
