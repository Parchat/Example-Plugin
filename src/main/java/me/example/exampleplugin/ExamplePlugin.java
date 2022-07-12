package me.example.exampleplugin;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.example.exampleplugin.api.ExampleManager;
import me.example.exampleplugin.command.ExampleCommand;
import me.example.exampleplugin.listeners.ExampleListener;
import me.example.exampleplugin.modules.PluginModule;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    private Injector injector;

    private boolean isEnabled = false;

    @Inject
    private ExampleManager exampleManager;

    @Inject
    private ExampleListener listener;

    @Inject
    private ExampleCommand command;

    @Override
    public void onEnable() {
        // Run in a try catch to make sure everything needed actually loads otherwise return.
        try {
            // We obviously need to bind it to something to begin with, so it isn't null.
            exampleManager = new ExampleManager(this);

            // Guice injector
            PluginModule module = new PluginModule(this, exampleManager);

            injector = module.createInjector();

            injector.injectMembers(this);

            // Now we can load.
            exampleManager.load();

            // Register listeners
            PluginManager pluginManager = getServer().getPluginManager();

            pluginManager.registerEvents(listener, this);

            getCommand("example").setExecutor(command);
        } catch (Exception e) {
            getLogger().severe(e.getMessage());
            getLogger().severe(e.getCause().getMessage());

            return;
        }

        isEnabled = true;
    }

    @Override
    public void onDisable() {
        if (!isEnabled) return;

        exampleManager.stop();

        injector = null;
    }
}