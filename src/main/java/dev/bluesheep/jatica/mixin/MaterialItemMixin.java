package dev.bluesheep.jatica.mixin;

import dev.bluesheep.jatica.MaterialNameUtil;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.tools.helper.TooltipUtil;
import slimeknights.tconstruct.library.tools.part.MaterialItem;

@Mixin(value = MaterialItem.class, remap = false)
public class MaterialItemMixin {
    @Inject(
            method = "getName(Ljava/lang/String;Lslimeknights/tconstruct/library/materials/definition/MaterialVariantId;)Lnet/minecraft/network/chat/Component;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void jatica$getName(String baseKey, MaterialVariantId variantId, CallbackInfoReturnable<Component> cir) {
        if (cir.getReturnValue() != null) return;
        if (variantId.getId().getNamespace().equals("jatica")) {
            cir.setReturnValue(Component.translatable(TooltipUtil.KEY_FORMAT, MaterialNameUtil.getMaterialName(variantId.getId()), Component.translatable(baseKey)));
        }
    }
}
