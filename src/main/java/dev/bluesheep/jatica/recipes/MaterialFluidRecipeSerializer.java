package dev.bluesheep.jatica.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import thelm.jaopca.api.materials.IMaterial;
import thelm.jaopca.api.recipes.IRecipeSerializer;

import java.util.Objects;
import java.util.function.Supplier;

public class MaterialFluidRecipeSerializer implements IRecipeSerializer {
    private final IMaterial material;
    private final int amount;
    private final Supplier<Fluid> fluidSupplier;

    public MaterialFluidRecipeSerializer(IMaterial material, int amount, Supplier<Fluid> fluidSupplier) {
        this.material = material;
        this.amount = amount;
        this.fluidSupplier = fluidSupplier;
    }

    @Override
    public JsonElement get() {
        JsonObject fluidIngredient = new JsonObject();
        fluidIngredient.addProperty("fluid", Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluidSupplier.get())).toString());
        fluidIngredient.addProperty("amount", amount);

        JsonObject root = new JsonObject();
        root.addProperty("type", "tconstruct:material_fluid");
        root.add("fluid", fluidIngredient);
        root.addProperty("temperature", fluidSupplier.get().getFluidType().getTemperature() - 300);
        root.addProperty("output", "jatica:" + material.getName());

        return root;
    }
}
