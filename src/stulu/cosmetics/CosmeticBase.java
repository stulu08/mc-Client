package stulu.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public abstract class CosmeticBase implements LayerRenderer<AbstractClientPlayer> {

    protected final RenderPlayer playerRenderer;

    protected CosmeticBase(RenderPlayer playerRenderer) {
        this.playerRenderer = playerRenderer;
    }


    @Override
    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageIntTicks, float HeadYaw, float headPitch, float scale){
        if(player.hasPlayerInfo() && !player.isInvisible()){

                render(player, limbSwing, limbSwingAmount, partialTicks, ageIntTicks, HeadYaw, headPitch, scale);

        }

    }
    public abstract void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageIntTicks, float HeadYaw, float headPitch, float scale);



    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
