package wehavecookies56.efmgender;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue RENDER_FIRST_PERSON = BUILDER
            .comment("Whether to render breasts in first person").gameRestart()
            .define("renderFirstPerson", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
