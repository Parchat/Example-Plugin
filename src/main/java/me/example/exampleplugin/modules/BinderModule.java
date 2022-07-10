package me.example.exampleplugin.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.api.ExampleManager;

public class BinderModule extends AbstractModule {

    private final ExamplePlugin instance;
    private final ExampleManager exampleManager;

    public BinderModule(ExamplePlugin plugin, ExampleManager exampleManager) {
        this.instance = plugin;

        this.exampleManager = exampleManager;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        bind(ExamplePlugin.class).toInstance(instance);

        bind(ExampleManager.class).toInstance(exampleManager);
    }
}