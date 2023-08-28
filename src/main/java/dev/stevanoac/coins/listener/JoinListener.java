package dev.stevanoac.coins.listener;

import dev.stevanoac.coins.Coins;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    public Coins coins;

    public JoinListener(Coins coins) {
        this.coins = coins;
    }

    @EventHandler
    public void createDocumentOnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            coins.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.GREEN + "Creating a new document for player " + player.getName() + ". " + ChatColor.GRAY + "(First Time Player)");
            Document document = new Document("uuid", player.getUniqueId().toString());
            document.append("coins", 0);

            coins.getMongoCollection().insertOne(document);
        }

        if (coins.getPlayerDocument(player) == null) {
            coins.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.GREEN + "Creating a new document for player " + player.getName() + ".");
            Document document = new Document("uuid", player.getUniqueId().toString());
            document.append("coins", 0);

            coins.getMongoCollection().insertOne(document);
        }
    }
}
