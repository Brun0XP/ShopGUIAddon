package com.github.brun0xp.shopguiaddon.listener;

import com.github.brun0xp.shopguiaddon.Main;
import com.github.brun0xp.shopguiaddon.resource.Message;
import net.brcdev.shopgui.api.event.ShopPreTransactionEvent;
import net.brcdev.shopgui.shop.ShopManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VerifyBankAccount implements Listener {

    private Main main;

    public VerifyBankAccount() {
        this.main = Main.getMain();
    }

    @EventHandler
    public void onPreTransaction(ShopPreTransactionEvent e) {
        if (!main.getConfig().getBoolean("bank.enable")) return;
        if (!main.getEcon().hasAccount(main.getBankName())) {
            new Message("messages.error.bank-not-found").colored().send(e.getPlayer());
            e.setCancelled(true);
            return;
        }
        if (e.getShopAction().equals(ShopManager.ShopAction.BUY)) {
            main.getEcon().depositPlayer(main.getBankName(), e.getPrice());
        } else if (e.getShopAction().equals(ShopManager.ShopAction.SELL) ||
                e.getShopAction().equals(ShopManager.ShopAction.SELL_ALL)){
            if (main.getEcon().has(main.getBankName(), e.getPrice())) {
                main.getEcon().withdrawPlayer(main.getBankName(), e.getPrice());
            } else {
                new Message("messages.bank.without-money", true)
                        .set("balance", main.getEcon().format(main.getEcon().getBalance(main.getBankName())))
                        .colored().send(e.getPlayer());
                e.setCancelled(true);
                return;
            }
        }
    }
}
