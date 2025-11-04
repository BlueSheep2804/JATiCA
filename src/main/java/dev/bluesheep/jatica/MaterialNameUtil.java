package dev.bluesheep.jatica;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import thelm.jaopca.api.JAOPCAApi;
import thelm.jaopca.localization.LocalizerDefault;

public class MaterialNameUtil {
    public static MutableComponent getMaterialName(MaterialId id) {
        String materialName = JAOPCAApi.instance().currentMaterialLocalizationMap().get("jaopca.material." + id.getPath());
        return Component.literal(materialName != null ? materialName : LocalizerDefault.splitAndCapitalize(id.getPath()));
    }
}
