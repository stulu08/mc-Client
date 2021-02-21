package stulu.commands.impl;

import net.minecraft.client.gui.GuiScreen;
import stulu.commands.IStuluCommand;
import stulu.commands.StuluCommandApi;

public class StuluHelpCommand implements IStuluCommand {
    @Override
    public void onCommand(String[] args, GuiScreen guiScreen) {
        guiScreen.sendClientMessage("Stulu Commands");
        for (int i = 0; i != StuluCommandApi.getInstance().commands.size(); i++) {
            String prefix = StuluCommandApi.getInstance().commands.keySet().toArray(new String[0])[i];
            IStuluCommand command = StuluCommandApi.getInstance().commands.values().toArray(new IStuluCommand[0])[i];
            guiScreen.sendClientMessage("." + prefix + " -> " + command.usage(),false);
        }
    }

    @Override
    public String Prefix() {
        return "help";
    }

    @Override
    public String Name() {
        return "Help";
    }

    @Override
    public String usage() {
        return "Displays this list";
    }
}
