package net.dumbcode.todm.server.commands;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class TODMCommand extends CommandTreeBase
{

    public TODMCommand()
    {
        this.addSubcommand(new SpawnAnimalCommand());
    }

    @Override
    public String getName()
    {
        return "todm";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/todm";
    }
}
