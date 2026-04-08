package wehavecookies56.efmgender;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = EFMGender.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue RENDER_FIRST_PERSON = BUILDER
            .comment("Whether to render breasts in first person").worldRestart()
            .define("renderFirstPerson", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean renderFirstPerson = true;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        renderFirstPerson = RENDER_FIRST_PERSON.get();
    }
}
