package stulu.cosmetics.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import stulu.Client;
import stulu.cosmetics.CosmeticBase;

public class GlowLayer extends CosmeticBase {
    public GlowLayer(RenderPlayer player){
        super(player);
    }
    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageIntTicks, float HeadYaw, float headPitch, float scale) {
        if(Client.Glow)
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
    }
}
