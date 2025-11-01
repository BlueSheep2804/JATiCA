package dev.bluesheep.jatica;

import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.util.Lazy;
import thelm.jaopca.api.config.IDynamicSpecConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MaterialConfig {
    private IDynamicSpecConfig config;

    private final Lazy<Boolean> materialCraftable = Lazy.of(() -> config.getDefinedBoolean("jatica.material.craftable", true, "If true, this material is allowed to be crafted in the part builder."));
    private final Lazy<Boolean> materialCastable = Lazy.of(() -> config.getDefinedBoolean("jatica.material.castable", true, "If true, this material is allowed to be cast in the smeltery."));
    private final Lazy<Boolean> materialMeltable = Lazy.of(() -> config.getDefinedBoolean("jatica.material.meltable", true, "If true, this material is allowed to be melted in the smeltery."));
    private final Lazy<Integer> materialTier = Lazy.of(() -> config.getDefinedInt("jatica.material.tier", 1, "Material tier."));
    private final Lazy<Integer> materialSortOrder = Lazy.of(() -> config.getDefinedInt("jatica.material.sort_order", 0, "Sort order within the tier."));
    private final Lazy<Boolean> materialHidden = Lazy.of(() -> config.getDefinedBoolean("jatica.material.hidden", false, "If true, this material is not shown in the book or as part of material items, though it may still show in crafting blocks."));
    private final Lazy<List<String>> materialFluid = Lazy.of(() -> config.getDefinedStringList("jatica.material.fluid", new ArrayList<>(), "List of fluids for this material. If empty, the default fluid tag will be used."));

    private final Lazy<List<String>> traitsDefault = Lazy.of(() -> config.getDefinedStringList("jatica.traits.default", new ArrayList<>(), "List of default traits for this material."));
    private final Lazy<List<String>> traitsMeleeHarvest = Lazy.of(() -> config.getDefinedStringList("jatica.traits.melee_harvest", new ArrayList<>(), "List of melee/harvest traits for this material."));
    private final Lazy<List<String>> traitsRanged = Lazy.of(() -> config.getDefinedStringList("jatica.traits.ranged", new ArrayList<>(), "List of ranged traits for this material."));
    private final Lazy<List<String>> traitsArmor = Lazy.of(() -> config.getDefinedStringList("jatica.traits.armor", new ArrayList<>(), "List of armor traits for this material."));

    private final Lazy<Boolean> headEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.head.enabled", true, "If true, add the head parts."));
    private final Lazy<Integer> headDurability = Lazy.of(() -> config.getDefinedInt("jatica.head.durability", 60, "Durability value."));
    private final Lazy<Float> headMiningSpeed = Lazy.of(() -> config.getDefinedFloat("jatica.head.mining_speed", 2.0F, "Mining speed value."));
    private final Lazy<Tiers> headMiningTier = Lazy.of(() -> config.getDefinedEnum("jatica.head.mining_tier", Tiers.class, Tiers.WOOD, "Mining tier value."));
    private final Lazy<Float> headAttackDamage = Lazy.of(() -> config.getDefinedFloat("jatica.head.attack_damage", 0.0F, "Attack damage value."));
    private final Lazy<List<String>> headTraits = Lazy.of(() -> config.getDefinedStringList("jatica.head.traits", new ArrayList<>(), "List of head traits for this material."));

    private final Lazy<Boolean> handleEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.handle.enabled", true, "If true, add the handle parts."));
    private final Lazy<Float> handleDurability = Lazy.of(() -> config.getDefinedFloat("jatica.handle.durability", 1.0F, "Durability multiplier."));
    private final Lazy<Float> handleAttackDamage = Lazy.of(() -> config.getDefinedFloat("jatica.handle.attack_damage", 1.0F, "Attack damage multiplier."));
    private final Lazy<Float> handleAttackSpeed = Lazy.of(() -> config.getDefinedFloat("jatica.handle.attack_speed", 1.0F, "Attack speed multiplier."));
    private final Lazy<Float> handleMiningSpeed = Lazy.of(() -> config.getDefinedFloat("jatica.handle.mining_speed", 1.0F, "Mining speed multiplier."));
    private final Lazy<List<String>> handleTraits = Lazy.of(() -> config.getDefinedStringList("jatica.handle.traits", new ArrayList<>(), "List of handle traits for this material."));

    private final Lazy<Boolean> bindingEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.binding.enabled", true, "If true, add the binding parts."));
    private final Lazy<List<String>> bindingTraits = Lazy.of(() -> config.getDefinedStringList("jatica.binding.traits", new ArrayList<>(), "List of binding traits for this material."));

    private final Lazy<Boolean> limbEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.limb.enabled", true, "If true, add the limb parts."));
    private final Lazy<Integer> limbDurability = Lazy.of(() -> config.getDefinedInt("jatica.limb.durability", 60, "Durability value."));
    private final Lazy<Float> limbDrawSpeed = Lazy.of(() -> config.getDefinedFloat("jatica.limb.draw_speed", 0.0F, "Draw speed value."));
    private final Lazy<Float> limbVelocity = Lazy.of(() -> config.getDefinedFloat("jatica.limb.velocity", 0.0F, "Velocity value."));
    private final Lazy<Float> limbAccuracy = Lazy.of(() -> config.getDefinedFloat("jatica.limb.accuracy", 0.0F, "Accuracy value."));
    private final Lazy<List<String>> limbTraits = Lazy.of(() -> config.getDefinedStringList("jatica.limb.traits", new ArrayList<>(), "List of limb traits for this material."));

    private final Lazy<Boolean> gripEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.grip.enabled", true, "If true, add the grip parts."));
    private final Lazy<Float> gripDurability = Lazy.of(() -> config.getDefinedFloat("jatica.grip.durability", 1.0F, "Durability multiplier."));
    private final Lazy<Float> gripAccuracy = Lazy.of(() -> config.getDefinedFloat("jatica.grip.accuracy", 1.0F, "Accuracy multiplier."));
    private final Lazy<Float> gripAttackDamage = Lazy.of(() -> config.getDefinedFloat("jatica.grip.attack_damage", 1.0F, "Attack damage multiplier."));
    private final Lazy<List<String>> gripTraits = Lazy.of(() -> config.getDefinedStringList("jatica.grip.traits", new ArrayList<>(), "List of grip traits for this material."));

    private final Lazy<Boolean> bowstringEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.bowstring.enabled", true, "If true, add the bowstring parts."));
    private final Lazy<List<String>> bowstringTraits = Lazy.of(() -> config.getDefinedStringList("jatica.bowstring.traits", new ArrayList<>(), "List of bowstring traits for this material."));

    private final Lazy<Boolean> platingHelmetEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.plating.helmet_enabled", true, "If true, add the helmet plating."));
    private final Lazy<Boolean> platingChestplateEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.plating.chestplate_enabled", true, "If true, add the chestplate plating."));
    private final Lazy<Boolean> platingLeggingsEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.plating.leggings_enabled", true, "If true, add the leggings plating."));
    private final Lazy<Boolean> platingBootsEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.plating.boots_enabled", true, "If true, add the boots plating."));
    private final Lazy<Boolean> platingShieldEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.plating.shield_enabled", true, "If true, add the shield plating."));

    private final Lazy<Float> platingHelmetArmor = Lazy.of(() -> config.getDefinedFloat("jatica.plating.helmet_armor", 0.0F, "Armor value for helmet plating."));
    private final Lazy<Float> platingChestplateArmor = Lazy.of(() -> config.getDefinedFloat("jatica.plating.chestplate_armor", 0.0F, "Armor value for chestplate plating."));
    private final Lazy<Float> platingLeggingsArmor = Lazy.of(() -> config.getDefinedFloat("jatica.plating.leggings_armor", 0.0F, "Armor value for leggings plating."));
    private final Lazy<Float> platingBootsArmor = Lazy.of(() -> config.getDefinedFloat("jatica.plating.boots_armor", 0.0F, "Armor value for boots plating."));

    private final Lazy<Float> platingDurabilityFactor = Lazy.of(() -> config.getDefinedFloat("jatica.plating.durability_factor", 16.0F, "Durability factor for all plating."));
    private final Lazy<Float> platingToughness = Lazy.of(() -> config.getDefinedFloat("jatica.plating.toughness", 0.0F, "Toughness value for all plating."));
    private final Lazy<Float> platingKnockbackResistance = Lazy.of(() -> config.getDefinedFloat("jatica.plating.knockback_resistance", 0.0F, "Knockback resistance value for all plating."));

    private final Lazy<List<String>> platingTraitsHelmet = Lazy.of(() -> config.getDefinedStringList("jatica.plating.helmet_traits", new ArrayList<>(), "List of helmet plating traits for this material."));
    private final Lazy<List<String>> platingTraitsChestplate = Lazy.of(() -> config.getDefinedStringList("jatica.plating.chestplate_traits", new ArrayList<>(), "List of chestplate plating traits for this material."));
    private final Lazy<List<String>> platingTraitsLeggings = Lazy.of(() -> config.getDefinedStringList("jatica.plating.leggings_traits", new ArrayList<>(), "List of leggings plating traits for this material."));
    private final Lazy<List<String>> platingTraitsBoots = Lazy.of(() -> config.getDefinedStringList("jatica.plating.boots_traits", new ArrayList<>(), "List of boots plating traits for this material."));
    private final Lazy<List<String>> platingTraitsShield = Lazy.of(() -> config.getDefinedStringList("jatica.plating.shield_traits", new ArrayList<>(), "List of shield plating traits for this material."));

    private final Lazy<Boolean> mailleEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.maille.enabled", true, "If true, add the maille parts."));
    private final Lazy<List<String>> mailleTraits = Lazy.of(() -> config.getDefinedStringList("jatica.maille.traits", new ArrayList<>(), "List of maille traits for this material."));

    private final Lazy<Boolean> repairKitEnabled = Lazy.of(() -> config.getDefinedBoolean("jatica.repair_kit.enabled", false, "If true, add the repair kit."));

    public MaterialConfig(IDynamicSpecConfig config) {
        this.config = config;
    }

    public List<String> enabledParts() {
        return Stream.of(
                headEnabled.get() ? "head" : "",
                handleEnabled.get() ? "handle" : "",
                bindingEnabled.get() ? "binding" : "",
                limbEnabled.get() ? "limb" : "",
                gripEnabled.get() ? "grip" : "",
                bowstringEnabled.get() ? "bowstring" : "",
                platingHelmetEnabled.get() ? "plating_helmet" : "",
                platingChestplateEnabled.get() ? "plating_chestplate" : "",
                platingLeggingsEnabled.get() ? "plating_leggings" : "",
                platingBootsEnabled.get() ? "plating_boots" : "",
                platingShieldEnabled.get() ? "plating_shield" : "",
                mailleEnabled.get() ? "maille" : "",
                repairKitEnabled.get() ? "repair_kit" : ""
        ).filter(s -> !s.isEmpty()).map(s -> "tconstruct:" + s).toList();
    }

    public Lazy<Boolean> getMaterialCraftable() {
        return materialCraftable;
    }

    public Lazy<Boolean> getMaterialCastable() {
        return materialCastable;
    }

    public Lazy<Boolean> getMaterialMeltable() {
        return materialMeltable;
    }

    public Lazy<Integer> getMaterialTier() {
        return materialTier;
    }

    public Lazy<Integer> getMaterialSortOrder() {
        return materialSortOrder;
    }

    public Lazy<Boolean> getMaterialHidden() {
        return materialHidden;
    }

    public Lazy<List<String>> getMaterialFluid() {
        return materialFluid;
    }

    public Lazy<List<String>> getTraitsDefault() {
        return traitsDefault;
    }

    public Lazy<List<String>> getTraitsMeleeHarvest() {
        return traitsMeleeHarvest;
    }

    public Lazy<List<String>> getTraitsRanged() {
        return traitsRanged;
    }

    public Lazy<List<String>> getTraitsArmor() {
        return traitsArmor;
    }

    public Lazy<Boolean> getHeadEnabled() {
        return headEnabled;
    }

    public Lazy<Integer> getHeadDurability() {
        return headDurability;
    }

    public Lazy<Float> getHeadMiningSpeed() {
        return headMiningSpeed;
    }

    public Lazy<Tiers> getHeadMiningTier() {
        return headMiningTier;
    }

    public Lazy<Float> getHeadAttackDamage() {
        return headAttackDamage;
    }

    public Lazy<List<String>> getHeadTraits() {
        return headTraits;
    }

    public Lazy<Boolean> getHandleEnabled() {
        return handleEnabled;
    }

    public Lazy<Float> getHandleDurability() {
        return handleDurability;
    }

    public Lazy<Float> getHandleAttackDamage() {
        return handleAttackDamage;
    }

    public Lazy<Float> getHandleAttackSpeed() {
        return handleAttackSpeed;
    }

    public Lazy<Float> getHandleMiningSpeed() {
        return handleMiningSpeed;
    }

    public Lazy<List<String>> getHandleTraits() {
        return handleTraits;
    }

    public Lazy<Boolean> getBindingEnabled() {
        return bindingEnabled;
    }

    public Lazy<List<String>> getBindingTraits() {
        return bindingTraits;
    }

    public Lazy<Boolean> getLimbEnabled() {
        return limbEnabled;
    }

    public Lazy<Integer> getLimbDurability() {
        return limbDurability;
    }

    public Lazy<Float> getLimbDrawSpeed() {
        return limbDrawSpeed;
    }

    public Lazy<Float> getLimbVelocity() {
        return limbVelocity;
    }

    public Lazy<Float> getLimbAccuracy() {
        return limbAccuracy;
    }

    public Lazy<List<String>> getLimbTraits() {
        return limbTraits;
    }

    public Lazy<Boolean> getGripEnabled() {
        return gripEnabled;
    }

    public Lazy<Float> getGripDurability() {
        return gripDurability;
    }

    public Lazy<Float> getGripAccuracy() {
        return gripAccuracy;
    }

    public Lazy<Float> getGripAttackDamage() {
        return gripAttackDamage;
    }

    public Lazy<List<String>> getGripTraits() {
        return gripTraits;
    }

    public Lazy<Boolean> getBowstringEnabled() {
        return bowstringEnabled;
    }

    public Lazy<List<String>> getBowstringTraits() {
        return bowstringTraits;
    }

    public Lazy<Boolean> getPlatingHelmetEnabled() {
        return platingHelmetEnabled;
    }

    public Lazy<Boolean> getPlatingChestplateEnabled() {
        return platingChestplateEnabled;
    }

    public Lazy<Boolean> getPlatingLeggingsEnabled() {
        return platingLeggingsEnabled;
    }

    public Lazy<Boolean> getPlatingBootsEnabled() {
        return platingBootsEnabled;
    }

    public Lazy<Boolean> getPlatingShieldEnabled() {
        return platingShieldEnabled;
    }

    public Lazy<Float> getPlatingHelmetArmor() {
        return platingHelmetArmor;
    }

    public Lazy<Float> getPlatingChestplateArmor() {
        return platingChestplateArmor;
    }

    public Lazy<Float> getPlatingLeggingsArmor() {
        return platingLeggingsArmor;
    }

    public Lazy<Float> getPlatingBootsArmor() {
        return platingBootsArmor;
    }

    public Lazy<Float> getPlatingDurabilityFactor() {
        return platingDurabilityFactor;
    }

    public Lazy<Float> getPlatingToughness() {
        return platingToughness;
    }

    public Lazy<Float> getPlatingKnockbackResistance() {
        return platingKnockbackResistance;
    }

    public Lazy<List<String>> getPlatingTraitsHelmet() {
        return platingTraitsHelmet;
    }

    public Lazy<List<String>> getPlatingTraitsChestplate() {
        return platingTraitsChestplate;
    }

    public Lazy<List<String>> getPlatingTraitsLeggings() {
        return platingTraitsLeggings;
    }

    public Lazy<List<String>> getPlatingTraitsBoots() {
        return platingTraitsBoots;
    }

    public Lazy<List<String>> getPlatingTraitsShield() {
        return platingTraitsShield;
    }

    public Lazy<Boolean> getMailleEnabled() {
        return mailleEnabled;
    }

    public Lazy<List<String>> getMailleTraits() {
        return mailleTraits;
    }

    public Lazy<Boolean> getRepairKitEnabled() {
        return repairKitEnabled;
    }
}
