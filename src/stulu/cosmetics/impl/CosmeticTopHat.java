package stulu.cosmetics.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import stulu.cosmetics.CosmeticBase;
import stulu.cosmetics.CosmeticController;
import stulu.cosmetics.CosmeticModelBase;

public class CosmeticTopHat extends CosmeticBase {
    private final ModelTopHat modelTopHat;
    private static final ResourceLocation texture = new ResourceLocation("stulu/cosmetics/hat/magic.png");
    public CosmeticTopHat(RenderPlayer renderPlayer){
        super(renderPlayer);
        modelTopHat = new ModelTopHat(renderPlayer);
    }

    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageIntTicks, float HeadYaw, float headPitch, float scale) {
        if(CosmeticController.shouldRenderTopHat(player)){
            GlStateManager.pushMatrix();
            playerRenderer.bindTexture(texture);

            if(player.isSneaking()){
                GL11.glTranslated(0, 0.225D, 0);
            }
            float[] color = CosmeticController.getTopHatColor(player);
            GL11.glColor3d(color[0], color[1], color[2]);
            modelTopHat.render(player, limbSwing, limbSwingAmount, ageIntTicks, HeadYaw, headPitch, scale);
            GL11.glColor3f(1, 1, 1);
            GL11.glPopMatrix();
        }

    }
    private class ModelTopHat extends CosmeticModelBase {
        private ModelRenderer rim;
        private ModelRenderer pointy;

        public ModelTopHat(RenderPlayer player) {
            super(player);
            rim = new ModelRenderer(playerModel, 0, 0);
            rim.addBox(-5.5f, -9f, -5.5f,11,2,11);
            pointy = new ModelRenderer(playerModel, 0, 13);
            pointy.addBox(-3.5f, -17f, -3.5f, 7, 8, 7);
        }

        @Override
        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageIntTicks, float HeadYaw, float headPitch, float scale){

            rim.rotateAngleX = playerModel.bipedHead.rotateAngleX;
            rim.rotateAngleY = playerModel.bipedHead.rotateAngleY;
            rim.rotationPointX = 0.0f;
            rim.rotationPointY = 0.0f;
            rim.render(scale);

            pointy.rotateAngleX = playerModel.bipedHead.rotateAngleX;
            pointy.rotateAngleY = playerModel.bipedHead.rotateAngleY;
            pointy.rotationPointX = 0.0f;
            pointy.rotationPointY = 0.0f;
            pointy.render(scale);
        }
    }
}
