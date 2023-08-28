package dev.stevanoac.coins.model;

import dev.stevanoac.coins.Coins;
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
        this.coins.getOfflinePlayerDocument(offlinePlayer).replace("coins", coins);
    }

    public void addCoins(int coins) {
        this.coins.getOfflinePlayerDocument(offlinePlayer).replace("coins", this.coins.getOfflinePlayerDocument(offlinePlayer).getInteger("coins"));
    }

    public void revokeCoins(int coins) {
        this.coins.getOfflinePlayerDocument(offlinePlayer).replace("coins", this.coins.getOfflinePlayerDocument(offlinePlayer).getInteger("coins"));
    }
}
