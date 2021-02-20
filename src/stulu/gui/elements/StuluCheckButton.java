package stulu.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class StuluCheckButton extends GuiButtonStulu{
    public boolean value;
    public StuluCheckButton(int buttonId, int x, int y, int widthIn, String buttonText,boolean defaultValue,float z) {
        super(buttonId, x, y, widthIn, widthIn, buttonText);
        this.zLevel = z;
        this.value = defaultValue;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(red,green,blue,alpha);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + i * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);


            RenderButton(xPosition,yPosition,width,height,fontrenderer);
            int j = 14737632;
            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }

            float zb = fontrenderer.zLevel;
            fontrenderer.zLevel = this.zLevel;
            this.drawCenteredString(fontrenderer,displayString,this.xPosition+(this.width/2),this.yPosition+(this.height/2), Color.white.getRGB());
            fontrenderer.zLevel = zb;
        }

    }

    private void RenderButton(int x, int y, int w, int h, FontRenderer fr) {
        int c = Color.green.getRGB();
        if(!value)
            c = Color.RED.getRGB();


        drawSRect(x,y,x+w,y+h, c);
        drawSRect(x+1,y+1,x+w-1,y+h-1, Color.GRAY.getRGB());


        this.drawHorizontalLine(x, x+w,y,c);
        this.drawHorizontalLine(x, x+w,y+h,c);
        this.drawVerticalLine(x, y+h, y, c);
        this.drawVerticalLine(x + w, y +h, y, c);
    }
    public void change(){
        this.value = !this.value;
    }
}
