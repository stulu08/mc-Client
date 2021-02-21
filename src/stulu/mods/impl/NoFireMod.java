package stulu.mods.impl;

import net.minecraft.client.renderer.ItemRenderer;
import stulu.Client;
import stulu.mods.config.IStuluModConfig;
import stulu.mods.StuluGUIModinstances;
import stulu.mods.StuluMod;

public class NoFireMod extends StuluMod {
    @Override
    public void onDisable() {
        ItemRenderer.setRenderFire(true);
    }

    @Override
    public void onEnable() {
        ItemRenderer.setRenderFire(false);
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
                return "NoFire";
            }

            boolean enable = true;

            public void setEnable(boolean value) {
                if (value)
                    StuluGUIModinstances.getNoFireMod().onEnable();
                else
                    StuluGUIModinstances.getNoFireMod().onDisable();
                this.enable = value;
                Client.Sfile.ClientINIWrite.save("Mods",Name(),""+value);
            }

            @Override
            public boolean getEnable() {
                return !ItemRenderer.RenderFire;
            }
        };
    }
}
