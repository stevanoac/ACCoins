package dev.stevanoac.coins;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
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
        return new EditPlayer(this, player);
    }

    public EditPlayer editOfflinePlayer(OfflinePlayer player) {
        return new EditPlayer(this, player);
    }

    public Document getPlayerDocument(Player player) {
        Document document = (Document) mongoCollection.find(new Document("UUID", player.getUniqueId())).first();

        if (document == null) {
            this.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "Unable to find a valid document for " + player.getName() + ".");
            return null;
        }

        return document;
    }

    public Document getOfflinePlayerDocument(OfflinePlayer player) {
        Document document = (Document) mongoCollection.find(new Document("UUID", player.getUniqueId())).first();

        if (document == null) {
            this.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[ACCoins] " + ChatColor.RED + "Unable to find a valid document for " + player.getName() + ".");
            return null;
        }

        return document;
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
