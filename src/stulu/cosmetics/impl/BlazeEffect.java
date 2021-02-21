package stulu.cosmetics.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import stulu.cosmetics.CosmeticBase;
import stulu.cosmetics.CosmeticController;
import stulu.cosmetics.CosmeticModelBase;

public class BlazeEffect extends CosmeticBase {
    private final ModelBlazeEffect modelBlazeEffect;
    private static final ResourceLocation texture = new ResourceLocation("stulu/cosmetics/effects/blaze.png");
    public BlazeEffect(RenderPlayer Renderplayer){
        super(Renderplayer);
        modelBlazeEffect = new ModelBlazeEffect(Renderplayer);
    }

    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageIntTicks, float HeadYaw, float headPitch, float scale) {
        if(CosmeticController.shouldRenderBlazeEffect(player)){
            GlStateManager.pushMatrix();
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.enableLighting();
            playerRenderer.bindTexture(texture);
            if(player.isSneaking()){
                GL11.glTranslated(0, 0.225D, 0);
            }
            float[] color = CosmeticController.getBlazeEffectColor;
            GL11.glColor4d(color[0], color[1], color[2], color[3]);
            GL11.glColor4f(color[0], color[1], color[2], color[3]);
            modelBlazeEffect.render(player, limbSwing, limbSwingAmount, ageIntTicks, HeadYaw, headPitch, scale);
            GL11.glPopMatrix();
        }
    }

    private class ModelBlazeEffect extends CosmeticModelBase{
        private ModelRenderer[] blazeSticks = new ModelRenderer[12];

        public ModelBlazeEffect(RenderPlayer player){
            super(player);

            for (int i = 0; i < this.blazeSticks.length; ++i)
            {
                this.blazeSticks[i] = new ModelRenderer(this, 0, 16);
                this.blazeSticks[i].addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
            }
        }

        @Override
        public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) {
            this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
            //super.render(entityIn, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale);
            for (int i = 0; i < this.blazeSticks.length; ++i)
            {
                this.blazeSticks[i].render(scale);
            }
        }

        @Override
        public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn) {
            float f = p_78087_3_ * (float)Math.PI * -0.1F;

            for (int i = 0; i < 4; ++i)
            {
                this.blazeSticks[i].rotationPointY = -2.0F + MathHelper.cos(((float)(i * 2) + p_78087_3_) * 0.25F);
                this.blazeSticks[i].rotationPointX = MathHelper.cos(f) * 9.0F;
                this.blazeSticks[i].rotationPointZ = MathHelper.sin(f) * 9.0F;
                ++f;
            }

            f = ((float)Math.PI / 4F) + p_78087_3_ * (float)Math.PI * 0.03F;

            for (int j = 4; j < 8; ++j)
            {
                this.blazeSticks[j].rotationPointY = 2.0F + MathHelper.cos(((float)(j * 2) + p_78087_3_) * 0.25F);
                this.blazeSticks[j].rotationPointX = MathHelper.cos(f) * 7.0F;
                this.blazeSticks[j].rotationPointZ = MathHelper.sin(f) * 7.0F;
                ++f;
            }

            f = 0.47123894F + p_78087_3_ * (float)Math.PI * -0.05F;

            for (int k = 8; k < 12; ++k)
            {
                this.blazeSticks[k].rotationPointY = 11.0F + MathHelper.cos(((float)k * 1.5F + p_78087_3_) * 0.5F);
                this.blazeSticks[k].rotationPointX = MathHelper.cos(f) * 5.0F;
                this.blazeSticks[k].rotationPointZ = MathHelper.sin(f) * 5.0F;
                ++f;
            }
        }
    }
}
