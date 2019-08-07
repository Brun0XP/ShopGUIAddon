package com.github.brun0xp.shopguibank.commands.subcommand;

import com.github.brun0xp.shopguibank.Main;
import com.github.brun0xp.shopguibank.resource.Message;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Balance extends AbstractSubCommand {

    private Main main;

    public Balance() {
        super("balance", "shopguibank.bank.balance", new String[]{}, new String[]{"saldo"});
        this.main = Main.getMain();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] strings) {
        if (!main.getEcon().hasAccount(main.getBankName())) {
            new Message("messages.error.bank-not-found").colored().send(commandSender);
            return true;
        }
        new Message("messages.balance").set("balance", main.getEcon().format(main.getEcon().getBalance(main.getName())))
                .colored().send(commandSender);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return new ArrayList<>();
    }
}
