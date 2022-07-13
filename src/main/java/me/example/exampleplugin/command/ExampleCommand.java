package me.example.exampleplugin.command;

import com.google.inject.Inject;
import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.api.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ExampleCommand implements CommandExecutor {

    @Inject
    private ExamplePlugin plugin;

    @Inject
    private Config config;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Command is working!");

            sender.sendMessage("/example reload");
        } else {
            switch (args[0].toLowerCase()) {
                case "reload" -> {
                    config.reload();

                    sender.sendMessage("Output: " + config.getFile().getString("Settings.metrics-enabled"));

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