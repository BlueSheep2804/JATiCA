package dev.bluesheep.jatica.mixin;

import dev.bluesheep.jatica.MaterialNameUtil;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.tools.helper.TooltipUtil;

@Mixin(value = TooltipUtil.class, remap = false)
public class TooltipUtilMixin {
    @Inject(method = "nameFor", at = @At("RETURN"), cancellable = true)
    private static void jatica$nameFor(String itemKey, Component itemName, MaterialVariantId variantId, CallbackInfoReturnable<Component> cir) {
        if (cir.getReturnValue() != null) return;
        if (variantId.getId().getNamespace().equals("jatica")) {
            cir.setReturnValue(Component.translatable(TooltipUtil.KEY_FORMAT, MaterialNameUtil.getMaterialName(variantId.getId()), itemName));
        }
    }
}
