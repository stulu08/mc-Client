package stulu.cosmetics.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import stulu.cosmetics.CosmeticBase;
import stulu.cosmetics.CosmeticController;
import stulu.cosmetics.CosmeticModelBase;

public class CosmeticDragonWings extends CosmeticBase {

    private final ModelWings modelWings;

    public CosmeticDragonWings(RenderPlayer renderPlayer){
        super(renderPlayer);
        modelWings = new ModelWings(renderPlayer);
    }
    AbstractClientPlayer player;
    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageIntTicks, float HeadYaw, float headPitch, float scale) {
        if(CosmeticController.shouldRenderDragonWings(player)) {
            GlStateManager.pushMatrix();
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.enableLighting();
            if(player.isSneaking()){
                GL11.glTranslated(0, 0.225D, 0);
            }
            float[] color = CosmeticController.getDragonWingsColor;
            GL11.glColor4d(color[0], color[1], color[2], color[3]);
            GL11.glColor4f(color[0], color[1], color[2], color[3]);
            modelWings.render(player, limbSwing, limbSwingAmount, ageIntTicks, HeadYaw, headPitch, scale / 4);


            GL11.glPopMatrix();
            this.player = player;
        }

    }


    private class ModelWings extends CosmeticModelBase {
        private ModelRenderer wing;
        private ModelRenderer wingTip;
        private float partialTicks;
        private final ResourceLocation enderDragonTextures = new ResourceLocation("stulu/cosmetics/wing/dragon.png");

        public ModelWings(RenderPlayer player) {
            super(player);

            this.textureWidth = 256;
            this.textureHeight = 256;

            this.setTextureOffset("wing.skin", -56, 88);
            this.setTextureOffset("wingtip.skin", -56, 144);
            this.setTextureOffset("wing.bone", 112, 88);
            this.setTextureOffset("wingtip.bone", 112, 136);

            this.wing = new ModelRenderer(this, "wing");
            this.wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
            this.wing.addBox("bone", -50.0F, -4.0F, -4.0F, 56, 8, 8);
            this.wing.addBox("skin", -50.0F, 0.0F, 2.0F, 56, 0, 56);
            this.wingTip = new ModelRenderer(this, "wingtip");
            this.wingTip.setRotationPoint(-50.0F, 0.0F, 0.0F);
            this.wingTip.addBox("bone", -50.0F, -2.0F, -2.0F, 56, 4, 4);
            this.wingTip.addBox("skin", -50.0F, 0.0F, 2.0F, 56, 0, 56);
            this.wing.addChild(this.wingTip);
        }


        @Override
        public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale){
            this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
            GlStateManager.pushMatrix();
            EntityPlayer entityPlayer = (EntityPlayer) entityIn;
            playerRenderer.bindTexture(enderDragonTextures);

            float f1 = (float)(Math.sin((double)(0 * (float)Math.PI * 2.0F - 1.0F)) + 1.0D);
            f1 = (f1 * f1 * 1.0F + f1 * 2.0F) * 0.05F;

            float f = entityPlayer.prevAnimTime + (entityPlayer.animTime - entityPlayer.prevAnimTime) * this.partialTicks;


            GlStateManager.pushMatrix();
            GlStateManager.translate(0,-0.1,0.1f);

            for (int j = 0; j < 2; ++j)
            {
                GlStateManager.enableCull();

                float f11 = f * (float)Math.PI * 0.25F;
                //this.wing.rotateAngleX = 37.0F - (float)Math.cos((double)f11) * 0.4F;
                this.wing.rotateAngleX = 74.35f - (float)Math.cos((double)f11) * 0.4F;
                this.wing.rotateAngleY = 175.6F;
                //this.wing.rotateAngleZ = (float)(Math.sin((double)f11) + 0.125D) * 0.8F;
                this.wing.rotateAngleZ = 0f;
                this.wingTip.rotateAngleZ = -((float)(Math.sin((double)(f11 + 2.0F)) + 0.5D)) * 0.75F;
                this.wing.render(scale);
                GlStateManager.scale(-1.0F, 1.0F, 1.0F);

                if (j == 0)
                {
                    GlStateManager.cullFace(1028);

                }
            }

            GlStateManager.popMatrix();
            GlStateManager.cullFace(1029);
            GlStateManager.disableCull();
            GlStateManager.popMatrix();

        }

        @Override
        public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {

        }

    }
}
