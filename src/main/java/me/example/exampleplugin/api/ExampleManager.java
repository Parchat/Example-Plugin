package me.example.exampleplugin.api;

import com.google.inject.Inject;
import me.example.exampleplugin.ExamplePlugin;

public class ExampleManager {

    @Inject
    private ExamplePlugin plugin;

    private boolean isEnabled = false;

    // Load your plugin and related code.
    public void load() {
        plugin.getLogger().info("Guten Tag!");

        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();

        isEnabled = true;
    }

    public void stop() {
        if (!isEnabled) return;

        plugin.getLogger().info("See you later!");
    }
}