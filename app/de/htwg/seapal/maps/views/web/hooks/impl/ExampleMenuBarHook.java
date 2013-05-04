package de.htwg.seapal.maps.views.web.hooks.impl;

import de.htwg.seapal.maps.views.web.hooks.MenuBarHook;

public class ExampleMenuBarHook implements MenuBarHook {

	@Override
	public String getMenuTitle() {
		return "Test";
	}

	@Override
	public String getUrl() {
		return de.htwg.seapal.maps.controllers.routes.PlayMapsController.test().url();
	}

}
