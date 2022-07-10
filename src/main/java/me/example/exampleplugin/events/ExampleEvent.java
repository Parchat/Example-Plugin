package me.example.exampleplugin.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.example.exampleplugin.api.ExampleManager;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ExampleEvent implements Listener {

    @EventHandler
    public void onAsyncChatEvent(AsyncChatEvent event) {
        ExampleManager.instance.getPlugin().getLogger().info(event.getPlayer().getName());

        event.getPlayer().sendMessage(Component.text("Guten Tag!"));
    }
}