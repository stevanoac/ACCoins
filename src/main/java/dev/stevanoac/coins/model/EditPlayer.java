package dev.stevanoac.coins.model;

import dev.stevanoac.coins.Coins;
import org.bson.Document;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class EditPlayer {
    private final Coins coins;
    private final OfflinePlayer offlinePlayer;
    public EditPlayer(Coins coins, Player player) {
        this.coins = coins;
        this.offlinePlayer = player;
    }

    public EditPlayer(Coins coins, OfflinePlayer player) {
        this.coins = coins;
        this.offlinePlayer = player;
    }

    public int getCoins() {
        return coins.getOfflinePlayerDocument(offlinePlayer).getInteger("coins");
    }

    public void setCoins(int coins) {
        Document document = this.coins.getOfflinePlayerDocument(offlinePlayer);
        document.replace("coins", coins);
        this.coins.getMongoCollection().updateOne(new Document("UUID", offlinePlayer.getUniqueId()), document);
    }

    public void addCoins(int amount) {
        Document document = coins.getOfflinePlayerDocument(offlinePlayer);
        int newCoins = getCoins() + amount;
        document.replace("coins", newCoins);
        this.coins.getMongoCollection().replaceOne(new Document("UUID", offlinePlayer.getUniqueId()), document);
    }

    public void revokeCoins(int amount) {
        Document document = coins.getOfflinePlayerDocument(offlinePlayer);
        int currentCoins = getCoins();
        int newCoins = Math.max(currentCoins - amount, 0);
        document.replace("coins", newCoins);
        this.coins.getMongoCollection().replaceOne(new Document("UUID", offlinePlayer.getUniqueId()), document);
    }
}
