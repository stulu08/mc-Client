package stulu.gui.screens.mods.gui;

import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import stulu.Client;
import stulu.gui.elements.*;
import stulu.mods.IStuluGUIModRender;
import stulu.mods.StuluMod;
import stulu.mods.StuluRenderApi;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class GuiModSettingsInGame extends GuiScreen {
    private final HashMap<Integer, StuluMod> mods = new HashMap<Integer, StuluMod>();
    private final HashMap<Integer, IStuluGUIModRender> renderers = new HashMap<Integer, IStuluGUIModRender>();
    Collection<StuluMod> registeredRenderers;
    Collection<IStuluGUIModRender> registeredGUIRenderers;
    StuluRenderApi api;
    float zb;
    public GuiModSettingsInGame(StuluRenderApi api){
        this.api = api;
        registeredRenderers = api.getRegisteredMods();
        registeredGUIRenderers = api.getRegisteredRenderers();
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawSRect(0,0,(int)(this.width/1.5),this.height, new Color(45,88,102).getRGB());
        UnicodeFontRenderer ufr = UnicodeFontRenderer.getFontOnPC("arial",50);

        zb = this.zLevel;
        this.zLevel = 198;
        ufr.drawString("Stulu",(((int)(width/1.5)/2)-(ufr.getStringWidth("Stulu ")/2))-1,1,Color.WHITE.getRGB());
        this.drawGradientRect(0, (this.height/8)*7, (int)(this.width/1.5), this.height, Color.DARK_GRAY.getRGB(), Color.DARK_GRAY.getRGB());
        this.zLevel = zb;
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {

        super.updateScreen();

    }
    @Override
    public void drawBackground(int tint) {

    }

    @Override
    public void initGui() {

        this.StulubuttonList.add(new GuiMiniButtonStulu(300,(int)((this.width/1.5)*0.75),this.height-30,20,20,"",200));
        this.StulubuttonList.add(new StuluCheckButton(301,(int)((this.width/1.5)-50),this.height-30,20,"Rainbow",Client.getINSTANCE().isRainbow, 201));

        calcButtons();

        GuiPageButtonList.GuiResponder responder = new GuiPageButtonList.GuiResponder() {
            @Override
            public void func_175321_a(int p_175321_1_, boolean p_175321_2_) {

            }

            @Override
            public void onTick(int id, float value) {
                if(id == 101)
                    Client.getINSTANCE().setHueSpeed(value);
                if(id == 102)
                    Client.getINSTANCE().setHue(value);
            }

            @Override
            public void func_175319_a(int p_175319_1_, String p_175319_2_) {

            }
        };
        StuluGuiSlider.FormatHelper formatHelper = new StuluGuiSlider.FormatHelper() {
            @Override
            public String getText(int id, String name, float value) {
                switch (id){
                    case 101:
                        return "Chroma Speed : " + Client.getINSTANCE().gethSpeed();
                    case 102:
                        return "Color Hue : " + (int)(Client.getINSTANCE().getHue() * 360f);
                }
                return "Undefined";
            }
        };

        this.StulubuttonList.add(new StuluGuiSlider(responder,101,5,this.height-21,"ChromaSlider",0.1f,5f, Client.getINSTANCE().gethSpeed(),formatHelper,200));

        this.StulubuttonList.add(new StuluGuiSlider(responder,102,5,this.height-40,"HueSlider",0f,360f, (int)(Client.getINSTANCE().getHue()*360),formatHelper,200,true));

        super.initGui();
    }
    void calcButtons(){
        int buttonDEX = 0;
        int row = 1;
        int MaxRow = 4;
        int ty = this.width/6;
        int tx = ty/4;

        int offsetX = tx;
        int offsetY = ty/3;
        int w = tx*2;
        int h = ty/2;



        for(StuluMod mod : registeredRenderers){
            this.StulubuttonList.add(new GuiOptinosModButtonStulu(buttonDEX,offsetX,offsetY,w,h,Color.CYAN,Color.GRAY,Color.RED,mod));
            this.mods.put(buttonDEX,mod);
            if(row == MaxRow) {
                row = 1;
                offsetY += h+ty/2;
                offsetX = tx;
            }
            else{
                offsetX += w + (tx*2);
                row++;
            }
            buttonDEX++;

        }
        for(IStuluGUIModRender ren : registeredGUIRenderers){
            this.zLevel = 201;
            this.StulubuttonList.add(new GuiOptinosModButtonStulu(buttonDEX,offsetX,offsetY,w,h,Color.CYAN,Color.GRAY,Color.RED,ren));
            this.zLevel = zb;
            this.renderers.put(buttonDEX,ren);

            if(row == MaxRow) {
                row = 1;
                offsetY += h+ty/2;
                offsetX = tx;
            }
            else{
                offsetX += w + (tx*2);
                row++;
            }
            buttonDEX++;
        }
    }
    @Override
    protected void actionPerformed(GuiButtonStulu button) throws IOException {
        if (button.enabled)
        {
            if (button.id == 300)
            {
                api.openConfigPositionScreen();
            }
            if(button.id == 301){
                StuluCheckButton b = (StuluCheckButton)button;
                b.change();
                Client.getINSTANCE().setRainbow(b.value);
            }

        }
        for (int i = 0; i != mods.size();i++){
            StuluMod mod = mods.get(i);
            if(button.id == i){
                mod.config().setEnable(!mod.config().getEnable());
            }

        }
        for (int i = mods.size(); i != renderers.size()+ mods.size(); i++){
            IStuluGUIModRender ren = renderers.get(i);
            if(button.id == i){
                ren.setEnabled(!ren.getEnabled());
            }

        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if(keyCode == Keyboard.KEY_ESCAPE){
            this.mc.displayGuiScreen(null);
        }
    }

}
