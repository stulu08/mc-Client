package stulu.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import stulu.mods.IStuluGUIModRender;
import stulu.mods.StuluMod;

import java.awt.*;

public class GuiOptinosModButtonStulu extends GuiButtonStulu {
    Minecraft mc;
    IStuluGUIModRender ren;
    StuluMod mod;
    Color out,in,dis;
    public GuiOptinosModButtonStulu(int buttonId, int x, int y, int widthIn, int heightIn,Color outline, Color in,Color dis, IStuluGUIModRender modinstances) {
        super(buttonId, x, y, widthIn, heightIn, modinstances.name());
        out = outline;
        this.in = in;
        this.ren = modinstances;
        this.mod = null;
        this.dis = dis;
    }
    public GuiOptinosModButtonStulu(int buttonId, int x, int y, int widthIn, int heightIn, Color outline, Color in, Color dis, StuluMod mod) {
        super(buttonId, x, y, widthIn, heightIn, mod.config().Name());
        out = outline;
        this.in = in;
        this.ren =null;
        this.mod = mod;
        this.dis = dis;
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
            /*if(out == null || in == null)
                System.out.println("Something is missing | stulu.gui.elements.gombs");*/


            if(mod != null){
                RenderModButton(xPosition,yPosition,width,height,mod,fontrenderer);
            }
            if(ren != null){
                RenderModButton(xPosition,yPosition,width,height,ren,fontrenderer);
            }

        }

    }

    private void RenderModButton(int x, int y, int w, int h,StuluMod mod,FontRenderer fr) {
        drawSRect(x,y,x+w,y+h, in.getRGB());
        int sw =fr.getStringWidth(mod.config().Name());
        int sh = fr.FONT_HEIGHT;
        this.drawString(fr, mod.config().Name(),x+(w/2)-(sw/2),(int)((y+h)+(sh*1.0)),Color.WHITE.getRGB());
        int c = out.getRGB();
        if(!mod.config().getEnable())
            c = dis.getRGB();
        this.drawHorizontalLine(x, x+w,y,c);
        this.drawHorizontalLine(x, x+w,y+h,c);
        this.drawVerticalLine(x, y+h, y, c);
        this.drawVerticalLine(x + w, y +h, y, c);
    }
    private void RenderModButton(int x, int y, int w, int h,IStuluGUIModRender guiModRender, FontRenderer fr) {
        drawSRect(x,y,x+w,y+h,in.getRGB());
        int sw = fr.getStringWidth(guiModRender.name());
        int sh = fr.FONT_HEIGHT;
        this.drawString(fr, guiModRender.name(),x+(w/2)-(sw/2),(int)((y+h)+(sh*1.0)),Color.WHITE.getRGB());

        int c = out.getRGB();
        if(!guiModRender.getEnabled())
            c = dis.getRGB();
        this.drawHorizontalLine(x, x+w,y,c);
        this.drawHorizontalLine(x, x+w,y+h,c);
        this.drawVerticalLine(x, y+h, y, c);
        this.drawVerticalLine(x + w, y +h, y, c);
    }

}
