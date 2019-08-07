package com.github.brun0xp.shopguibank.commands.subcommand;

import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.List;

@Getter
public abstract class AbstractSubCommand {

    protected String name;
    protected String permission;
    protected String[] args;
    protected String[] aliases;

    public AbstractSubCommand(String name, String permission, String[] args, String[] aliases) {
        this.name = name;
        this.permission = permission;
        this.args = args;
        this.aliases = aliases;
    }

    public abstract boolean onCommand(CommandSender commandSender, String[] strings);

    public abstract List<String> onTabComplete(CommandSender commandSender, String[] strings);

    public String getUsage(String command) {
        return "/" + command + " " + this.getName() + " " + String.join(" ", args);
    }
}
