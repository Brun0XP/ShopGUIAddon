package com.github.brun0xp.shopguiaddon.resource;

import com.github.brun0xp.shopguiaddon.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Message {

    private List<String> source;

    public Message(String path) {
        this(path, false);
    }

    public Message(String path, boolean list) {
        if (list)
            this.source = Main.getMain().getLanguage().getFile().getStringList(path);
        else
            this.source = Collections.singletonList(Main.getMain().getLanguage().getFile().getString(path));
    }

    public Message(String... messages) {
        this.source = Arrays.asList(messages);
    }

    public Message colored() {
        List<String> colored = new ArrayList<>();

        for (String str : source)
            colored.add(ChatColor.translateAlternateColorCodes('&', str));

        return new Message(colored.toArray(new String[colored.size()]));
    }

    public Message set(String target, String replacement) {
        List<String> replaced = new ArrayList<>();

        for (String str : source)
            replaced.add(str.replace("{" + target + "}", replacement));

        return new Message(replaced.toArray(new String[replaced.size()]));
    }

    public String getString() {
        if (source.size() != 1) return "";
        else return source.get(0);
    }

    public void send(CommandSender sender) {
        for (String message : source)
            sender.sendMessage(message);
    }

    public void send(Player player) {
        for (String message : source)
            player.sendMessage(message);
    }

    public void serverBroadcast() {
        for (String message : source)
            Bukkit.getServer().broadcastMessage(message);
    }

    public void playerBroadcast() {
        for (Player player : Bukkit.getOnlinePlayers())
            for (String message : source)
                player.sendMessage(message);
    }
}
