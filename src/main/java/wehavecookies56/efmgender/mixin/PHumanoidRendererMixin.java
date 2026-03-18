package wehavecookies56.efmgender.mixin;

import wehavecookies56.efmgender.PatchedGenderLayerRenderer;
import com.wildfire.render.GenderLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.client.mesh.HumanoidMesh;
import yesman.epicfight.client.renderer.patched.entity.PHumanoidRenderer;

@Mixin(PHumanoidRenderer.class)
public class PHumanoidRendererMixin {

    @SuppressWarnings("all")
    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(AssetAccessor<? extends HumanoidMesh> mesh, EntityRendererProvider.Context context, EntityType entityType, CallbackInfo ci) {
        PHumanoidRenderer thisOne = ((PHumanoidRenderer)(Object)this);
        thisOne.addPatchedLayer(GenderLayer.class, new PatchedGenderLayerRenderer(false));
    }
}
