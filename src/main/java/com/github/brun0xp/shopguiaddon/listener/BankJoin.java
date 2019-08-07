package com.github.brun0xp.shopguiaddon.listener;

import com.github.brun0xp.shopguiaddon.Main;
import com.github.brun0xp.shopguiaddon.resource.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BankJoin implements Listener {

    private Main main;

    public BankJoin() {
        this.main = Main.getMain();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().getName().equalsIgnoreCase(main.getBankName())) {
            if (main.getConfig().getBoolean("bank.can-join")) return;
            e.getPlayer().kickPlayer(new Message("messages.bank.cant-join").colored().getString());
        }
    }
}
