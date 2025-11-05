package dev.bluesheep.jatica;

import net.minecraft.world.item.Tiers;
import thelm.jaopca.api.config.IDynamicSpecConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MaterialConfig {
    private IDynamicSpecConfig config;

    private final Supplier<Boolean> materialCraftable = () -> config.getDefinedBoolean("jatica.material.craftable", true, "If true, this material is allowed to be crafted in the part builder.");
    private final Supplier<Boolean> materialCastable = () -> config.getDefinedBoolean("jatica.material.castable", true, "If true, this material is allowed to be cast in the smeltery.");
    private final Supplier<Boolean> materialMeltable = () -> config.getDefinedBoolean("jatica.material.meltable", true, "If true, this material is allowed to be melted in the smeltery.");
    private final Supplier<Integer> materialTier = () -> config.getDefinedInt("jatica.material.tier", 1, "Material tier.");
    private final Supplier<Integer> materialSortOrder = () -> config.getDefinedInt("jatica.material.sort_order", 0, "Sort order within the tier.");
    private final Supplier<Boolean> materialHidden = () -> config.getDefinedBoolean("jatica.material.hidden", false, "If true, this material is not shown in the book or as part of material items, though it may still show in crafting blocks.");
    private final Supplier<List<String>> materialFluid = () -> config.getDefinedStringList("jatica.material.fluid", new ArrayList<>(), "List of fluids for this material. If empty, the default fluid tag will be used.");

    private final Supplier<List<String>> traitsDefault = () -> config.getDefinedStringList("jatica.traits.default", new ArrayList<>(), "List of default traits for this material.");
    private final Supplier<List<String>> traitsMeleeHarvest = () -> config.getDefinedStringList("jatica.traits.melee_harvest", new ArrayList<>(), "List of melee/harvest traits for this material.");
    private final Supplier<List<String>> traitsRanged = () -> config.getDefinedStringList("jatica.traits.ranged", new ArrayList<>(), "List of ranged traits for this material.");
    private final Supplier<List<String>> traitsArmor = () -> config.getDefinedStringList("jatica.traits.armor", new ArrayList<>(), "List of armor traits for this material.");

    private final Supplier<Boolean> headEnabled = () -> config.getDefinedBoolean("jatica.head.enabled", false, "If true, add the head parts.");
    private final Supplier<Integer> headDurability = () -> config.getDefinedInt("jatica.head.durability", 60, "Durability value.");
    private final Supplier<Float> headMiningSpeed = () -> config.getDefinedFloat("jatica.head.mining_speed", 2.0F, "Mining speed value.");
    private final Supplier<Tiers> headMiningTier = () -> config.getDefinedEnum("jatica.head.mining_tier", Tiers.class, Tiers.WOOD, "Mining tier value.");
    private final Supplier<Float> headAttackDamage = () -> config.getDefinedFloat("jatica.head.attack_damage", 0.0F, "Attack damage value.");
    private final Supplier<List<String>> headTraits = () -> config.getDefinedStringList("jatica.head.traits", new ArrayList<>(), "List of head traits for this material.");

    private final Supplier<Boolean> handleEnabled = () -> config.getDefinedBoolean("jatica.handle.enabled", false, "If true, add the handle parts.");
    private final Supplier<Float> handleDurability = () -> config.getDefinedFloat("jatica.handle.durability", 1.0F, "Durability multiplier.");
    private final Supplier<Float> handleAttackDamage = () -> config.getDefinedFloat("jatica.handle.attack_damage", 1.0F, "Attack damage multiplier.");
    private final Supplier<Float> handleAttackSpeed = () -> config.getDefinedFloat("jatica.handle.attack_speed", 1.0F, "Attack speed multiplier.");
    private final Supplier<Float> handleMiningSpeed = () -> config.getDefinedFloat("jatica.handle.mining_speed", 1.0F, "Mining speed multiplier.");
    private final Supplier<List<String>> handleTraits = () -> config.getDefinedStringList("jatica.handle.traits", new ArrayList<>(), "List of handle traits for this material.");

    private final Supplier<Boolean> bindingEnabled = () -> config.getDefinedBoolean("jatica.binding.enabled", false, "If true, add the binding parts.");
    private final Supplier<List<String>> bindingTraits = () -> config.getDefinedStringList("jatica.binding.traits", new ArrayList<>(), "List of binding traits for this material.");

    private final Supplier<Boolean> limbEnabled = () -> config.getDefinedBoolean("jatica.limb.enabled", false, "If true, add the limb parts.");
    private final Supplier<Integer> limbDurability = () -> config.getDefinedInt("jatica.limb.durability", 60, "Durability value.");
    private final Supplier<Float> limbDrawSpeed = () -> config.getDefinedFloat("jatica.limb.draw_speed", 0.0F, "Draw speed value.");
    private final Supplier<Float> limbVelocity = () -> config.getDefinedFloat("jatica.limb.velocity", 0.0F, "Velocity value.");
    private final Supplier<Float> limbAccuracy = () -> config.getDefinedFloat("jatica.limb.accuracy", 0.0F, "Accuracy value.");
    private final Supplier<List<String>> limbTraits = () -> config.getDefinedStringList("jatica.limb.traits", new ArrayList<>(), "List of limb traits for this material.");

    private final Supplier<Boolean> gripEnabled = () -> config.getDefinedBoolean("jatica.grip.enabled", false, "If true, add the grip parts.");
    private final Supplier<Float> gripDurability = () -> config.getDefinedFloat("jatica.grip.durability", 1.0F, "Durability multiplier.");
    private final Supplier<Float> gripAccuracy = () -> config.getDefinedFloat("jatica.grip.accuracy", 1.0F, "Accuracy multiplier.");
    private final Supplier<Float> gripAttackDamage = () -> config.getDefinedFloat("jatica.grip.attack_damage", 1.0F, "Attack damage multiplier.");
    private final Supplier<List<String>> gripTraits = () -> config.getDefinedStringList("jatica.grip.traits", new ArrayList<>(), "List of grip traits for this material.");

    private final Supplier<Boolean> bowstringEnabled = () -> config.getDefinedBoolean("jatica.bowstring.enabled", false, "If true, add the bowstring parts.");
    private final Supplier<List<String>> bowstringTraits = () -> config.getDefinedStringList("jatica.bowstring.traits", new ArrayList<>(), "List of bowstring traits for this material.");

    private final Supplier<Boolean> platingHelmetEnabled = () -> config.getDefinedBoolean("jatica.plating.helmet_enabled", false, "If true, add the helmet plating.");
    private final Supplier<Boolean> platingChestplateEnabled = () -> config.getDefinedBoolean("jatica.plating.chestplate_enabled", false, "If true, add the chestplate plating.");
    private final Supplier<Boolean> platingLeggingsEnabled = () -> config.getDefinedBoolean("jatica.plating.leggings_enabled", false, "If true, add the leggings plating.");
    private final Supplier<Boolean> platingBootsEnabled = () -> config.getDefinedBoolean("jatica.plating.boots_enabled", false, "If true, add the boots plating.");
    private final Supplier<Boolean> platingShieldEnabled = () -> config.getDefinedBoolean("jatica.plating.shield_enabled", false, "If true, add the shield plating.");

    private final Supplier<Float> platingHelmetArmor = () -> config.getDefinedFloat("jatica.plating.helmet_armor", 0.0F, "Armor value for helmet plating.");
    private final Supplier<Float> platingChestplateArmor = () -> config.getDefinedFloat("jatica.plating.chestplate_armor", 0.0F, "Armor value for chestplate plating.");
    private final Supplier<Float> platingLeggingsArmor = () -> config.getDefinedFloat("jatica.plating.leggings_armor", 0.0F, "Armor value for leggings plating.");
    private final Supplier<Float> platingBootsArmor = () -> config.getDefinedFloat("jatica.plating.boots_armor", 0.0F, "Armor value for boots plating.");

    private final Supplier<Float> platingDurabilityFactor = () -> config.getDefinedFloat("jatica.plating.durability_factor", 16.0F, "Durability factor for all plating.");
    private final Supplier<Float> platingToughness = () -> config.getDefinedFloat("jatica.plating.toughness", 0.0F, "Toughness value for all plating.");
    private final Supplier<Float> platingKnockbackResistance = () -> config.getDefinedFloat("jatica.plating.knockback_resistance", 0.0F, "Knockback resistance value for all plating.");

    private final Supplier<List<String>> platingTraitsHelmet = () -> config.getDefinedStringList("jatica.plating.helmet_traits", new ArrayList<>(), "List of helmet plating traits for this material.");
    private final Supplier<List<String>> platingTraitsChestplate = () -> config.getDefinedStringList("jatica.plating.chestplate_traits", new ArrayList<>(), "List of chestplate plating traits for this material.");
    private final Supplier<List<String>> platingTraitsLeggings = () -> config.getDefinedStringList("jatica.plating.leggings_traits", new ArrayList<>(), "List of leggings plating traits for this material.");
    private final Supplier<List<String>> platingTraitsBoots = () -> config.getDefinedStringList("jatica.plating.boots_traits", new ArrayList<>(), "List of boots plating traits for this material.");
    private final Supplier<List<String>> platingTraitsShield = () -> config.getDefinedStringList("jatica.plating.shield_traits", new ArrayList<>(), "List of shield plating traits for this material.");

    private final Supplier<Boolean> mailleEnabled = () -> config.getDefinedBoolean("jatica.maille.enabled", false, "If true, add the maille parts.");
    private final Supplier<List<String>> mailleTraits = () -> config.getDefinedStringList("jatica.maille.traits", new ArrayList<>(), "List of maille traits for this material.");

    private final Supplier<Boolean> repairKitEnabled = () -> config.getDefinedBoolean("jatica.repair_kit.enabled", false, "If true, add the repair kit.");

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

    public Supplier<Boolean> getMaterialCraftable() {
        return materialCraftable;
    }

    public Supplier<Boolean> getMaterialCastable() {
        return materialCastable;
    }

    public Supplier<Boolean> getMaterialMeltable() {
        return materialMeltable;
    }

    public Supplier<Integer> getMaterialTier() {
        return materialTier;
    }

    public Supplier<Integer> getMaterialSortOrder() {
        return materialSortOrder;
    }

    public Supplier<Boolean> getMaterialHidden() {
        return materialHidden;
    }

    public Supplier<List<String>> getMaterialFluid() {
        return materialFluid;
    }

    public Supplier<List<String>> getTraitsDefault() {
        return traitsDefault;
    }

    public Supplier<List<String>> getTraitsMeleeHarvest() {
        return traitsMeleeHarvest;
    }

    public Supplier<List<String>> getTraitsRanged() {
        return traitsRanged;
    }

    public Supplier<List<String>> getTraitsArmor() {
        return traitsArmor;
    }

    public Supplier<Boolean> getHeadEnabled() {
        return headEnabled;
    }

    public Supplier<Integer> getHeadDurability() {
        return headDurability;
    }

    public Supplier<Float> getHeadMiningSpeed() {
        return headMiningSpeed;
    }

    public Supplier<Tiers> getHeadMiningTier() {
        return headMiningTier;
    }

    public Supplier<Float> getHeadAttackDamage() {
        return headAttackDamage;
    }

    public Supplier<List<String>> getHeadTraits() {
        return headTraits;
    }

    public Supplier<Boolean> getHandleEnabled() {
        return handleEnabled;
    }

    public Supplier<Float> getHandleDurability() {
        return handleDurability;
    }

    public Supplier<Float> getHandleAttackDamage() {
        return handleAttackDamage;
    }

    public Supplier<Float> getHandleAttackSpeed() {
        return handleAttackSpeed;
    }

    public Supplier<Float> getHandleMiningSpeed() {
        return handleMiningSpeed;
    }

    public Supplier<List<String>> getHandleTraits() {
        return handleTraits;
    }

    public Supplier<Boolean> getBindingEnabled() {
        return bindingEnabled;
    }

    public Supplier<List<String>> getBindingTraits() {
        return bindingTraits;
    }

    public Supplier<Boolean> getLimbEnabled() {
        return limbEnabled;
    }

    public Supplier<Integer> getLimbDurability() {
        return limbDurability;
    }

    public Supplier<Float> getLimbDrawSpeed() {
        return limbDrawSpeed;
    }

    public Supplier<Float> getLimbVelocity() {
        return limbVelocity;
    }

    public Supplier<Float> getLimbAccuracy() {
        return limbAccuracy;
    }

    public Supplier<List<String>> getLimbTraits() {
        return limbTraits;
    }

    public Supplier<Boolean> getGripEnabled() {
        return gripEnabled;
    }

    public Supplier<Float> getGripDurability() {
        return gripDurability;
    }

    public Supplier<Float> getGripAccuracy() {
        return gripAccuracy;
    }

    public Supplier<Float> getGripAttackDamage() {
        return gripAttackDamage;
    }

    public Supplier<List<String>> getGripTraits() {
        return gripTraits;
    }

    public Supplier<Boolean> getBowstringEnabled() {
        return bowstringEnabled;
    }

    public Supplier<List<String>> getBowstringTraits() {
        return bowstringTraits;
    }

    public Supplier<Boolean> getPlatingHelmetEnabled() {
        return platingHelmetEnabled;
    }

    public Supplier<Boolean> getPlatingChestplateEnabled() {
        return platingChestplateEnabled;
    }

    public Supplier<Boolean> getPlatingLeggingsEnabled() {
        return platingLeggingsEnabled;
    }

    public Supplier<Boolean> getPlatingBootsEnabled() {
        return platingBootsEnabled;
    }

    public Supplier<Boolean> getPlatingShieldEnabled() {
        return platingShieldEnabled;
    }

    public Supplier<Float> getPlatingHelmetArmor() {
        return platingHelmetArmor;
    }

    public Supplier<Float> getPlatingChestplateArmor() {
        return platingChestplateArmor;
    }

    public Supplier<Float> getPlatingLeggingsArmor() {
        return platingLeggingsArmor;
    }

    public Supplier<Float> getPlatingBootsArmor() {
        return platingBootsArmor;
    }

    public Supplier<Float> getPlatingDurabilityFactor() {
        return platingDurabilityFactor;
    }

    public Supplier<Float> getPlatingToughness() {
        return platingToughness;
    }

    public Supplier<Float> getPlatingKnockbackResistance() {
        return platingKnockbackResistance;
    }

    public Supplier<List<String>> getPlatingTraitsHelmet() {
        return platingTraitsHelmet;
    }

    public Supplier<List<String>> getPlatingTraitsChestplate() {
        return platingTraitsChestplate;
    }

    public Supplier<List<String>> getPlatingTraitsLeggings() {
        return platingTraitsLeggings;
    }

    public Supplier<List<String>> getPlatingTraitsBoots() {
        return platingTraitsBoots;
    }

    public Supplier<List<String>> getPlatingTraitsShield() {
        return platingTraitsShield;
    }

    public Supplier<Boolean> getMailleEnabled() {
        return mailleEnabled;
    }

    public Supplier<List<String>> getMailleTraits() {
        return mailleTraits;
    }

    public Supplier<Boolean> getRepairKitEnabled() {
        return repairKitEnabled;
    }
}
