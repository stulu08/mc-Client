package stulu.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import stulu.mods.config.StuluModGuiPosition;

import java.io.IOException;

public interface IStuluGUIModRender {
    void render(StuluModGuiPosition pos);
    void renderDummy(StuluModGuiPosition pos);
    boolean getEnabled();
    void setEnabled(boolean value);
    String name();
    public FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
    public Minecraft mc = Minecraft.getMinecraft();
    StuluModGuiPosition load();
    void Save(StuluModGuiPosition _pos) throws IOException;
    int getHeight();
    int getWidth();
}
