package stulu.gui.screens.settings.Cosmetics.subscreen;

import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import stulu.Client;
import stulu.cosmetics.CosmeticController;
import stulu.gui.elements.GuiButtonStulu;
import stulu.gui.elements.StuluGuiSlider;

import java.io.IOException;

public class GuiEditeBlazeEffekt extends GuiScreen{
    private final GuiScreen field_146505_f;

    float[] RodColor = CosmeticController.getBlazeEffectColor;
    int[] RodColorAsInt;
    protected String field_146507_a = "Options";
    private String field_146508_h;
    public boolean Enabled;
    public GuiEditeBlazeEffekt(GuiScreen p_i45025_1_)
    {
        this.field_146505_f = p_i45025_1_;

    }
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        Enabled = Client.BlazeEffect;
        this.field_146507_a = I18n.format("Stulu", new Object[0]);
        RodColorAsInt = new int[]{(int)(RodColor[0]*100), (int)(RodColor[1]*100), (int)(RodColor[2]*100), (int)(RodColor[3]*100)};

        int i = 0;

        GuiPageButtonList.GuiResponder responder = new GuiPageButtonList.GuiResponder() {
            @Override
            public void func_175321_a(int p_175321_1_, boolean p_175321_2_) {

            }

            @Override
            public void onTick(int id, float value) {
                if(id == 1)
                    RodColor[0] = value/100;
                if(id == 2)
                    RodColor[1] = value/100;
                if(id == 3)
                    RodColor[2] = value/100;
                if(id == 4)
                    RodColor[3] = value/100;
                RodColorAsInt = new int[]{(int)(RodColor[0]*100), (int)(RodColor[1]*100), (int)(RodColor[2]*100), (int)(RodColor[3]*100)};
                CosmeticController.setGetBlazeEffectColor(RodColor);
            }

            @Override
            public void func_175319_a(int p_175319_1_, String p_175319_2_) {

            }
        };
        StuluGuiSlider.FormatHelper formatHelper = new StuluGuiSlider.FormatHelper() {
            @Override
            public String getText(int id, String name, float value) {
                switch (id){
                    case 1:
                        return "Red : " + RodColorAsInt[0];
                    case 2:
                        return "Green : " + RodColorAsInt[1];
                    case 3:
                        return "Blue : " + RodColorAsInt[2];
                    case 4:
                        return "Alpha : " + RodColorAsInt[3];
                }
                return "Undefined";
            }
        };
        this.StulubuttonList.add(new StuluGuiSlider(responder,1,this.width / 2 - 155, this.height / 6 + 24, "ColorRed", 0,100,RodColorAsInt[0],formatHelper));
        this.StulubuttonList.add(new StuluGuiSlider(responder,2,this.width / 2 +5, this.height / 6 + 24, "ColorGreen", 0,100,RodColorAsInt[1],formatHelper));
        this.StulubuttonList.add(new StuluGuiSlider(responder,3,this.width / 2 - 155, this.height / 6 + 48, "ColorBlue", 0,100,RodColorAsInt[2],formatHelper));
        this.StulubuttonList.add(new StuluGuiSlider(responder,4,this.width / 2 +5, this.height / 6 + 48, "ColorAlpha", 0,100,RodColorAsInt[3],formatHelper));

        this.StulubuttonList.add(new GuiButtonStulu(201, this.width/2-100, this.height / 6 + 72, I18n.format("Enable : " + Enabled, new Object[0])));
        this.StulubuttonList.add(new GuiButtonStulu(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButtonStulu button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.field_146505_f);
            }
            if(button.id == 201){

                Client.setBlazeEffect(!Enabled);
                Enabled = !Enabled;
                button.displayString = "Enable : " + Enabled;
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
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawEntityPlayerOnScreen(50, this.height-5, 30, this.width/2, (this.height / 4)*3, mc.thePlayer);
    }
}
