package stulu.cosmetics.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import stulu.cosmetics.CosmeticBase;
import stulu.cosmetics.CosmeticController;

public class WitherEffect extends CosmeticBase {
    private static final ResourceLocation WITHER_ARMOR = new ResourceLocation("stulu/cosmetics/effects/wither.png");
    public final ModelPlayer Model;

    public WitherEffect(RenderPlayer renderer){
        super(renderer);
        Model = renderer.getMainModel();
    }
    @Override
    public void render(AbstractClientPlayer entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        if(!CosmeticController.shouldRenderWitherEffect(entitylivingbaseIn))
            return;
        GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
        playerRenderer.bindTexture(WITHER_ARMOR);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        float f = (float)entitylivingbaseIn.ticksExisted + partialTicks;
        float f1 = MathHelper.cos(f * 0.02F) * 3.0F;
        float f2 = f * 0.01F;
        GlStateManager.translate(f1, f2, 0.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.enableBlend();
        float f3 = 0.5F;
        GlStateManager.color(f3, f3, f3, 1.0F);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(1, 1);
        this.Model.setLivingAnimations(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks);
        this.Model.setModelAttributes(this.playerRenderer.getMainModel());
        this.Model.render(entitylivingbaseIn, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, scale);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();

    }
}

