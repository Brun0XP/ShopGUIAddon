package com.github.brun0xp.shopguibank.commands.subcommand;

import com.github.brun0xp.shopguibank.Main;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Transfer extends AbstractSubCommand {

    private Main main;

    public Transfer() {
        super("transfer", "shopguibank.bank.transfer", new String[]{"<amount>"}, new String[]{"transferir"});
        this.main = Main.getMain();
    }
    @Override
    public boolean onCommand(CommandSender commandSender, String[] strings) {
        commandSender.sendMessage("§a§lBank §7» §6Coming soon...");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return new ArrayList<>();
    }
}
