package dev.bluesheep.jatica;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.world.item.ArmorItem;
import slimeknights.tconstruct.library.materials.json.MaterialStatJson;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.tools.stats.*;
import thelm.jaopca.api.materials.IMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TiCMaterialHelper {
    private static final Gson gson = new Gson();
    public static JsonObject materialDefinitionProvider(MaterialConfig config) {
        JsonObject materialDefinition = new JsonObject();
        materialDefinition.addProperty("craftable", config.getMaterialCraftable().get());
        materialDefinition.addProperty("tier", config.getMaterialTier().get());
        materialDefinition.addProperty("sortOrder", config.getMaterialSortOrder().get());
        materialDefinition.addProperty("hidden", config.getMaterialHidden().get());
        return materialDefinition;
    }

    public static JsonElement materialStatsProvider(MaterialConfig config) {
        List<IMaterialStats> stats = new ArrayList<>();
        // Melee / Harvest
        if (config.getHeadEnabled().get()) {
            HeadMaterialStats headStats = new HeadMaterialStats(
                    config.getHeadDurability().get(),
                    config.getHeadMiningSpeed().get(),
                    config.getHeadMiningTier().get(),
                    config.getHeadAttackDamage().get()
            );
            stats.add(headStats);
        }
        if (config.getHandleEnabled().get()) {
            HandleMaterialStats handleStats = HandleMaterialStats.multipliers()
                    .durability(config.getHandleDurability().get())
                    .attackDamage(config.getHandleAttackDamage().get())
                    .attackSpeed(config.getHandleAttackSpeed().get())
                    .miningSpeed(config.getHandleMiningSpeed().get())
                    .build();
            stats.add(handleStats);
        }
        if (config.getBindingEnabled().get()) {
            stats.add(StatlessMaterialStats.BINDING);
        }

        // Ranged
        if (config.getLimbEnabled().get()) {
            LimbMaterialStats limbStats = new LimbMaterialStats(
                    config.getLimbDurability().get(),
                    config.getLimbDrawSpeed().get(),
                    config.getLimbVelocity().get(),
                    config.getLimbAccuracy().get()
            );
            stats.add(limbStats);
        }
        if (config.getGripEnabled().get()) {
            GripMaterialStats gripStats = new GripMaterialStats(
                    config.getGripDurability().get(),
                    config.getGripAccuracy().get(),
                    config.getGripAttackDamage().get()
            );
            stats.add(gripStats);
        }
        if (config.getBowstringEnabled().get()) {
            stats.add(StatlessMaterialStats.BOWSTRING);
        }

        // Armor
        Map<ArmorItem.Type, Boolean> enabledArmor = Map.of(
                ArmorItem.Type.HELMET, config.getPlatingHelmetEnabled().get(),
                ArmorItem.Type.CHESTPLATE, config.getPlatingChestplateEnabled().get(),
                ArmorItem.Type.LEGGINGS, config.getPlatingLeggingsEnabled().get(),
                ArmorItem.Type.BOOTS, config.getPlatingBootsEnabled().get()
        );

        PlatingMaterialStats.Builder platingStats = PlatingMaterialStats.builder()
                .durabilityFactor(config.getPlatingDurabilityFactor().get())
                .armor(
                        config.getPlatingBootsArmor().get(),
                        config.getPlatingLeggingsArmor().get(),
                        config.getPlatingChestplateArmor().get(),
                        config.getPlatingHelmetArmor().get()
                )
                .toughness(config.getPlatingToughness().get())
                .knockbackResistance(config.getPlatingKnockbackResistance().get());
        for (Map.Entry<ArmorItem.Type, Boolean> armor : enabledArmor.entrySet()) {
            if (armor.getValue()) stats.add(platingStats.build(armor.getKey()));
        }
        if (config.getPlatingShieldEnabled().get()) {
            stats.add(platingStats.buildShield());
        }

        if (config.getMailleEnabled().get()) {
            stats.add(StatlessMaterialStats.MAILLE);
        }

        if (config.getRepairKitEnabled().get()) {
            stats.add(StatlessMaterialStats.REPAIR_KIT);
        }

        return gson.toJsonTree(convert(stats));
    }

    public static JsonElement materialTraitsProvider(MaterialConfig config) {
        JsonObject root = new JsonObject();
        setTraits(root, "default", config.getTraitsDefault().get());

        JsonObject perStat = new JsonObject();
        setTraits(perStat, "tconstruct:melee_harvest", config.getTraitsMeleeHarvest().get());
        setTraits(perStat, "tconstruct:head", config.getHeadTraits().get());
        setTraits(perStat, "tconstruct:handle", config.getHandleTraits().get());
        setTraits(perStat, "tconstruct:binding", config.getBindingTraits().get());
        setTraits(perStat, "tconstruct:ranged", config.getTraitsRanged().get());
        setTraits(perStat, "tconstruct:limb", config.getLimbTraits().get());
        setTraits(perStat, "tconstruct:grip", config.getGripTraits().get());
        setTraits(perStat, "tconstruct:bowstring", config.getBowstringTraits().get());
        setTraits(perStat, "tconstruct:armor", config.getTraitsArmor().get());
        setTraits(perStat, "tconstruct:plating_helmet", config.getPlatingTraitsHelmet().get());
        setTraits(perStat, "tconstruct:plating_chestplate", config.getPlatingTraitsChestplate().get());
        setTraits(perStat, "tconstruct:plating_leggings", config.getPlatingTraitsLeggings().get());
        setTraits(perStat, "tconstruct:plating_boots", config.getPlatingTraitsBoots().get());
        setTraits(perStat, "tconstruct:plating_shield", config.getPlatingTraitsShield().get());
        setTraits(perStat, "tconstruct:maille", config.getMailleTraits().get());

        root.add("perStat", perStat);
        return root;
    }

    public static JsonObject materialRenderInfoProvider(IMaterial material, MaterialConfig config) {
        String color = Integer.toHexString(material.getColor());
        JsonArray fallbacks = new JsonArray();
        fallbacks.add("metal");

        JsonArray supported_stats = new JsonArray();
        config.enabledParts().forEach(supported_stats::add);

        JsonArray palette = new JsonArray();
        for (int i : new int[]{255, 216, 178, 140, 102, 63, 0}) {
            JsonObject paletteElement = new JsonObject();
            paletteElement.addProperty("color", color);
            paletteElement.addProperty("grey", i);
            palette.add(paletteElement);
        }

        JsonObject transformer = new JsonObject();
        transformer.addProperty("type", "tconstruct:grey_to_sprite");
        transformer.add("palette", palette);

        JsonObject generator = new JsonObject();
        generator.add("supported_stats", supported_stats);
        generator.add("transformer", transformer);

        JsonObject root = new JsonObject();
        root.addProperty("color", color);
        root.add("fallbacks", fallbacks);
        root.add("generator", generator);

        return root;
    }

    private static void setTraits(JsonObject json, String statId, List<String> traits) {
        if (traits.isEmpty()) {
            return;
        }
        JsonArray traitsArray = new JsonArray();
        if (!Objects.equals(traits.get(0), "NONE")) {
            traits.forEach(traitString -> {
                JsonObject trait = new JsonObject();
                String[] traitParts = traitString.split(" ");
                trait.addProperty("name", traitParts[0]);
                if (traitParts.length > 1) {
                    try {
                        int level = Integer.parseInt(traitParts[1]);
                        trait.addProperty("level", level);
                    } catch (NumberFormatException ignored) {
                    }
                }
                traitsArray.add(trait);
            });
        }
        json.add(statId, traitsArray);
    }

    // https://github.com/SlimeKnights/TinkersConstruct/blob/0dc23ca4f47382e337febf0742d3a3c6a337f6cf/src/main/java/slimeknights/tconstruct/library/data/material/AbstractMaterialStatsDataProvider.java
    private static MaterialStatJson convert(List<IMaterialStats> stats) {
        return new MaterialStatJson(stats.stream()
                .collect(Collectors.toMap(
                        IMaterialStats::getIdentifier,
                        stat -> encodeStats(stat, stat.getType()))));
    }

    @SuppressWarnings("unchecked")
    private static <T extends IMaterialStats> JsonElement encodeStats(IMaterialStats stats, MaterialStatType<T> type) {
        return type.getLoadable().serialize((T) stats);
    }
}
