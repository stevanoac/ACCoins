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
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Coins {
    private MongoClientURI clientURI;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    public MongoCollection mongoCollection;
    public JavaPlugin plugin;

    public Coins(String uri, String databaseName, JavaPlugin plugin) {
        clientURI = new MongoClientURI(uri);
        mongoClient = new MongoClient(clientURI);
        mongoDatabase = mongoClient.getDatabase(databaseName);
        mongoCollection = mongoDatabase.getCollection("coins");
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(new JoinListener(this), this.plugin);
    }

    public EditPlayer editPlayer(Player player) {
        BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId());
        FindIterable<Document> result = mongoCollection.find(query);

        if (result.first() == null) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "The first document for player " + player.getName() + " has returned null. It could be that there is no document for this player.");
            return null;
        }

        return new EditPlayer(player);
    }

    public Document getPlayerDocument(Player player) {
        BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId());
        FindIterable<Document> result = mongoCollection.find(query);

        Document playerDocument = result.first();
        if (playerDocument == null) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "The document for player " + player.getName() + " was not found.");
        }

        return playerDocument;
    }

    public Document getOfflinePlayerDocument(OfflinePlayer player) {
        BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId());
        FindIterable<Document> result = mongoCollection.find(query);

        Document playerDocument = result.first();
        if (playerDocument == null) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "The document for player " + player.getName() + " was not found.");
        }

        return playerDocument;
    }

    public EditPlayer editOfflinePlayer(OfflinePlayer player) {
        BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId());
        FindIterable<Document> result = mongoCollection.find(query);

        if (result.first() == null) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "The first document for player " + player.getName() + " has returned null. It could be that there is no document for this player.");
            return null;
        }

        return new EditPlayer(player);
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

    public Listener getJoinListener() {
        return new JoinListener(this);
    }
}
