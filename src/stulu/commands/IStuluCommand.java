package stulu.commands;

import net.minecraft.client.gui.GuiScreen;

public interface IStuluCommand {
    void onCommand(String args[], GuiScreen guiScreen);
    String Prefix();
    String Name();
}
