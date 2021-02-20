package stulu.mods.impl.gui;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import stulu.Client;
import stulu.mods.IStuluGUIModRender;
import stulu.mods.config.StuluModGuiPosition;

import java.awt.*;

public class MainhandDisplayMod implements IStuluGUIModRender {
    @Override
    public void render(StuluModGuiPosition pos) {
        ItemStack is = mc.thePlayer.getHeldItem();
        if(is == null)
            return;
        GL11.glPushMatrix();
        if(is.getItem().isDamageable()){
            double dmg = ((is.getMaxDamage() - is.getItemDamage())/ (double) is.getMaxDamage()) * 100;

            fr.drawString(String.format("%.1f%%", dmg), pos.getPosX()+15, pos.getPosY()+5, color.getRGB());
        }
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getPosX() , pos.getPosY());
        GL11.glPopMatrix();
    }

    @Override
    public void renderDummy(StuluModGuiPosition pos) {
        ItemStack is = new ItemStack(Items.diamond_sword);
        GL11.glPushMatrix();
        if(is.getItem().isDamageable()){
            double dmg = ((is.getMaxDamage() - is.getItemDamage())/ (double) is.getMaxDamage()) * 100;

            fr.drawString(String.format("%.1f%%", dmg), pos.getPosX()+15, pos.getPosY()+5, color.getRGB());
        }
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getPosX() , pos.getPosY());
        GL11.glPopMatrix();
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

    public boolean rainbow() {
        return true;
    }

    @Override
    public String name() {
        return "Main Hand Display";
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
        return 16;
    }

    @Override
    public int getWidth() {
        return 64;
    }
}
