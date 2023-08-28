package dev.stevanoac.coins.model;

import dev.stevanoac.coins.Coins;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class EditPlayer {
    private int coinsAmount;
    private final Coins coins;
    public EditPlayer(Coins coins, Player player) {
        this.coins = coins;
        if (coins.getPlayerDocument(player) == null) coinsAmount = 0;
        this.coinsAmount = coins.getPlayerDocument(player).getInteger("coins");
    }

    public EditPlayer(Coins coins, OfflinePlayer player) {
        this.coins = coins;
        if (coins.getOfflinePlayerDocument(player) == null) coinsAmount = 0;
        this.coinsAmount = coins.getOfflinePlayerDocument(player).getInteger("coins");
    }

    public int getCoins() {
        return coinsAmount;
    }

    public void setCoins(int coins) {
        this.coinsAmount = coins;
    }

    public void addCoins(int coins) {
        this.coinsAmount = this.coinsAmount + coins;
    }

    public void revokeCoins(int coins) {
        this.coinsAmount = this.coinsAmount - coins;
    }
}
