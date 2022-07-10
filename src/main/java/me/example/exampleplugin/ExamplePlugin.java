package me.example.exampleplugin;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.example.exampleplugin.api.ExampleManager;
import me.example.exampleplugin.command.ExampleCommand;
import me.example.exampleplugin.listeners.ExampleListener;
import me.example.exampleplugin.modules.BinderModule;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ExamplePlugin extends JavaPlugin {

    @Inject
    private ExampleManager exampleManager;

    @Inject
    private ExamplePlugin examplePlugin;

    @Inject
    private ExampleListener listener;

    @Inject
    private ExampleCommand command;

    @Override
    public void onEnable() {
        BinderModule module = new BinderModule(examplePlugin, exampleManager);
        Injector injector = module.createInjector();

        injector.injectMembers(examplePlugin);

        exampleManager.load();

        // Register listeners
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(listener, this);

        Objects.requireNonNull(getCommand("example")).setExecutor(command);
    }

    @Override
    public void onDisable() {
        exampleManager.stop();
    }
}