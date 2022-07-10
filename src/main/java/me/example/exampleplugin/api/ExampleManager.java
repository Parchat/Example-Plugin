package me.example.exampleplugin.api;

import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.events.ExampleEvent;
import org.bukkit.plugin.PluginManager;

public class ExampleManager {

    /**
     * The plugin instance of your Example Plugin.
     */
    private ExamplePlugin plugin;

    public static ExamplePlugin instance;

    public ExampleManager() {
        instance = this;
    }

    /**
     * Get the plugin instance.
     * @return Your plugin instance.
     */
    public ExamplePlugin getPlugin() {
        return plugin;
    }

    /**
     * @param plugin
     * Load the plugin instance.
     */
    public void loadPlugin(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    // Load your plugin and related code.
    public void load() {
        plugin.getLogger().info("Guten Tag!");

        // Register listeners
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new ExampleEvent(), plugin);
    }
}