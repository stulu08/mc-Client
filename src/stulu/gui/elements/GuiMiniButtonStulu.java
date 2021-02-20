package stulu.gui.elements;

public class GuiMiniButtonStulu extends GuiButtonStulu{
    public GuiMiniButtonStulu(int buttonId, int x, int y, String buttonText, float r, float g, float b, float a) {
        super(buttonId, x, y, buttonText, r, g, b, a);
        this.width = 200;
        this.height = 20;
    }
    public GuiMiniButtonStulu(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
        this.width = 200;
        this.height = 20;
    }
    public GuiMiniButtonStulu(int buttonId, int x, int y ,int w, int h, String buttonText,float z) {
        super(buttonId, x, y,w,h, buttonText);
        this.width = w;
        this.height = h;
        this.zLevel = z;
    }
}
