package me.repeat.ruFix.Listeners;

import me.repeat.ruFix.ruFix;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerListener implements Listener {

    public static ruFix plugin;

    public PlayerListener(ruFix instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) return;
        String fix = ruFix.fixUseTable(event.getMessage());
        if (!event.getMessage().equals(fix)) {
            if (ruFix.ruFixDebug)
                System.out.print("[ruFixDebug]:" + event.getMessage() + ":");
            event.setMessage("/");
            event.setCancelled(true);
            event.getPlayer().chat(fix);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (ruFix.ruFixDebug)
            System.out.print("[ruFixDebug]:" + event.getMessage() + ":");
        event.setMessage(ruFix.fixUseTable(event.getMessage()));
    }
}
