package me.example.exampleplugin.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.api.ExampleManager;
import me.example.exampleplugin.api.FileManager;
import me.example.exampleplugin.api.config.Config;
import me.example.exampleplugin.api.config.Lang;

import javax.annotation.Nonnull;
import java.io.File;

public class PluginModule extends AbstractModule {

    private final ExamplePlugin plugin;

    private final ExampleManager exampleManager;
    private final FileManager fileManager;

    private final Config config;
    private final Lang lang;

    public PluginModule(ExamplePlugin plugin, Config config, Lang lang, ExampleManager exampleManager, FileManager fileManager) {
        this.plugin = plugin;

        this.exampleManager = exampleManager;
        this.fileManager = fileManager;

        this.config = config;
        this.lang = lang;
    }

    @Nonnull
    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        bind(ExamplePlugin.class).toInstance(plugin);

        bind(FileManager.class).toInstance(fileManager);
        bind(ExampleManager.class).toInstance(exampleManager);

        bind(Config.class).toInstance(config);
        bind(Lang.class).toInstance(lang);

        bind(File.class).annotatedWith(Names.named("ConfigFolder")).toInstance(plugin.getDataFolder());
    }
}