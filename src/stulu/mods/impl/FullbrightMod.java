package stulu.mods.impl;

import net.minecraft.client.Minecraft;
import stulu.Client;
import stulu.mods.config.IStuluModConfig;
import stulu.mods.StuluGUIModinstances;
import stulu.mods.StuluMod;

public class FullbrightMod extends StuluMod {
    public static float defaultGamma = 1;
    public static float fullBrightGamma = 50f;
    @Override
    public void onEnable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = fullBrightGamma;
        System.out.println("Fulbright Mod was enabled");
    }

    @Override
    public void onDisable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = defaultGamma;
        System.out.println("Fulbright Mod was disabled");
    }

    @Override
    public IStuluModConfig config() {
        return new IStuluModConfig() {
            @Override
            public int color() {
                return -1;
            }
            boolean enable = true;

            @Override
            public String Name() {
                return "FulbrightMod";
            }
            @Override
            public void setEnable(boolean value) {
                if(value)
                    StuluGUIModinstances.getFullbrightMod().onEnable();
                else
                    StuluGUIModinstances.getFullbrightMod().onDisable();
                this.enable = value;
                Client.Sfile.ClientINIWrite.save("Mods",Name(),""+value);
            }

            @Override
            public boolean getEnable() {
                return Minecraft.getMinecraft().gameSettings.gammaSetting == 50f;
            }
        };
    }
}
