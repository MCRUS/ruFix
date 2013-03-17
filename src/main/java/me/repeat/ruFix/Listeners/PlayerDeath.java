package me.repeat.ruFix.Listeners;

import me.repeat.ruFix.ruFix;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Alex Bond
 * Date: 26.12.12
 * Time: 3:01
 */
public class PlayerDeath implements Listener {
    public static ruFix plugin;

    public PlayerDeath(ruFix instance) {
        plugin = instance;
    }

    @EventHandler
    public void death(PlayerDeathEvent e) {
        String before = e.getDeathMessage();
        Player p = e.getEntity();
        EntityDamageEvent damageEvent = p.getLastDamageCause();
        if (damageEvent instanceof EntityDamageByEntityEvent) {
            Entity damager = ((EntityDamageByEntityEvent) damageEvent).getDamager();
            if (damager.getType().toString().equals("ARROW")) {
                Projectile arrow = (Arrow) damager;
                if (arrow.getShooter().getType().toString().equals("SKELETON")) {
                    e.setDeathMessage("%killed% был убит Скелетом");
                }
            } else if ((damager instanceof Zombie)) {
                if (damager.getType().toString().equals("PIG_ZOMBIE")) {
                    e.setDeathMessage("%killed% был убит Свинозомби");
                } else if (damager.getType().toString().equals("ZOMBIE")) {
                    e.setDeathMessage("%killed% был убит Зомби");
                } else {
                    e.setDeathMessage("%killed% был убит Зомби" + "(" + damager.getType().toString() + ")");
                }
            } else if ((damager instanceof Skeleton)) {
                e.setDeathMessage("%killed% был убит Скелетом-Иссушителем");
            } else if ((damager instanceof Creeper)) {
                e.setDeathMessage("%killed% был убит Крипером");
            } else if ((damager instanceof Ghast)) {
                e.setDeathMessage("%killed% был убит Гастом");
            } else if ((damager instanceof Blaze)) {
                e.setDeathMessage("%killed% был убит Ифритом");
            } else if (damager.getType().toString().equals("SMALL_FIREBALL")) {
                e.setDeathMessage("%killed% был убит Ифритом");
            } else if ((damager instanceof Slime)) {
                if (damager.getType().toString().equals("MAGMA_CUBE")) {
                    e.setDeathMessage("%killed% был убит Адским Слизнем");
                } else if (damager.getType().toString().equals("SLIME")) {
                    e.setDeathMessage("%killed% был убит Слизнем");
                } else {
                    e.setDeathMessage("%killed% был убит Слизнем" + "(" + damager.getType().toString() + ")");
                }
            } else if ((damager instanceof MagmaCube)) {
                e.setDeathMessage("%killed% был убит Адским Слизнем");
            } else if ((damager instanceof Wolf)) {
                e.setDeathMessage("%killed% был убит Волком");
            } else if ((damager instanceof Spider)) {
                e.setDeathMessage("%killed% был убит Пауком");
            } else if ((damager instanceof CaveSpider)) {
                e.setDeathMessage("%killed% был убит Пещерным Пауком");
            } else if ((damager instanceof Silverfish)) {
                e.setDeathMessage("%killed% был убит Чешуйницей");
            } else if ((damager instanceof Enderman)) {
                e.setDeathMessage("%killed% был убил Эндерменом");
            } else if ((damager instanceof EnderDragon)) {
                e.setDeathMessage("%killed% был убит Драконом Эндера");
            } else if ((damager instanceof Wither)) {
                e.setDeathMessage("%killed% был убит Иссушителем");
            } else if ((damager instanceof Witch)) {
                e.setDeathMessage("%killed% был убит Ведьмой");
            } else if ((damager instanceof WitherSkull)) {
                e.setDeathMessage("%killed% был убит Скелетом-иссушителем");
            }
        }
        if (!before.equals(e.getDeathMessage()))
            e.setDeathMessage(ruFix.fixUseTable(e.getDeathMessage().replace("%killed%", p.getName())));

    }
}
