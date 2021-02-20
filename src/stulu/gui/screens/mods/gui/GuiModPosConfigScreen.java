package stulu.gui.screens.mods.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import stulu.mods.IStuluGUIModRender;
import stulu.mods.config.StuluModGuiPosition;
import stulu.mods.StuluRenderApi;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

public class GuiModPosConfigScreen extends GuiScreen {
    private Optional<IStuluGUIModRender> selectedRenderer = Optional.empty();
    private final HashMap<IStuluGUIModRender, StuluModGuiPosition> renderers = new HashMap<IStuluGUIModRender, StuluModGuiPosition>();
    private int prevX, prevY;
    public GuiModPosConfigScreen(StuluRenderApi api){
        Collection<IStuluGUIModRender> registeredRenderers = api.getRegisteredRenderers();
        for(IStuluGUIModRender ren : registeredRenderers){
            if(!ren.getEnabled()){
                continue;
            }

            StuluModGuiPosition pos = ren.load();

            if(pos == null){
                pos = new StuluModGuiPosition(0.5,0.5);
            }
            adjustBounds(ren, pos);
            this.renderers.put(ren, pos);
        }
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        this.drawHollowRect(0, 0, this.width-1, this.height-1,  0xFFFF0000);
        for(IStuluGUIModRender renderer : renderers.keySet()){
            StuluModGuiPosition pos = renderers.get(renderer);

            renderer.renderDummy(pos);

            this.drawHollowRect(pos.getPosX(), pos.getPosY(), renderer.getWidth(), renderer.getHeight(), 0xFF00FFFF);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    private void drawHollowRect(int x, int y, int w, int h, int color) {
        this.drawHorizontalLine(x, x+w,y,color);
        this.drawHorizontalLine(x, x+w,y+h,color);


        this.drawVerticalLine(x, y+h, y, color);
        this.drawVerticalLine(x + w, y +h, y, color);
    }

    @Override
    protected void mouseClickMove(int x, int y, int Button, long time) {
        if(selectedRenderer.isPresent()){
            moveSelectedRenderBy(x-prevX, y - prevY);

        }
        this.prevX = x;
        this.prevY = y;
    }

    private void moveSelectedRenderBy(int offsetX, int offsetY) {
        IStuluGUIModRender renderer = selectedRenderer.get();
        StuluModGuiPosition pos = renderers.get(renderer);

        pos.setPosX(pos.getPosX() + offsetX);
        pos.setPosY(pos.getPosY() + offsetY);
        adjustBounds(renderer, pos);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode){
        if(keyCode == Keyboard.KEY_ESCAPE){
            renderers.entrySet().forEach((entry) -> {
                try {
                    entry.getKey().Save(entry.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            this.mc.displayGuiScreen(null);
        }
    }


    @Override
    public void onGuiClosed() {
        for(IStuluGUIModRender renderer : renderers.keySet()){
            try{
                renderer.Save(renderers.get(renderer));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    private void adjustBounds(IStuluGUIModRender renderer, StuluModGuiPosition pos){
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());

        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();

        int absoluteX = Math.max(0, Math.min(pos.getPosX(), Math.max(screenWidth - renderer.getWidth(), 0)));
        int absoluteY = Math.max(0, Math.min(pos.getPosY(), Math.max(screenHeight - renderer.getHeight(), 0)));

        pos.setPosX(absoluteX);
        pos.setPosY(absoluteY);
    }

    @Override
    protected void mouseClicked(int x, int y, int Button) {
        this.prevX = x;
        this.prevY = y;

        loadMouseOver(x, y);

    }

    private void loadMouseOver(int x, int y) {
        this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
    }
    private class MouseOverFinder implements Predicate<IStuluGUIModRender> {

        int mouseX;
        int mouseY;

        public MouseOverFinder(int x, int y){
            this.mouseX = x;
            this.mouseY = y;
        }
        @Override
        public boolean test(IStuluGUIModRender renderer) {

            StuluModGuiPosition pos = renderers.get(renderer);

            int absoluteX = pos.getPosX();
            int absoluteY = pos.getPosY();
            if(mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()){
                if(mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()){
                    return true;
                }
            }

            return false;
        }
    }
}
