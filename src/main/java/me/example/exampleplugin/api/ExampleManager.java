package me.example.exampleplugin.api;

import me.example.exampleplugin.ExamplePlugin;

public class ExampleManager {

    /**
     * The instance of this class.
     */
    private static final ExampleManager instance = new ExampleManager();

    /**
     * The plugin instance of your Example Plugin.
     */
    private ExamplePlugin plugin;

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

    /**
     * Get the instance of your class.
     * @return this;
     */
    public ExampleManager getInstance() {
        return this;
    }

    // Load your plugin and related code.
    public void load(ExamplePlugin plugin) {
        plugin.getLogger().info("Guten Tag!");
    }
}