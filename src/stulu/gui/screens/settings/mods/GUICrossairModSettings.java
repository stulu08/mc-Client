package stulu.gui.screens.settings.mods;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.resources.I18n;
import stulu.Client;

import java.io.IOException;

public class GUICrossairModSettings extends GuiScreen {
    private final GuiScreen field_146505_f;


    protected String field_146507_a = "Options";
    private String field_146508_h;

    public GUICrossairModSettings(GuiScreen p_i45025_1_)
    {
        this.field_146505_f = p_i45025_1_;

    }
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.field_146507_a = I18n.format("Stulu Crosshair Settings", new Object[0]);

        GuiPageButtonList.GuiResponder responder = new GuiPageButtonList.GuiResponder() {
            @Override
            public void func_175321_a(int p_175321_1_, boolean p_175321_2_) {

            }

            @Override
            public void onTick(int id, float value) {
                switch(id){
                    case 1:
                        Client.SCrossair.offsetX = (int)value;
                        Client.SCrossair.ini.ClientINIWrite.save("Offset","x",(int)value+"");
                        break;
                    case 2:
                        Client.SCrossair.offsetY = (int)value;
                        Client.SCrossair.ini.ClientINIWrite.save("Offset","y",(int)value+"");
                        break;
                    case 3:
                        Client.SCrossair.thickNess = (int)value;
                        Client.SCrossair.ini.ClientINIWrite.save("Other","thickness",(int)value+"");
                        break;
                    case 4:
                        Client.SCrossair.length = (int)value;
                        Client.SCrossair.ini.ClientINIWrite.save("Other","length",(int)value+"");
                        break;
                    case 5:
                        Client.SCrossair.dotSize = (int)value;
                        Client.SCrossair.ini.ClientINIWrite.save("Other","dotSize",(int)value+"");
                        break;
                    case 6:
                        Client.SCrossair.colorH = 1.0f / 360 * value;
                        Client.SCrossair.ini.ClientINIWrite.save("Color","hue",1.0f / 360 * value+"");
                        break;
                    case 7:
                        Client.SCrossair.colorS = 1.0f / 100 * value;
                        Client.SCrossair.ini.ClientINIWrite.save("Color","saturation",1.0f / 100 * value+"");
                        break;
                    case 8:
                        Client.SCrossair.colorB = 1.0f / 100 * value;
                        Client.SCrossair.ini.ClientINIWrite.save("Color","brightness",1.0f / 100 * value+"");
                        break;
                }
            }

            @Override
            public void func_175319_a(int p_175319_1_, String p_175319_2_) {

            }
        };
        GuiSlider.FormatHelper formatHelper = (id, name, value) -> {
            switch (id){
                case 1:
                    return "Offset X: " + Client.SCrossair.offsetX;
                case 2:
                    return "Offset Y: " + Client.SCrossair.offsetY;
                case 3:
                    return "Thickness: " + Client.SCrossair.thickNess;
                case 4:
                    return "Length: " + Client.SCrossair.length;
                case 5:
                    return "Dot Size: " + Client.SCrossair.dotSize;
                case 6:
                    return "Color-Hue: " + (int)(Client.SCrossair.colorH * 360);
                case 7:
                    return "Color-Saturation: " + (int)(Client.SCrossair.colorS * 100);
                case 8:
                    return "Color-Brightness: " + (int)(Client.SCrossair.colorB * 100);
            }
            return "Undefined";
        };
        this.buttonList.add(new GuiSlider(responder,1,this.width / 2 - 155, this.height / 6 + 24, "OffsetX", 0,10, Client.SCrossair.offsetX, formatHelper));
        this.buttonList.add(new GuiSlider(responder,2,this.width / 2 +5, this.height / 6 + 24, "OffsetY", 0,10,Client.SCrossair.offsetY,formatHelper));
        this.buttonList.add(new GuiSlider(responder,3,this.width / 2 - 155, this.height / 6 + 48, "Thickness", 0,5, Client.SCrossair.thickNess, formatHelper));
        this.buttonList.add(new GuiSlider(responder,4,this.width / 2 +5, this.height / 6 + 48, "Length", 0,10,Client.SCrossair.length,formatHelper));
        this.buttonList.add(new GuiSlider(responder,5,this.width / 2 - 155, this.height / 6 + 72, "DotSize", 0,5, Client.SCrossair.dotSize, formatHelper));
        this.buttonList.add(new GuiSlider(responder,6,this.width / 2 +5, this.height / 6 + 72, "Hue", 0,360,Client.SCrossair.colorH,formatHelper));
        this.buttonList.add(new GuiSlider(responder,7,this.width / 2 - 155, this.height / 6 + 96, "Saturation", 0,100, Client.SCrossair.colorS, formatHelper));
        this.buttonList.add(new GuiSlider(responder,8,this.width / 2 +5, this.height / 6 + 96, "Brightness", 0,100,Client.SCrossair.colorB,formatHelper));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.field_146505_f);
            }

        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.field_146507_a, this.width / 2, 15, 16777215);
        this.drawCustomCrossHair(this.width / 2, this.height / 6 + 130);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
