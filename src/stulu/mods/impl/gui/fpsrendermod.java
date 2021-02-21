package stulu.mods.impl.gui;

import net.minecraft.client.Minecraft;
import stulu.Client;
import stulu.mods.IStuluGUIModRender;
import stulu.mods.config.StuluModGuiPosition;

import java.awt.*;

public class fpsrendermod implements IStuluGUIModRender {
    @Override
    public void render(StuluModGuiPosition pos) {
        if(rainbow())
            this.color = Client.g().Rainbow;
        fr.drawString("FPS "+ Minecraft.getDebugFPS(), pos.getPosX(), pos.getPosY(),color.getRGB());
    }

    @Override
    public void renderDummy(StuluModGuiPosition pos) {
        fr.drawString("FPS 9999" , pos.getPosX(), pos.getPosY(),color.getRGB());
    }

    @Override
    public boolean getEnabled() {
        return enable;
    }

    @Override
    public void setEnabled(boolean value) {
        this.enable = value;
        Client.Sfile.ClientINIWrite.save("Mods",name(),""+value);
    }
    public boolean enable = true;
    Color color = Color.white;




    public boolean rainbow() {
        return true;
    }

    @Override
    public String name() {
        return "FPS Render Mod";
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT+2;
    }

    @Override
    public int getWidth() {
        return fr.getStringWidth("FPS 9999");
    }

    //StuluModGuiPosition pos = new StuluModGuiPosition(2, 2);

    @Override
    public StuluModGuiPosition load() {
        //return pos;
        String s = Client.getINSTANCE().Sfile.ClientINIRead.get("Mods",this.name() + " Pos");
        if(s == null)
            return null;
        String[] pos = s.split(",");
        if(pos.length < 2)
            return null;
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);
        return new StuluModGuiPosition(x,y);
    }

    @Override
    public void Save(StuluModGuiPosition _pos) {
        //this.pos = _pos;

            Client.Sfile.ClientINIWrite.save("Mods",this.name() + " Pos",_pos.toString());


    }

}
