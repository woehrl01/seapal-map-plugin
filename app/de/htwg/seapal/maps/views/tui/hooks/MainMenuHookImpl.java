package de.htwg.seapal.maps.views.tui.hooks;

import de.devsurf.injection.guice.annotations.Bind;

@Bind(multiple=true)
public class MainMenuHookImpl implements MainMenuHook {

	@Override
	public void print(String entryName) {
		System.out.print("hook for " + entryName);
	}

}
