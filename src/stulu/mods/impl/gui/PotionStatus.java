package stulu.mods.impl.gui;

import net.minecraft.potion.PotionEffect;
import stulu.Client;
import stulu.mods.IStuluGUIModRender;
import stulu.mods.config.StuluModGuiPosition;

import java.awt.*;

public class PotionStatus implements IStuluGUIModRender {
    public boolean rainbow = true;
    @Override
    public void render(StuluModGuiPosition pos) {
        if(rainbow)
            this.color = Client.g().Rainbow;
        PotionEffect[] effects = mc.thePlayer.getActivePotionEffects().toArray(new PotionEffect[0]);
        if(effects.length <= 0)
            return;
        int yAdd = 0;
        for(int i=0; i!= effects.length;i++){
            PotionEffect e = effects[i];
            String potionname = e.getEffectName();
            StringBuilder sb = new StringBuilder(potionname);
            sb.delete(0,7);
            String name = sb.toString().substring(0,1).toUpperCase() + sb.toString().substring(1).toLowerCase();
            int seconds = e.getDuration()/20;
            String dura;
            int minutes = (seconds % 3600) / 60;
            int secondss = seconds % 60;
            dura = String.format("%02d:%02d", minutes, secondss);

            fr.drawString(dura + " " + name, pos.getPosX(),pos.getPosY()+yAdd,color.getRGB());
            yAdd += 14;
        }
    }

    @Override
    public void renderDummy(StuluModGuiPosition pos) {
        PotionEffect[] effects = mc.thePlayer.getActivePotionEffects().toArray(new PotionEffect[0]);

        if(effects.length <= 0){
            effects = new PotionEffect[]{new PotionEffect(1,500), new PotionEffect(2, 720)};
        }
        int yAdd = 0;
        for(int i=0; i!= effects.length;i++){
            PotionEffect e = effects[i];
            String potionname = e.getEffectName();

            StringBuilder sb = new StringBuilder(potionname);
            sb.delete(0,7);
            String name = sb.toString().substring(0,1).toUpperCase() + sb.toString().substring(1).toLowerCase();
            int seconds = e.getDuration()/20;
            String dura;
            int minutes = (seconds % 3600) / 60;
            int secondss = seconds % 60;
            dura = String.format("%02d:%02d", minutes, secondss);

            fr.drawString(dura + " " + name, pos.getPosX(),pos.getPosY()+yAdd,color.getRGB());
            yAdd += 14;
        }
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
        return "Potion Status";
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
        int length = mc.thePlayer.getActivePotionEffects().toArray(new PotionEffect[0]).length;
        return (fr.FONT_HEIGHT+2)*length;
    }

    @Override
    public int getWidth() {
        PotionEffect[] effects = mc.thePlayer.getActivePotionEffects().toArray(new PotionEffect[0]);
        String longest = "---";
        if(effects.length <= 0)
            return fr.getStringWidth("-----------");
        for(PotionEffect mod : effects){
            if(longest.length() <= mod.getEffectName().length())
                longest = mod.getEffectName();
        }
        return fr.getStringWidth(longest);
    }
}
