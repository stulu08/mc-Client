package stulu.commands.impl;

import net.minecraft.client.gui.GuiScreen;
import stulu.commands.IStuluCommand;

public class StuluSayCommand implements IStuluCommand {
    @Override
    public void onCommand(String[] args, GuiScreen guiScreen) {
        StringBuilder msg = new StringBuilder();
        for(String a : args){
            msg.append(a).append(" ");
        }
        guiScreen.sendChatMessage(msg.toString(),true);
    }

    @Override
    public String Prefix() {
        return "say";
    }

    @Override
    public String Name() {
        return "Say Command";
    }

    @Override
    public String usage() {
        return "say something";
    }
}
