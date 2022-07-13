package me.example.exampleplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ExampleTab implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("example.reload") || sender.hasPermission("example.admin.*")) completions.add("reload");
            if (sender.hasPermission("example.help") || sender.hasPermission("example.player.*")) completions.add("help");

            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        }

        return completions;
    }
}