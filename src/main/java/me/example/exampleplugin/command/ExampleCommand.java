package me.example.exampleplugin.command;

import com.google.inject.Inject;
import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.api.config.ConfigFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

// A command example
public class ExampleCommand implements CommandExecutor {

    // An example of injecting our plugin instance.
    @Inject private ExamplePlugin plugin;

    // An example of injecting our config instance.
    @Inject private ConfigFile configFile;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Command is working!");

            sender.sendMessage("/example reload");
        } else {
            switch (args[0].toLowerCase()) {
                case "reload" -> {
                    configFile.reload();

                    sender.sendMessage("Output: " + configFile.getFile().getString("Settings.metrics-enabled"));

                    sender.sendMessage("You have reloaded the plugin.");
                }

                case "help" -> {
                    sender.sendMessage("/example help");
                    sender.sendMessage("/example reload");
                }
            }
        }
        return true;
    }
}