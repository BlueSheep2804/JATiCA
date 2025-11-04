package dev.bluesheep.jatica.mixin;

import dev.bluesheep.jatica.MaterialNameUtil;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.client.materials.MaterialTooltipCache;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.utils.Util;

@Mixin(value = MaterialTooltipCache.class, remap = false)
public class MaterialTooltipCacheMixin {
    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true)
    private static void jatica$getDisplayName(MaterialVariantId id, CallbackInfoReturnable<MutableComponent> cir) {
        if (id.getId().getNamespace().equals("jatica") && !Util.canTranslate("material.jatica." + id.getId().getPath())) {
            cir.setReturnValue(MaterialNameUtil.getMaterialName(id.getId()));
        }
    }
}
