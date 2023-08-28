package dev.stevanoac.coins.model;

import dev.stevanoac.coins.Coins;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class EditPlayer {
    private int coins;
    public EditPlayer(Player player) {
        if (Coins.getPlayerDocument(player) == null) coins = 0;
        this.coins = Coins.getPlayerDocument(player).getInteger("coins");
    }

    public EditPlayer(OfflinePlayer player) {
        if (Coins.getOfflinePlayerDocument(player) == null) coins = 0;
        this.coins = Coins.getOfflinePlayerDocument(player).getInteger("coins");
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int coins) {
        this.coins = this.coins + coins;
    }

    public void revokeCoins(int coins) {
        this.coins = this.coins - coins;
    }
}
