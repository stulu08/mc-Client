package stulu.mods.impl.gui;

import net.minecraft.util.ResourceLocation;
import stulu.Client;
import stulu.gui.elements.GradientFontRenderer;
import stulu.mods.IStuluGUIModRender;
import stulu.mods.StuluMod;
import stulu.mods.StuluRenderApi;
import stulu.mods.config.StuluModGuiPosition;

import java.awt.*;

public class ModRenderer implements IStuluGUIModRender {
    private Color color = Color.white;
    @Override
    public void render(StuluModGuiPosition pos) {
        if(rainbow())
            this.color = Client.g().Rainbow;

        StuluMod[]mods = StuluRenderApi.getInstance().getRegisteredMods().toArray(new StuluMod[0]);
        int yAdd = 0;
        for(int i = 0; i != mods.length; i++){
            if(mods[i].config().getEnable()){
                fr.drawString(mods[i].config().Name(), pos.getPosX(), pos.getPosY()+yAdd, color.getRGB(), false);
                yAdd += 14;
            }
        }
    }

    @Override
    public void renderDummy(StuluModGuiPosition pos) {

        StuluMod[]mods = StuluRenderApi.getInstance().getRegisteredMods().toArray(new StuluMod[0]);

        int yAdd = 0;
        for(int i = 0; i != mods.length; i++){
           if(mods[i].config().getEnable()){
               fr.drawString(mods[i].config().Name(), pos.getPosX(), pos.getPosY() + yAdd, color.getRGB());
               yAdd += 14;
           }

        }
    }

    GradientFontRenderer gfr = new GradientFontRenderer(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine, false);

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


    public boolean rainbow() {
        return true;
    }

    @Override
    public String name() {
        return "Mods";
    }

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

    @Override
    public int getHeight() {
        int length = StuluRenderApi.getInstance().getRegisteredMods().size();
        int s = 0;
        for (int i = 0; i < length; i++) {
            if(StuluRenderApi.getInstance().getRegisteredMods().toArray(new StuluMod[0])[i].config().getEnable())
                s++;
        }
        if(s == 0)s=1;
        return (fr.FONT_HEIGHT+2)*s;
    }

    @Override
    public int getWidth() {
        StuluMod[]mods = StuluRenderApi.getInstance().getRegisteredMods().toArray(new StuluMod[0]);
        String longest = "---";
        if(mods.length <= 0)
            return fr.getStringWidth("-----------");
        for(StuluMod mod : mods){
            if(mod.config().getEnable())
                if(longest.length() <= mod.config().Name().length())
                    longest = mod.config().Name();
        }
        return fr.getStringWidth(longest);
    }
}
