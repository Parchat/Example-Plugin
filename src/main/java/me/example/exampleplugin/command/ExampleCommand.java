package me.example.exampleplugin.command;

import com.google.inject.Inject;
import me.example.exampleplugin.ExamplePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ExampleCommand implements CommandExecutor {

    @Inject
    private ExamplePlugin plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        plugin.getLogger().finest("Guten Tag!");

        sender.sendMessage("Command is working!");
        return true;
    }
}