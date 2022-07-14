package me.example.exampleplugin.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.api.config.Config;
import me.example.exampleplugin.api.config.Lang;

@Singleton
public class ExampleManager {

    @Inject
    private ExamplePlugin plugin;

    @Inject
    private Config config;

    @Inject
    private Lang lang;

    // Load your plugin and related code.
    public void load() {
        plugin.getLogger().info("Guten Tag!");

        config.load();
        lang.load();
    }

    public void stop() {
        plugin.getLogger().info("Bis Später");

        config.save();
        lang.save();
    }
}