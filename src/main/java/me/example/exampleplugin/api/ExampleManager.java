package me.example.exampleplugin.api;

import com.google.inject.Inject;
import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.api.config.Config;

public class ExampleManager {

    @Inject
    private ExamplePlugin plugin;

    @Inject
    private Config config;

    // Load your plugin and related code.
    public void load() {
        plugin.getLogger().info("Guten Tag!");

        config.load();
    }

    public void stop() {
        plugin.getLogger().info("Bis Später");

        config.save();
    }
}