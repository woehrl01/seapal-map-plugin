package de.htwg.seapal.maps.app;

import com.google.inject.Binder;
import com.google.inject.CreationException;
import com.google.inject.multibindings.Multibinder;

import de.devsurf.injection.guice.annotations.Bind;
import de.devsurf.injection.guice.annotations.GuiceModule;
import de.devsurf.injection.guice.annotations.features.AutoBindingFeature;
import de.devsurf.injection.guice.annotations.features.MultiBindingFeature;
import de.devsurf.injection.guice.scanner.ClasspathScanner;
import de.devsurf.injection.guice.scanner.PackageFilter;
import de.devsurf.injection.guice.scanner.StartupModule;
import de.devsurf.injection.guice.scanner.features.ScannerFeature;

public class ReflectionModule extends StartupModule {

	public ReflectionModule(Class<? extends ClasspathScanner> scanner, PackageFilter... packages) {
		super(scanner, packages);
	}

	@Override
	protected Multibinder<ScannerFeature> bindFeatures(Binder binder) {
		Multibinder<ScannerFeature> listeners = Multibinder.newSetBinder(binder,
			ScannerFeature.class);
		listeners.addBinding().to(MultiBindingFeature.class);
		return listeners;
	}

}