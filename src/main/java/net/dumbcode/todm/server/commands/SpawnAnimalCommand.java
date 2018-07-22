package net.dumbcode.todm.server.commands;

import com.google.common.collect.Lists;
import net.dumbcode.todm.server.creatures.animal.Animal;
import net.dumbcode.todm.server.entities.EntityHandler;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class SpawnAnimalCommand extends CommandBase
{

    @Override
    public String getName()
    {
        return "spawn";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "this needs to be done";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
    {
        AnimalEntity entity = new AnimalEntity(sender.getEntityWorld());
        entity.setPosition(sender.getPositionVector().x, sender.getPositionVector().y, sender.getPositionVector().z);
        sender.getEntityWorld().spawnEntity(entity);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, EntityHandler.ANIMAL_REGISTRY.getValuesCollection().stream().map(Animal::getRegName).map(Object::toString).collect(Collectors.toList())) : Lists.newArrayList();
    }
}