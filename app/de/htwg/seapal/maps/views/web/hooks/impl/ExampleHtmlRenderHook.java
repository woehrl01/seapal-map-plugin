package de.htwg.seapal.maps.views.web.hooks.impl;

import play.api.templates.Html;
import de.devsurf.injection.guice.annotations.Bind;
import de.htwg.seapal.maps.views.web.hooks.HtmlRenderHook;

@Bind(multiple=true)
public class ExampleHtmlRenderHook implements HtmlRenderHook {

	@Override
	public Html getHtml() {
		return de.htwg.seapal.maps.views.html.web.hooks.impl.imagelink.render();
	}
}
