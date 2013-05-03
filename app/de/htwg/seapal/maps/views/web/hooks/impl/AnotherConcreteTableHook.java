package de.htwg.seapal.maps.views.web.hooks.impl;

import play.api.templates.Html;
import scala.collection.mutable.StringBuilder;
import de.devsurf.injection.guice.annotations.Bind;
import de.htwg.seapal.maps.views.web.hooks.MenuBarHook;

@Bind(multiple=true)
public class AnotherConcreteTableHook implements MenuBarHook {

	@Override
	public Html print() {
		return de.htwg.seapal.maps.views.html.web.hooks.impl.menuhook.render();
	}

}
