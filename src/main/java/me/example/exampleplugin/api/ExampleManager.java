package me.example.exampleplugin.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.api.config.ConfigFile;
import me.example.exampleplugin.api.config.LangFile;

@Singleton
public class ExampleManager {

    // An example of fetching our plugin instance.
    @Inject private ExamplePlugin plugin;

    // An example of fetching our config instance.
    @Inject private ConfigFile configFile;

    @Inject private LangFile langFile;

    // Load your plugin and related code.
    public void load() {
        plugin.getLogger().info("Guten Tag!");

        // Load the files.
        configFile.load();
        langFile.load();
    }

    public void stop() {
        plugin.getLogger().info("Bis Später");

        // Save the files.
        configFile.save();
        langFile.save();
    }
}