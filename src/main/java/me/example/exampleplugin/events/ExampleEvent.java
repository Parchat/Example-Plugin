package me.example.exampleplugin.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.example.exampleplugin.ExamplePlugin;
import me.example.exampleplugin.api.ExampleManager;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ExampleEvent implements Listener {

    private final ExampleManager exampleManager = ExamplePlugin.getExampleManager();

    @EventHandler
    public void onAsyncChatEvent(AsyncChatEvent event) {
        exampleManager.getPlugin().getLogger().info(event.getPlayer().getName());

        event.getPlayer().sendMessage(Component.text("Guten Tag!"));
    }
}