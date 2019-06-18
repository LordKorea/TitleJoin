package io.gitlab.lordkorea.titlejoin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin entry point.
 */
public class TitleJoinPlugin extends JavaPlugin implements Listener {

    /**
     * The message format for the title when someone joins.
     */
    private String joinMessageTitleFmt;

    /**
     * The message format for the subtitle when someone joins.
     */
    private String joinMessageSubitleFmt;

    /**
     * The delay before the message is sent.
     */
    private long joinMessageDelay;

    /**
     * The number of ticks that the message will fade in for.
     */
    private int joinMessageFadeIn;

    /**
     * The number of ticks that the message will stay on the screen for.
     */
    private int joinMessageStay;

    /**
     * The number of ticks that the message will fade out for.
     */
    private int joinMessageFadeOut;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        reloadSettings();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("titlejoin.reload")) {
                reloadSettings();
                sender.sendMessage("Reloaded settings!");
            } else {
                sender.sendMessage("You lack the permission titlejoin.reload");
            }
        } else {
            sender.sendMessage("Syntax: /titlejoin reload");
        }
        return true;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            final String playerName = event.getPlayer().getName();
            final String title = String.format(joinMessageTitleFmt, playerName);
            final String subtitle = String.format(joinMessageSubitleFmt, playerName);
            event.getPlayer().sendTitle(title, subtitle, joinMessageFadeIn, joinMessageStay, joinMessageFadeOut);
        }, joinMessageDelay);
    }

    /**
     * Reloads the plugin's settings.
     */
    private void reloadSettings() {
        //noinspection ConstantConditions
        joinMessageTitleFmt = ChatColor.translateAlternateColorCodes('&',
                getConfig().getString("join-message-title"));
        //noinspection ConstantConditions
        joinMessageSubitleFmt = ChatColor.translateAlternateColorCodes('&',
                getConfig().getString("join-message-subtitle"));
        joinMessageDelay = getConfig().getLong("join-message-delay");
        joinMessageFadeIn = getConfig().getInt("join-message-fade-in");
        joinMessageStay = getConfig().getInt("join-message-stay");
        joinMessageFadeOut = getConfig().getInt("join-message-fade-out");
    }
}
