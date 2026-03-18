package wehavecookies56.efmgender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wildfire.main.entitydata.EntityConfig;
import com.wildfire.render.GenderLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.client.renderer.patched.layer.PatchedLayer;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class PatchedGenderLayerRenderer<E extends LivingEntity, T extends LivingEntityPatch<E>, M extends HumanoidModel<E>> extends PatchedLayer<E, T, M, GenderLayer<E, M>> {

    boolean isFirstPerson;

    public PatchedGenderLayerRenderer(boolean isFirstPerson) {
        this.isFirstPerson = isFirstPerson;
    }

    @Override
    protected void renderLayer(T entitypatch, E livingentity, GenderLayer<E, M> vanillaLayer, PoseStack poseStack, MultiBufferSource buffer, int packedLight, OpenMatrix4f[] poses, float bob, float yRot, float xRot, float partialTicks) {
        EntityConfig config = EntityConfig.getEntity(livingentity);
        if (config.getGender().canHaveBreasts()) {
            float breastOffsetX = 0;
            float breastOffsetY = -0.7F;
            float breastOffsetZ = 0;
            if (livingentity.isCrouching()) {
                breastOffsetY -= 0.15F;
                breastOffsetZ -= 0.15F;
            }
            OpenMatrix4f modelMatrix = new OpenMatrix4f();
            modelMatrix.scale(new Vec3f(-1F, -1F, 1F)).translate(new Vec3f(breastOffsetX, breastOffsetY, breastOffsetZ)).mulFront(poses[7]);
            poseStack.pushPose();
            MathUtils.mulStack(poseStack, modelMatrix);
            vanillaLayer.render(poseStack, buffer, packedLight, livingentity, livingentity.walkAnimation.position(), livingentity.walkAnimation.speed(), partialTicks, bob, yRot, xRot);
            poseStack.popPose();
        }
    }
}
