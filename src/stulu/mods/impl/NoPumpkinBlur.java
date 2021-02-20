package stulu.mods.impl;

import net.minecraft.client.gui.GuiIngame;
import stulu.Client;
import stulu.mods.config.IStuluModConfig;
import stulu.mods.StuluGUIModinstances;
import stulu.mods.StuluMod;

public class NoPumpkinBlur extends StuluMod {
    @Override
    public void onEnable() {
        GuiIngame.setPumpkinBlur(false);
        System.out.println("Pumpkin blur was disabled");
    }

    @Override
    public void onDisable() {
        GuiIngame.setPumpkinBlur(true);
        System.out.println("Pumpkin blur was enabled");
    }

    @Override
    public IStuluModConfig config() {
        return new IStuluModConfig() {
            @Override
            public int color() {
                return -1;
            }

            @Override
            public String Name() {
                return "NoPumpkinBlur";
            }
            boolean enable = true;
            public void setEnable(boolean value) {
                if(value)
                    StuluGUIModinstances.getNoPumpkinBlur().onEnable();
                else
                    StuluGUIModinstances.getNoPumpkinBlur().onDisable();
                this.enable = value;
                Client.Sfile.ClientINIWrite.save("Mods",Name(),""+value);
            }

            @Override
            public boolean getEnable() {
                return !GuiIngame.pumpkinBlur;
            }
        };
    }
}
