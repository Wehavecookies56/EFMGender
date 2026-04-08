package wehavecookies56.efmgender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wildfire.main.GenderPlayer;
import com.wildfire.main.WildfireGender;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.client.renderer.patched.layer.PatchedLayer;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class PatchedGenderLayerRenderer<E extends AbstractClientPlayer, T extends LivingEntityPatch<E>, M extends PlayerModel<E>> extends PatchedLayer<E, T, M, RenderLayer<E, M>> {

    boolean isFirstPerson;

    public PatchedGenderLayerRenderer(boolean isFirstPerson) {
        this.isFirstPerson = isFirstPerson;
    }

    @Override
    protected void renderLayer(T t, E livingentity, @Nullable RenderLayer<E, M> emGenderLayer, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, OpenMatrix4f[] openMatrix4fs, float v, float v1, float v2, float v3) {
        GenderPlayer config = WildfireGender.getPlayerById(livingentity.getUUID());
        if (config.getGender().canHaveBreasts()) {
            float breastOffsetX = 0;
            float breastOffsetY = -0.7F;
            float breastOffsetZ = 0;
            if (livingentity.isCrouching()) {
                breastOffsetY -= 0.15F;
                breastOffsetZ -= 0.15F;
            }
            OpenMatrix4f modelMatrix = new OpenMatrix4f();
            modelMatrix.scale(new Vec3f(-1F, -1F, 1F)).translate(new Vec3f(breastOffsetX, breastOffsetY, breastOffsetZ)).mulFront(openMatrix4fs[7]);
            poseStack.pushPose();
            MathUtils.mulStack(poseStack, modelMatrix);
            emGenderLayer.render(poseStack, multiBufferSource, i, livingentity, livingentity.walkAnimation.position(), livingentity.walkAnimation.speed(), v3, v, v1, v2);
            poseStack.popPose();
        }
    }
}
