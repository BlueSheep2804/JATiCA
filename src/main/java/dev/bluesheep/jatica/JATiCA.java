package dev.bluesheep.jatica;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(JATiCA.MODID)
public class JATiCA {
    public static final String MODID = "jatica";
    static final Logger LOGGER = LogUtils.getLogger();

    public JATiCA() {}

    @SuppressWarnings("removal")
    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }
}
