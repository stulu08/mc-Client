package stulu.mods.impl;

import net.minecraft.client.gui.GuiIngame;
import stulu.Client;
import stulu.mods.StuluGUIModinstances;
import stulu.mods.StuluMod;
import stulu.mods.config.IStuluModConfig;

public class CrosshairMod extends StuluMod {
    @Override
    public void onDisable() {
        GuiIngame.setCustomCroshair(true);
    }

    @Override
    public void onEnable() {
        GuiIngame.setCustomCroshair(false);
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
                return "Vanilla Cosshair";
            }

            boolean enable = true;

            public void setEnable(boolean value) {
                if (value)
                    StuluGUIModinstances.getCrosshairMod().onEnable();
                else
                    StuluGUIModinstances.getCrosshairMod().onDisable();
                this.enable = value;
                Client.Sfile.ClientINIWrite.save("Mods",Name(),""+value);
            }

            @Override
            public boolean getEnable() {
                return !GuiIngame.CustomCroshair;
            }
        };
    }
    public class Crossair{

    }
}
