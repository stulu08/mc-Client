package stulu.gui;

import net.minecraft.client.gui.Gui;
import stulu.file.StuluFile;

import java.awt.*;
import java.io.IOException;

public class Crossair extends Gui {
    public StuluFile ini;
    public int offsetX = 2;
    public int offsetY = 2;
    public int thickNess = 1;
    public int length = 10;
    public int dotSize = 1;
    public float colorH = 1;
    public float colorS = 1;
    public float colorB = 1;
    public Crossair(){}
    public void init(){
        try{
            ini = new StuluFile("crosshair");
        }catch (IOException exception){
            System.out.println(exception.toString());
        }
        offsetX = Integer.parseInt(ini.ClientINIRead.get("Offset","x"));
        offsetY = Integer.parseInt(ini.ClientINIRead.get("Offset","y"));
        colorH = Float.parseFloat(ini.ClientINIRead.get("Color","hue"));
        colorS = Float.parseFloat(ini.ClientINIRead.get("Color","saturation"));
        colorB = Float.parseFloat(ini.ClientINIRead.get("Color","brightness"));
        thickNess = Integer.parseInt(ini.ClientINIRead.get("Other","thickness"));
        length = Integer.parseInt(ini.ClientINIRead.get("Other","length"));
        dotSize = Integer.parseInt(ini.ClientINIRead.get("Other","dotSize"));
    }
    public void renderCrossair(int x, int y){
        //dot
        drawRect(x,y,x-(dotSize/2) + dotSize,y-(dotSize/2)+ dotSize, Color.getHSBColor(colorH,colorS,colorB).getRGB());
        //left
        x -= dotSize /2;
        y -= dotSize /2;
        drawRect(x - offsetX - length,y,x - offsetX,y+thickNess,Color.getHSBColor(colorH,colorS,colorB).getRGB());
        //right
        drawRect(x + dotSize + offsetX + length,y,x + offsetX + dotSize,y+thickNess,Color.getHSBColor(colorH,colorS,colorB).getRGB());
        //top
        drawRect(x,y - offsetY - length,x + thickNess,y - offsetY,Color.getHSBColor(colorH,colorS,colorB).getRGB());
        //bottom
        drawRect(x,y + dotSize + offsetY + length,x + thickNess,y + offsetY + dotSize,Color.getHSBColor(colorH,colorS,colorB).getRGB());
    }
}
