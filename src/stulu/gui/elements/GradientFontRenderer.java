package stulu.gui.elements;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GradientFontRenderer extends FontRenderer {

    public GradientFontRenderer(GameSettings gameSettingsIn, ResourceLocation location, TextureManager textureManagerIn, boolean unicode) {
        super(gameSettingsIn, location, textureManagerIn, unicode);
    }

    public int drawGradientString(String text, float x, float y, int topColor, int bottomColor, boolean dropShadow)
    {
        enableAlpha();
        int i;

        if (dropShadow)
        {
            i = this.renderGradientString(text, x + 1.0F, y + 1.0F, topColor, bottomColor, true);
            i = Math.max(i, this.renderGradientString(text, x, y, topColor, bottomColor, false));
        }
        else
        {
            i = this.renderGradientString(text, x, y, topColor, bottomColor, false);
        }

        return i;
    }

    private int renderGradientString(String text, float x, float y, int topColor, int bottomColor, boolean dropShadow)
    {
        if (text == null)
        {
            return 0;
        }
        else
        {

            if ((topColor & -67108864) == 0)
            {
                topColor |= -16777216;
            }

            if ((bottomColor & -67108864) == 0)
            {
                bottomColor |= -16777216;
            }

            if (dropShadow)
            {
                topColor = (topColor & 16579836) >> 2 | topColor & -16777216;
                bottomColor = (bottomColor & 16579836) >> 2 | bottomColor & -16777216;
            }

            this.posX = x;
            this.posY = y;
            this.renderGradientStringAtPos(text, dropShadow, topColor, bottomColor);
            return (int)this.posX;
        }
    }

    private void renderGradientStringAtPos(String text, boolean shadow, int topColor, int bottomColor) {
        for (int i = 0; i < text.length(); ++i) {
            char c0 = text.charAt(i);
            int j = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(c0);

            float f1 = j == -1 ? 0.5f : 1f;
            boolean flag = (c0 == 0 || j == -1) && shadow;

            if (flag) {
                this.posX -= f1;
                this.posY -= f1;
            }

            float f = this.renderGradientChar(c0, topColor, bottomColor);

            if (flag) {
                this.posX += f1;
                this.posY += f1;
            }
            this.posX += f;

            //doDraw(f);
        }
    }

    private float renderGradientChar(char ch, int topColor, int bottomColor) {
        if (ch == 160) return 4.0F; // forge: display nbsp as space. MC-2595
        if (ch == ' ') {
            return 4.0F;
        } else {
            int i = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(ch);
            if (i != -1) {
                return this.renderGradientDefaultChar(i, topColor, bottomColor);
            } else {
                throw new RuntimeException("Unrecognized char: " + ch);
            }
        }
    }

    protected float renderGradientDefaultChar(int ch, int topColor, int bottomColor) {
        float topAlpha = ((topColor >> 24) & 0xFF) / 255f;
        float topRed = ((topColor >> 16) & 0xFF) / 255f;
        float topGreen = ((topColor >> 8) & 0xFF) / 255f;
        float topBlue = (topColor & 0xFF) / 255f;

        float bottomAlpha = ((bottomColor >> 24) & 0xFF) / 255f;
        float bottomRed = ((bottomColor >> 16) & 0xFF) / 255f;
        float bottomGreen = ((bottomColor >> 8) & 0xFF) / 255f;
        float bottomBlue = (bottomColor & 0xFF) / 255f;

        int charXPos = (int) (ch % 16 * 8f);
        int charYPos = (int) ((ch / 16) * 8f);
        bindTexture(this.locationFontTexture);
        float charWidth = this.charWidth[ch];
        float width = (float) charWidth - 0.01F;

        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_QUADS);

        GlStateManager.color(topRed, topGreen, topBlue, topAlpha);
        GL11.glTexCoord2f(charXPos / 128.0F, charYPos / 128.0F); // 0 0
        GL11.glVertex3f(this.posX, this.posY, 0.0F);

        GlStateManager.color(bottomRed, bottomGreen, bottomBlue, bottomAlpha);
        GL11.glTexCoord2f(charXPos / 128.0F, (charYPos + 7.99F) / 128.0F); // 0 1
        GL11.glVertex3f(this.posX, this.posY + 7.99F, 0.0F);

        GlStateManager.color(bottomRed, bottomGreen, bottomBlue, bottomAlpha);
        GL11.glTexCoord2f((charXPos + width - 1.0F) / 128.0F, (charYPos + 7.99F) / 128.0F); // 1 1
        GL11.glVertex3f(this.posX + width - 1.0F, this.posY + 7.99F, 0.0F);

        GlStateManager.color(topRed, topGreen, topBlue, topAlpha);
        GL11.glTexCoord2f((charXPos + width - 1.0F) / 128.0F, charYPos / 128.0F); // 1 0
        GL11.glVertex3f(this.posX + width - 1.0F, this.posY, 0.0F);

        GL11.glEnd();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        return (float) charWidth;
    }
    private float renderDefaultChar(int p_78266_1_, int topColor, int bottomColor,boolean p_78266_2_)
    {
        float topAlpha = ((topColor >> 24) & 0xFF) / 255f;
        float topRed = ((topColor >> 16) & 0xFF) / 255f;
        float topGreen = ((topColor >> 8) & 0xFF) / 255f;
        float topBlue = (topColor & 0xFF) / 255f;

        float bottomAlpha = ((bottomColor >> 24) & 0xFF) / 255f;
        float bottomRed = ((bottomColor >> 16) & 0xFF) / 255f;
        float bottomGreen = ((bottomColor >> 8) & 0xFF) / 255f;
        float bottomBlue = (bottomColor & 0xFF) / 255f;

        int i = p_78266_1_ % 16 * 8;
        int j = p_78266_1_ / 16 * 8;
        int k = p_78266_2_ ? 1 : 0;
        this.bindTexture(this.locationFontTexture);
        float f = this.charWidth[p_78266_1_];
        float f1 = 7.99F;
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);

        GL11.glTexCoord2f((float)i / 128.0F, (float)j / 128.0F);
        GL11.glVertex3f(this.posX + (float)k, this.posY, 0.0F);

        GL11.glTexCoord2f((float)i / 128.0F, ((float)j + 7.99F) / 128.0F);
        GL11.glVertex3f(this.posX - (float)k, this.posY + 7.99F, 0.0F);

        GL11.glTexCoord2f(((float)i + f1 - 1.0F) / 128.0F, (float)j / 128.0F);
        GL11.glVertex3f(this.posX + f1 - 1.0F + (float)k, this.posY, 0.0F);

        GL11.glTexCoord2f(((float)i + f1 - 1.0F) / 128.0F, ((float)j + 7.99F) / 128.0F);
        GL11.glVertex3f(this.posX + f1 - 1.0F - (float)k, this.posY + 7.99F, 0.0F);

        GL11.glEnd();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        return f;
    }
}
