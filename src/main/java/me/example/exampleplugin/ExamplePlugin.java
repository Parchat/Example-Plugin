package me.example.exampleplugin;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.example.exampleplugin.api.ExampleManager;
import me.example.exampleplugin.api.FileManager;
import me.example.exampleplugin.api.config.ConfigFile;
import me.example.exampleplugin.api.config.LangFile;
import me.example.exampleplugin.command.ExampleCommand;
import me.example.exampleplugin.command.ExampleTab;
import me.example.exampleplugin.listeners.ExampleListener;
import me.example.exampleplugin.modules.PluginModule;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Singleton
public class ExamplePlugin extends JavaPlugin {

    private Injector injector;

    // Defines if plugin is enabled or not.
    private boolean isEnabled = false;


    // An example of fetching our manager instances.
    @Inject private ExampleManager exampleManager;

    @Inject private FileManager fileManager;

    // An example of injecting our listeners.
    @Inject private ExampleListener listener;

    // An example of injecting our example commands.
    @Inject private ExampleCommand command;

    @Inject private ExampleTab tab;

    // An example of injecting our config instance.
    @Inject private ConfigFile configFile;

    @Inject private LangFile langFile;

    @Override
    public void onEnable() {
        // Run in a try catch to make sure everything needed actually loads otherwise return.
        try {
            // We obviously need to bind it to something to begin with, so it isn't null which allows us to use @Inject annotation with it.
            exampleManager = new ExampleManager();
            fileManager = new FileManager();
            configFile = new ConfigFile();
            langFile = new LangFile();

            // Guice injector which is like Lombok, It's a proper way to use dependency injection.
            PluginModule module = new PluginModule(this, configFile, langFile, exampleManager, fileManager);

            injector = module.createInjector();

            injector.injectMembers(this);

            // An example of how to set up basic files.
            // Check src/main/resources for where these files come from.
            fileManager.registerCustomFolder("/test").registerDefaultGenerateFiles("Test.yml", "/test").setup().isLogging(true);

            // Now we can load.
            exampleManager.load();

            // Register listeners
            PluginManager pluginManager = getServer().getPluginManager();

            pluginManager.registerEvents(listener, this);

            // Register commands
            // The value enclosed in getCommand("") must match the plugin.yml
            getCommand("example").setExecutor(command);
            getCommand("example").setTabCompleter(tab);
        } catch (Exception e) {
            getLogger().severe(e.getMessage());

            for (StackTraceElement stack : e.getStackTrace()) {
                getLogger().severe(String.valueOf(stack));
            }

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