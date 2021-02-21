package stulu.commands.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import stulu.commands.IStuluCommand;
import stulu.mods.StuluGUIModinstances;

public class StuluFullbrightCommand implements IStuluCommand {
    @Override
    public void onCommand(String[] args, GuiScreen guiScreen) {
        StuluGUIModinstances.getFullbrightMod().config().setEnable(Minecraft.getMinecraft().gameSettings.gammaSetting != 50);
        guiScreen.sendClientMessage("Fullbright was set to: " + StuluGUIModinstances.getFullbrightMod().config().getEnable());
    }

    @Override
    public String Prefix() {
        return "fb";
    }

    @Override
    public String Name() {
        return "Fullbright Command";
    }

    @Override
    public String usage() {
        return "toggle fulbright";
    }
}
