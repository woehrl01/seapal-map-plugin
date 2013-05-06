package de.htwg.seapal.maps.app;

import play.api.templates.Html;
import scala.collection.mutable.StringBuilder;
import de.htwg.seapal.common.plugin.HookHandler;
import de.htwg.seapal.common.plugin.HookRegistry;
import de.htwg.seapal.common.plugin.Initializable;

public class MapsInitialize implements Initializable {

	@Override
	public void initHooks(HookRegistry registry) {
		registry.registerHook("menu.show", new HookHandler<Html, Object>(Html.class, Object.class){

			@Override
			public Html execute(Object nothing) {
				final StringBuilder builder = new StringBuilder();
				builder.append("<a href=\"\">test</a>");

				return new Html(builder);
			}
		});
		
		registry.registerHook("menu.show", new HookHandler<Integer, Object>(Integer.class, Object.class){

			@Override
			public Integer execute(Object nothing) {

				/* this should not show */
				return 34343;
			}
		});
		
		registry.registerHook("menu.show", new HookHandler<Html, Object>(Html.class, Object.class){

			@Override
			public Html execute(Object nothing) {
				final StringBuilder builder = new StringBuilder();
				builder.append("<a href=\"\">shouldShow</a>");

				return new Html(builder);
			}
		});
		System.out.println("registered hooks");
	}
}
