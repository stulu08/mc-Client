package stulu.mods;

import stulu.Client;
import stulu.mods.impl.CrosshairMod;
import stulu.mods.impl.FullbrightMod;
import stulu.mods.impl.NoFireMod;
import stulu.mods.impl.NoPumpkinBlur;
import stulu.mods.impl.gui.*;

import java.util.Collection;

public class StuluGUIModinstances {
    static stulu.mods.impl.gui.fpsrendermod fpsrendermod;
    static ArmorStatusMod armorStatusMod;
    static PositionMod positionMod;
    static MiniPlayerMod miniPlayerMod;
    static PotionStatus potionStatus;
    static MainhandDisplayMod mainhandDisplayMod;
    static ModRenderer modRenderer;

    static FullbrightMod fullbrightMod;
    static NoPumpkinBlur noPumpkinBlur;
    static NoFireMod noFireMod;
    static CrosshairMod crosshairMod;
    public static void Register(StuluRenderApi api){
        mainhandDisplayMod = new MainhandDisplayMod();
        fpsrendermod = new fpsrendermod();
        armorStatusMod = new ArmorStatusMod();
        positionMod = new PositionMod();
        miniPlayerMod = new MiniPlayerMod();
        potionStatus = new PotionStatus();
        modRenderer = new ModRenderer();
        api.register(positionMod);
        api.register(armorStatusMod);
        api.register(fpsrendermod);
        api.register(mainhandDisplayMod);
        api.register(potionStatus);
        api.register(modRenderer);
        api.register(miniPlayerMod);

        noPumpkinBlur = new NoPumpkinBlur();
        fullbrightMod = new FullbrightMod();
        noFireMod = new NoFireMod();
        crosshairMod = new CrosshairMod();
        api.register(fullbrightMod);
        api.register(noPumpkinBlur);
        api.register(noFireMod);
        api.register(crosshairMod);
    }
    public static FullbrightMod getFullbrightMod() {
        return fullbrightMod;
    }
    public static NoPumpkinBlur getNoPumpkinBlur() {
        return noPumpkinBlur;
    }
    public static NoFireMod getNoFireMod() {
        return noFireMod;
    }
    public static CrosshairMod getCrosshairMod() {
        return crosshairMod;
    }

    public static stulu.mods.impl.gui.fpsrendermod getFpsrendermod() {
        return fpsrendermod;
    }
    public static void init(StuluRenderApi api){
        Collection<StuluMod> mods = api.getRegisteredMods();
        Collection<IStuluGUIModRender> rend = api.getRegisteredRenderers();
        for(StuluMod mod : mods){
            mod.config().setEnable(Client.Sfile.ClientINIRead.getBool("Mods",mod.config().Name()));
        }
        for(IStuluGUIModRender ren : rend){
            ren.setEnabled(Client.Sfile.ClientINIRead.getBool("Mods",ren.name()));
        }
    }
}
