package stulu.mods.impl.gui;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import stulu.Client;
import stulu.mods.IStuluGUIModRender;
import stulu.mods.config.StuluModGuiPosition;

import java.awt.*;

public class ArmorStatusMod implements IStuluGUIModRender {
    Color color = Color.white;
    @Override
    public void render(StuluModGuiPosition pos) {
        if(rainbow())
            this.color = Client.g().Rainbow;
        int yAdd = 64;
        for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++){
            yAdd -= 16;
            ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
            renderItemStack(itemStack, yAdd, pos);
        }
    }

    @Override
    public void renderDummy(StuluModGuiPosition pos) {
        int yAdd = 0;
        renderItemStack(new ItemStack(Items.diamond_helmet), yAdd, pos);
        yAdd += 16;
        renderItemStack(new ItemStack(Items.diamond_chestplate), yAdd, pos);
        yAdd += 16;
        renderItemStack(new ItemStack(Items.diamond_leggings), yAdd, pos);
        yAdd += 16;
        renderItemStack(new ItemStack(Items.diamond_boots), yAdd, pos);
    }



    private void renderItemStack(ItemStack is, int y, StuluModGuiPosition pos) {
        if(is == null){
            return;
        }
        GL11.glPushMatrix();

        if(is.getItem().isDamageable()){
            double dmg = ((is.getMaxDamage() - is.getItemDamage())/ (double) is.getMaxDamage()) * 100;

            fr.drawString(String.format("%.1f%%", dmg), pos.getPosX()+15, pos.getPosY()+y+5, color.getRGB());
        }
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getPosX() , pos.getPosY()+y);
        GL11.glPopMatrix();
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

    public boolean rainbow() {
        return true;
    }

    @Override
    public String name() {
        return "Armor status";
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
        return 64;
    }

    @Override
    public int getWidth() {
        return 64;
    }

}
