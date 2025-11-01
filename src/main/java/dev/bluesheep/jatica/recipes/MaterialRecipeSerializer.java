package dev.bluesheep.jatica.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import thelm.jaopca.api.JAOPCAApi;
import thelm.jaopca.api.helpers.IMiscHelper;
import thelm.jaopca.api.materials.IMaterial;
import thelm.jaopca.api.recipes.IRecipeSerializer;

public class MaterialRecipeSerializer implements IRecipeSerializer {
    private final IMaterial material;
    private final String type;
    private final int materialValue;
    private final int needed;
    private final ResourceLocation itemTag;

    public MaterialRecipeSerializer(IMaterial material, String type, int materialValue, int needed) {
        this.material = material;
        this.type = type;
        this.materialValue = materialValue;
        this.needed = needed;
        this.itemTag = null;
    }

    public MaterialRecipeSerializer(IMaterial material, String type, int materialValue, int needed, ResourceLocation itemTag) {
        this.material = material;
        this.type = type;
        this.materialValue = materialValue;
        this.needed = needed;
        this.itemTag = itemTag;
    }

    @Override
    public JsonElement get() {
        IMiscHelper miscHelper = JAOPCAApi.instance().miscHelper();

        JsonObject leftOver = new JsonObject();
        if (itemTag != null) {
            leftOver.addProperty("tag", itemTag.toString());
            leftOver.addProperty("count", 1);
        }

        JsonObject root = new JsonObject();
        root.addProperty("type", "tconstruct:material");
        root.add("ingredient", miscHelper.getIngredient(miscHelper.getTagLocation(type, material.getName())).toJson());
        root.addProperty("material", "jatica:" + material.getName());
        root.addProperty("needed", needed);
        root.addProperty("value", materialValue);
        root.add("leftover", leftOver);

        return root;
    }
}
