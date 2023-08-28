package dev.stevanoac.coins;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.stevanoac.coins.listener.JoinListener;
import dev.stevanoac.coins.model.EditPlayer;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Coins extends JavaPlugin {
    private static MongoClientURI clientURI;
    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    public static MongoCollection mongoCollection;
    public static JavaPlugin plugin;

    public Coins(String uri, String databaseName, JavaPlugin plugin) {
        clientURI = new MongoClientURI(uri);
        mongoClient = new MongoClient(clientURI);
        mongoDatabase = mongoClient.getDatabase(databaseName);
        mongoCollection = mongoDatabase.getCollection("coins");
        Coins.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new JoinListener(Coins.this), plugin);
    }

    public static EditPlayer editPlayer(Player player) {
        BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId().toString());
        FindIterable<Document> result = mongoCollection.find(query);

        if (result.first() == null) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "The first document for player " + player.getName() + " has returned null. It could be that there is no document for this player.");
            return null;
        }

        return new EditPlayer(player);
    }

    public static Document getPlayerDocument(Player player) {
        BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId().toString());
        FindIterable<Document> result = mongoCollection.find(query);

        Document playerDocument = result.first();
        if (playerDocument == null) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "The document for player " + player.getName() + " was not found.");
        }

        return playerDocument;
    }

    public static Document getOfflinePlayerDocument(OfflinePlayer player) {
        BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId().toString());
        FindIterable<Document> result = mongoCollection.find(query);

        Document playerDocument = result.first();
        if (playerDocument == null) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "The document for player " + player.getName() + " was not found.");
        }

        return playerDocument;
    }

    public static EditPlayer editOfflinePlayer(OfflinePlayer player) {
        BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId().toString());
        FindIterable<Document> result = mongoCollection.find(query);

        if (result.first() == null) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "The first document for player " + player.getName() + " has returned null. It could be that there is no document for this player.");
            return null;
        }

        return new EditPlayer(player);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoClientURI getClientURI() {
        return clientURI;
    }

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
