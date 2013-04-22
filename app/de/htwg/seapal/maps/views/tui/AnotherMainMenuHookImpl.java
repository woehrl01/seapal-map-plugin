package de.htwg.seapal.maps.views.tui;

import de.devsurf.injection.guice.annotations.Bind;

@Bind(multiple=true)
public class AnotherMainMenuHookImpl implements MainMenuHook {

	@Override
	public void print(String entryName) {
		System.out.print(" another hook,");

	}

}
