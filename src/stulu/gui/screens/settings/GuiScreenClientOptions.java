package stulu.gui.screens.settings;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import stulu.gui.elements.GuiButtonStulu;
import stulu.gui.screens.mods.GuiScreenModOptions;
import stulu.gui.screens.settings.Cosmetics.GuiScreenCosmetics;

import java.io.IOException;

public class GuiScreenClientOptions extends GuiScreen {
    private final GuiScreen field_146505_f;


    protected String field_146507_a = "Options";
    private String field_146508_h;

    public GuiScreenClientOptions(GuiScreen p_i45025_1_, GameSettings p_i45025_2_)
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


        int i = 0;

        this.StulubuttonList.add(new GuiButtonStulu(201, this.width / 2 - 100, this.height / 6 + 24,I18n.format("Cosmetics", new Object[0])));
        this.StulubuttonList.add(new GuiButtonStulu(202, this.width / 2 - 100, this.height / 6 + 48,I18n.format("Mod Options", new Object[0])));


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
               this.mc.displayGuiScreen(new GuiScreenCosmetics(this));
            }
            if(button.id == 202){
                this.mc.displayGuiScreen(new GuiScreenModOptions(this));
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
