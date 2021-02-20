package stulu.mods;

import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import stulu.events.EventManager;
import stulu.events.EventTarget;
import stulu.events.impl.StuluRenderEvent;
import stulu.events.impl.StuluTickEvent;
import stulu.gui.screens.mods.gui.GuiModPosConfigScreen;
import stulu.gui.screens.mods.gui.GuiModSettingsInGame;
import stulu.mods.config.StuluModGuiPosition;

import java.util.Collection;
import java.util.Set;

public class StuluRenderApi {


    private StuluRenderApi(){}
    private static StuluRenderApi instance = null;
    public static StuluRenderApi getInstance(){
        if(instance != null) {
            return instance;
        }
        instance = new StuluRenderApi();
        EventManager.register(instance);
        return instance;
    }

    private Set<IStuluGUIModRender> registeredRenderers = Sets.newHashSet();
    private Set<StuluMod> registerdMods = Sets.newHashSet();
    private Minecraft mc = Minecraft.getMinecraft();

    public void register(IStuluGUIModRender...renderers){
        for(IStuluGUIModRender render : renderers){
            this.registeredRenderers.add(render);
        }
    }
    public void register(StuluMod...renderers){
        for(StuluMod render : renderers){
            this.registerdMods.add(render);
        }
    }
    public void unregister(StuluMod...renderers){
        for(StuluMod render : renderers){
            this.registerdMods.remove(render);
        }
    }

    public void unregister(IStuluGUIModRender...renderers){
        for(IStuluGUIModRender render : renderers){
            this.registeredRenderers.remove(render);
        }
    }

    public Collection<IStuluGUIModRender> getRegisteredRenderers(){
        return Sets.newHashSet(registeredRenderers);
    }
    public Collection<StuluMod> getRegisteredMods(){
        return Sets.newHashSet(registerdMods);
    }
    public void openConfigScreen(){
        mc.displayGuiScreen(new GuiModSettingsInGame(this));
    }
    public void openConfigPositionScreen(){
        mc.displayGuiScreen(new GuiModPosConfigScreen(this));
    }
    @EventTarget
    public void onRender(StuluRenderEvent e){
        if(mc.currentScreen == null || mc.currentScreen instanceof GuiChat){
            for(IStuluGUIModRender renderer : registeredRenderers){
                if (!mc.gameSettings.showDebugInfo)
                    callRenderer(renderer);
            }
        }
    }

    private void callRenderer(IStuluGUIModRender renderer) {
        if(!renderer.getEnabled()){
            return;
        }
        StuluModGuiPosition pos = renderer.load();
        if(pos == null){
            pos = new StuluModGuiPosition(0.5,0.5);
        }
        renderer.render(pos);
    }
    @EventTarget
    public void onTick(StuluTickEvent e){
        for(StuluMod renderer : registerdMods){
            if (!mc.gameSettings.showDebugInfo)
                callMod(renderer);
        }
    }
    private void callMod(StuluMod mod){
        if(mod.config().getEnable())
            mod.onUpdate();
    }
}
