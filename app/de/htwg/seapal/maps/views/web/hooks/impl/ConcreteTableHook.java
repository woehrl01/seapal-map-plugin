package de.htwg.seapal.maps.views.web.hooks.impl;

import play.api.templates.Html;
import scala.collection.mutable.StringBuilder;
import de.devsurf.injection.guice.annotations.Bind;
import de.htwg.seapal.maps.views.web.hooks.MenuBarHook;

@Bind(multiple=true)
public class ConcreteTableHook implements MenuBarHook {

	@Override
	public Html print() {
		return new Html(new StringBuilder());
	}

}
