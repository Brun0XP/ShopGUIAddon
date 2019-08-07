package com.github.brun0xp.shopguibank.commands.subcommand;

import com.github.brun0xp.shopguibank.Main;
import com.github.brun0xp.shopguibank.resource.Message;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Add extends AbstractSubCommand {

    private Main main;

    public Add() {
        super("add", "shopguibank.bank.add", new String[]{"<amount>"}, new String[]{});
        this.main = Main.getMain();
    }
    @Override
    public boolean onCommand(CommandSender commandSender, String[] strings) {
        if (!main.getEcon().hasAccount(main.getBankName())) {
            new Message("messages.error.bank-not-found").colored().send(commandSender);
            return true;
        }
        if (strings.length == 0) return false;
        try {
            double amount = Double.parseDouble(strings[0]);
            main.getEcon().depositPlayer(main.getBankName(), amount);
            new Message("messages.add").set("amount", main.getEcon().format(amount))
                    .colored().send(commandSender);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return new ArrayList<>();
    }
}
