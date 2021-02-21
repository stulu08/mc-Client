package stulu.gui.screens.settings.Cosmetics;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import stulu.Client;
import stulu.gui.elements.GuiButtonStulu;
import stulu.gui.elements.GuiOptionsButtonStulu;
import stulu.gui.screens.settings.Cosmetics.subscreen.GuiEditeBlazeEffekt;
import stulu.gui.screens.settings.Cosmetics.subscreen.GuiEditeDragonWings;

import java.io.IOException;

public class GuiScreenCosmetics extends GuiScreen {
    private final GuiScreen field_146505_f;

    /** Reference to the GameSettings object. */
    protected String field_146507_a = "Options";
    private String field_146508_h;

    public GuiScreenCosmetics(GuiScreen p_i45025_1_)
    {
        this.field_146505_f = p_i45025_1_;
    }
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        String CurrentHat = Client.Hats[0];
        String CurrentCape = Client.Capes[0];
        switch(Client.CurrentHat){
            case 0:
                CurrentHat = Client.Hats[0];
                break;
            case 1:
                CurrentHat = Client.Hats[1];
                break;
        }
        switch(Client.CurrentCape){
            case 0:
                CurrentCape = Client.Capes[0];
                break;
            case 1:
                CurrentCape = Client.Capes[1];
            case 2:
                CurrentCape = Client.Capes[2];
                break;
            case 3:
                CurrentCape = Client.Capes[3];
            case 4:
                CurrentCape = Client.Capes[4];
                break;
            case 5:
                CurrentCape = Client.Capes[5];
            case 6:
                CurrentCape = Client.Capes[6];
                break;
            case 7:
                CurrentCape = Client.Capes[7];
                break;
            case 8:
                CurrentCape = Client.Capes[8];
                break;
            case 9:
                CurrentCape = Client.Capes[9];
                break;
        }

        this.field_146507_a = I18n.format("Cosmetics", new Object[0]);


        int i = 0;

        this.StulubuttonList.add(new GuiOptionsButtonStulu(201, this.width / 2 - 155, this.height / 6 + 24, I18n.format("Dragon Wings", new Object[0])));
        this.StulubuttonList.add(new GuiOptionsButtonStulu(202, this.width / 2 + 5, this.height / 6 + 24, I18n.format("Blaze Effect", new Object[0])));
        this.StulubuttonList.add(new GuiOptionsButtonStulu(203, this.width / 2 - 155, this.height / 6 + 48, I18n.format("Hat: "+CurrentHat, new Object[0])));
        this.StulubuttonList.add(new GuiOptionsButtonStulu(204, this.width / 2 + 5, this.height / 6 + 48, I18n.format("Cape: "+CurrentCape, new Object[0])));
        this.StulubuttonList.add(new GuiOptionsButtonStulu(205, this.width / 2 - 155, this.height / 6 + 72, I18n.format("Glow : " + Client.Glow, new Object[0])));
        this.StulubuttonList.add(new GuiOptionsButtonStulu(206, this.width / 2 +5, this.height / 6 + 72, I18n.format("Wither Effect : " + Client.Wither, new Object[0])));



        this.StulubuttonList.add(new GuiButtonStulu(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButtonStulu button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 200) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.field_146505_f);
            }
            if(button.id == 201){
               this.mc.displayGuiScreen(new GuiEditeDragonWings(this));
            }
            if(button.id == 202){
                this.mc.displayGuiScreen(new GuiEditeBlazeEffekt(this));
            }
            if(button.id == 203){
                switch(Client.CurrentHat){
                    case 0:
                        Client.AddCurrentHat();
                        button.displayString = "Hat: "+Client.Hats[1];
                        break;
                    case 1:
                        Client.SetCurrentHat(0);
                        button.displayString = "Hat: "+Client.Hats[0];
                        break;
                }
            }
            if(button.id==204){
                switch(Client.CurrentCape){
                    case 0:
                        button.displayString = "Cape: " + Client.Capes[1];
                        Client.AddCurrentCape();
                        break;
                    case 1:
                        button.displayString = "Cape: " + Client.Capes[2];
                        Client.AddCurrentCape();
                        break;
                    case 2:
                        button.displayString = "Cape: " + Client.Capes[3];
                        Client.AddCurrentCape();
                        break;
                    case 3:
                        button.displayString = "Cape: " + Client.Capes[4];
                        Client.AddCurrentCape();
                        break;
                    case 4:
                        button.displayString = "Cape: " + Client.Capes[5];
                        Client.AddCurrentCape();
                        break;
                    case 5:
                        button.displayString = "Cape: " + Client.Capes[6];
                        Client.AddCurrentCape();
                        break;
                    case 6:
                        button.displayString = "Cape: " + Client.Capes[7];
                        Client.AddCurrentCape();
                        break;
                    case 7:
                        button.displayString = "Cape: " + Client.Capes[8];
                        Client.AddCurrentCape();
                        break;
                    case 8:
                        button.displayString = "Cape: " + Client.Capes[9];
                        Client.AddCurrentCape();
                        break;
                    case 9:
                        button.displayString = "Cape: " + Client.Capes[0];
                        Client.SetCurrentCape(0);
                        break;
                }
            }
            if(button.id == 205){
                Client.setGlow(!Client.Glow);
                button.displayString = "Glow : " + Client.Glow;
            }
            if(button.id == 206){
                Client.setWither(!Client.Wither);
                button.displayString = "Wither Effect : " + Client.Wither;
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
