package dev.bluesheep.jatica;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import thelm.jaopca.api.materials.IMaterial;

public class TiCMaterialHelper {
    public static JsonObject materialDefinitionProvider(IMaterial material) {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "forge:true");

        JsonObject materialDefinition = new JsonObject();
        materialDefinition.addProperty("craftable", true);
        materialDefinition.addProperty("tier", 2);
        materialDefinition.addProperty("sortOrder", 0);
        materialDefinition.addProperty("hidden", false);
        return materialDefinition;
    }

    public static JsonObject materialStatsProvider(IMaterial material) {
        JsonObject handleStats = new JsonObject();
        handleStats.addProperty("durability", 2.8F);
        handleStats.addProperty("melee_damage", 4.2F);
        handleStats.addProperty("melee_speed", 0.8F);
        handleStats.addProperty("mining_speed", 0.0F);

        JsonObject headStats = new JsonObject();
        headStats.addProperty("durability", 2804);
        headStats.addProperty("melee_attack", 8.0F);
        headStats.addProperty("mining_speed", 8.0F);
        headStats.addProperty("mining_tier", "minecraft:diamond");

        JsonObject stats = new JsonObject();
        stats.add("tconstruct:binding", new JsonObject());
        stats.add("tconstruct:handle", handleStats);
        stats.add("tconstruct:head", headStats);
        JsonObject root = new JsonObject();
        root.add("stats", stats);
        return root;
    }

    public static JsonObject materialRenderInfoProvider(IMaterial material) {
        String color = Integer.toHexString(material.getColor());
        JsonArray fallbacks = new JsonArray();
        fallbacks.add("metal");

        JsonArray supported_stats = new JsonArray();
        supported_stats.add("tconstruct:binding");

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
}
