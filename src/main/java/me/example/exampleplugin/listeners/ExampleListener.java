package me.example.exampleplugin.listeners;

import com.google.inject.Inject;
import io.papermc.paper.event.player.AsyncChatEvent;
import me.example.exampleplugin.ExamplePlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

// A listener example.
@Singleton
public class ExampleListener implements Listener {

    // An example of injecting our plugin instance.
    @Inject private ExamplePlugin plugin;

    @EventHandler
    public void onAsyncChatEvent(AsyncChatEvent event) {
        plugin.getLogger().info(event.getPlayer().getName());

        event.getPlayer().sendMessage(Component.text("Guten Tag!"));
    }
}