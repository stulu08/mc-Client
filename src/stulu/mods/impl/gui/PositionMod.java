package stulu.mods.impl.gui;

import net.minecraft.util.BlockPos;
import stulu.Client;
import stulu.mods.IStuluGUIModRender;
import stulu.mods.config.StuluModGuiPosition;

import java.awt.*;

public class PositionMod implements IStuluGUIModRender {
    StuluModGuiPosition pos = new StuluModGuiPosition(2, 160);
    public boolean rainbow = true;
    @Override
    public void render(StuluModGuiPosition pos) {
        if(rainbow)
            this.color = Client.g().Rainbow;
        int yAdd = 0;
        fr.drawString("x : " + String.format("%.1f", mc.thePlayer.posX), pos.getPosX(), pos.getPosY()+(yAdd), color.getRGB());
        yAdd += 12;
        fr.drawString("y : " + String.format("%.1f", mc.thePlayer.posY), pos.getPosX(),pos.getPosY()+(yAdd), color.getRGB());
        yAdd += 12;
        fr.drawString("z : " + String.format("%.1f", mc.thePlayer.posZ), pos.getPosX(),pos.getPosY()+(yAdd), color.getRGB());
        yAdd += 12;
        fr.drawString("Biome : " + mc.theWorld.getBiomeGenForCoords(new BlockPos(mc.thePlayer)).biomeName, pos.getPosX(),pos.getPosY()+(yAdd), color.getRGB());
    }

    @Override
    public void renderDummy(StuluModGuiPosition pos) {
        int yAdd = 0;
        fr.drawString("x : 30000000.0", pos.getPosX(), pos.getPosY()+(yAdd), color.getRGB());
        yAdd += 12;
        fr.drawString("y : 30000000.0", pos.getPosX(),pos.getPosY()+(yAdd), color.getRGB());
        yAdd += 12;
        fr.drawString("z : 30000000.0", pos.getPosX(),pos.getPosY()+(yAdd), color.getRGB());
        yAdd += 12;
        fr.drawString("Biome : Extreme Hills", pos.getPosX(),pos.getPosY()+(yAdd), color.getRGB());
    }

    Color color = Color.white;

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

    @Override
    public String name() {
        return "Position Mod";
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
        int height = 0;
        for(int i = 1; i!=4;i++){
            height += 14;
        }
        return height;
    }

    @Override
    public int getWidth() {
        return fr.getStringWidth("Biome : Extreme Hills");
    }
}
