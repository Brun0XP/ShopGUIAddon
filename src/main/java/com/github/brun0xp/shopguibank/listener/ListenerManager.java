package com.github.brun0xp.shopguibank.listener;

import com.github.brun0xp.shopguibank.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    private Main main;

    public ListenerManager() {
        this.main = Main.getMain();
        this.registerListeners();
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new VerifyBankAccount(), main);
        pm.registerEvents(new BankJoin(), main);
    }

}
