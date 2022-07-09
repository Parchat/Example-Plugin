package me.example.exampleplugin.api;

import org.bukkit.plugin.java.JavaPlugin;

public class ExampleManager {

    /**
     * The instance of this class.
     */
    private static final ExampleManager instance = new ExampleManager();

    /**
     * The plugin instance of your Example Plugin.
     */
    private JavaPlugin plugin;

    /**
     * Get the plugin instance.
     * @return Your plugin instance.
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * @param plugin
     * Load the plugin instance.
     */
    public void loadPlugin(JavaPlugin plugin) {
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
    public void load(JavaPlugin plugin) {
        plugin.getLogger().info("Guten Tag!");
    }
}