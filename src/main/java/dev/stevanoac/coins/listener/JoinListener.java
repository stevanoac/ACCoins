package dev.stevanoac.coins.listener;

import dev.stevanoac.coins.Coins;
import org.bson.Document;
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

        Document document = new Document("uuid", player.getUniqueId());
        document.append("coins", 0);

        coins.getMongoCollection().insertOne(document);
    }
}
