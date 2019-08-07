package com.github.brun0xp.shopguiaddon.commands;

import com.github.brun0xp.shopguiaddon.commands.manager.AbstractCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CentralBank extends AbstractCommand {

    public CentralBank() {
        super("centralbank", "shopguiaddon.centralbank", new String[]{}, new String[]{"bancocentral"});
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] strings) {
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }
}
