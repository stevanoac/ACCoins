# ACCoins

[![](https://jitpack.io/v/stevanoac/Coins.svg)](https://jitpack.io/#stevanoac/Coins)


This is a API for minecraft that I made that uses MongoDB to store and save coins for each player.
This is practically an economy plugin but it is something that I made for personal use but can be used by anyone else.

An example usage:
```
Coins coins;

@Override
public void onEnable() {
    #Replace "MONGO_URI" with your mongo uri and "MONGO_DATABASE_NAME" with your mongo database name.
    #This should realistically be in your main class so "this" is an instance of your plugin.
    coins = new Coins("MONGO_URI", "MONGO_DATABASE_NAME", this);
}

public Coins getCoins() {
    return coins;
}

@EventHandler
public void onJoin(PlayerMoveEvent event) {
    Player player = event.getPlayer();

    plugin.getCoins.editPlayer(player).addCoins(1);
    player.sendMessage("You received 1 coin for walking! You now have " + plugin.getCoins().editPlayer(player).getCoins() + " coins!");
}
```
