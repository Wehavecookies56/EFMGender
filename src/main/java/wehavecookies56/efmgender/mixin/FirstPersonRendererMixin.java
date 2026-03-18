package wehavecookies56.efmgender.mixin;

import com.wildfire.render.GenderLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wehavecookies56.efmgender.Config;
import wehavecookies56.efmgender.PatchedGenderLayerRenderer;
import yesman.epicfight.client.renderer.FirstPersonRenderer;

@Mixin(FirstPersonRenderer.class)
public class FirstPersonRendererMixin {

    @SuppressWarnings("all")
    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(EntityRendererProvider.Context context, EntityType entityType, CallbackInfo ci) {
        if (Config.RENDER_FIRST_PERSON.getAsBoolean()) {
            FirstPersonRenderer thisOne = ((FirstPersonRenderer) (Object) this);
            thisOne.addPatchedLayer(GenderLayer.class, new PatchedGenderLayerRenderer(true));
        }
    }

}
