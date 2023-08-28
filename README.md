# ACCoins

[![](https://jitpack.io/v/stevanoac/Coins.svg)](https://jitpack.io/#stevanoac/Coins)


This is a API for minecraft that I made that uses MongoDB to store and save coins for each player.
This is practically an economy plugin but it is something that I made for personal use but can be used by anyone else.

An example usage:
```
public final class CoinsPlugin extends JavaPlugin implements Listener {
    Coins coins;

    @Override
    public void onEnable() {
        String uri = "URI";
        String database = "DATABASE_NAME";

        coins = new Coins(uri, database, this);

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void coinsTest(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GREEN + "This is a Coins test.");
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage(ChatColor.GOLD + "You have " + coins.editPlayer(player).getCoins() + " coins!");
            }
        }.runTaskLater(this, 20L);
    }
}
```
