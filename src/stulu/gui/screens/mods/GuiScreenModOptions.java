package stulu.gui.screens.mods;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import stulu.gui.elements.GuiButtonStulu;
import stulu.gui.elements.GuiOptionsButtonStulu;
import stulu.gui.screens.settings.mods.GUICrossairModSettings;

import java.io.IOException;

public class GuiScreenModOptions extends GuiScreen {
    private final GuiScreen field_146505_f;


    protected String field_146507_a = "Options";
    private String field_146508_h;

    public GuiScreenModOptions(GuiScreen p_i45025_1_)
    {
        this.field_146505_f = p_i45025_1_;

    }
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {

        this.field_146507_a = I18n.format("Stulu", new Object[0]);




        int wL = this.width / 2 - 155;
        int wR = this.width / 2 + 5;
        int h = this.height / 16;
        this.StulubuttonList.add(new GuiOptionsButtonStulu(201,wL, h*4, "Crosshair"));
        //this.StulubuttonList.add(new GuiOptionsButtonStulu(201,wR, h*4, "Crosshair"));
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
            if(button.id == 201)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GUICrossairModSettings(this));
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
    }
}
