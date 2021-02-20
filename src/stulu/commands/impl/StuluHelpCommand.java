package stulu.commands.impl;

import net.minecraft.client.gui.GuiScreen;
import stulu.commands.IStuluCommand;

public class StuluHelpCommand implements IStuluCommand {
    @Override
    public void onCommand(String[] args, GuiScreen guiScreen) {
        guiScreen.sendClientMessage("Commands:\n-help\n-f/fb --> Fulbright toggle");
    }

    @Override
    public String Prefix() {
        return "help";
    }

    @Override
    public String Name() {
        return "Help";
    }
}
