package net.maplecraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.client.model.ArrowForBowEntityModel;
import net.maplecraft.entities.DiamondArrowForBowEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DiamondArrowForBowRenderer extends EntityRenderer<DiamondArrowForBowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MapleCraftMod.MODID, "textures/entities/diamond_arrow_for_bow_entity.png");

    private final ArrowForBowEntityModel model;
    private final float scale = 0.8F;

    public DiamondArrowForBowRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new ArrowForBowEntityModel(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(DiamondArrowForBowEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn,
                       int packedLightIn) {
        VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
        model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
        poseStack.popPose();
        super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(DiamondArrowForBowEntity entity) {
        return TEXTURE;
    }
}
