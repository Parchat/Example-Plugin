package me.example.exampleplugin;

import me.example.exampleplugin.api.ExampleManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    // Avoid using "this"
    private final ExamplePlugin plugin = this;

    // Get the ExampleManager instance
    private final ExampleManager exampleManager = new ExampleManager().getInstance();

    @Override
    public void onEnable() {
        exampleManager.loadPlugin(plugin);

        // Always use a plugin pass through for this.
        exampleManager.load(plugin);
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("See you later!");
    }
}