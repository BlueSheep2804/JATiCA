package dev.bluesheep.jatica;

import dev.bluesheep.jatica.recipes.MaterialFluidRecipeSerializer;
import dev.bluesheep.jatica.recipes.MaterialMeltingRecipeSerializer;
import dev.bluesheep.jatica.recipes.MaterialRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import thelm.jaopca.api.JAOPCAApi;
import thelm.jaopca.api.config.IDynamicSpecConfig;
import thelm.jaopca.api.helpers.IMiscHelper;
import thelm.jaopca.api.materials.IMaterial;
import thelm.jaopca.api.materials.MaterialType;
import thelm.jaopca.api.modules.IModule;
import thelm.jaopca.api.modules.IModuleData;
import thelm.jaopca.api.modules.JAOPCAModule;
import thelm.jaopca.api.resources.IInMemoryResourcePack;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static dev.bluesheep.jatica.JATiCA.rl;

@JAOPCAModule(modDependencies = "tconstruct")
public class TiCMaterialModule implements IModule {
    private final JAOPCAApi api = JAOPCAApi.instance();
    private Map<IMaterial, IDynamicSpecConfig> configs;

    @Override
    public String getName() {
        return "jatica_materials";
    }

    @Override
    public boolean isPassive() {
        return true;
    }

    @Override
    public Set<MaterialType> getMaterialTypes() {
        return EnumSet.allOf(MaterialType.class);
    }

    @Override
    public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
        this.configs = configs;
    }

    @SuppressWarnings("removal")
    @Override
    public void onCommonSetup(IModuleData moduleData, FMLCommonSetupEvent event) {
        IMiscHelper miscHelper = api.miscHelper();
        for (IMaterial material : moduleData.getMaterials()) {
            MaterialConfig config = new MaterialConfig(configs.get(material));
//          部品作成台やティンカー台での修理に使用される素材値
            api.registerRecipe(
                    rl("tools/materials/" + material.getName() + "/material"),
                    new MaterialRecipeSerializer(
                            material,
                            material.getType().getFormName(),
                            1,
                            1
                    )
            );
            api.registerRecipe(
                    rl("tools/materials/" + material.getName() + "/block"),
                    new MaterialRecipeSerializer(
                            material,
                            "storage_blocks",
                            material.isSmallStorageBlock() ? 4 : 9,
                            1,
                            new ResourceLocation("forge", material.getType().getFormName() + "/" + material.getName())
                    )
            );
            api.registerRecipe(
                    rl("tools/materials/" + material.getName() + "/nugget"),
                    new MaterialRecipeSerializer(
                            material,
                            "nuggets",
                            1,
                            9
                    )
            );

            int fluidAmount = Arrays.stream(MaterialType.INGOTS)
                    .anyMatch(materialType -> material.getType().equals(materialType)) ? 90 : 100;
            List<String> definedFluids = config.getMaterialFluid().get();

            // 液体からパーツを作成する際の素材の値
            if (config.getMaterialCastable().get()) {
                Supplier<Fluid> fluidSupplier = getPreferredFluidSupplier(material, definedFluids, miscHelper);
                api.registerRecipe(
                        rl("tools/materials/molten/" + material.getName()),
                        new MaterialFluidRecipeSerializer(
                                material,
                                fluidAmount,
                                fluidSupplier
                        )
                );
            }

            // パーツを溶かして液体にする際の素材の値
            if (config.getMaterialMeltable().get()) {
                Supplier<Fluid> fluidSupplier = getPreferredFluidSupplier(material, definedFluids, miscHelper);
                api.registerRecipe(
                        rl("tools/materials/melting/" + material.getName()),
                        new MaterialMeltingRecipeSerializer(
                                material,
                                fluidAmount,
                                fluidSupplier
                        )
                );
            }
        }
    }

    @Override
    public void onCreateDataPack(IModuleData moduleData, IInMemoryResourcePack dataPack) {
        for (IMaterial material : moduleData.getMaterials()) {
            MaterialConfig materialConfig = new MaterialConfig(configs.get(material));
            dataPack.putJson(PackType.SERVER_DATA, rl("tinkering/materials/definition/" + material.getName() + ".json"), TiCMaterialHelper.materialDefinitionProvider(materialConfig));
            dataPack.putJson(PackType.SERVER_DATA, rl("tinkering/materials/stats/" + material.getName() + ".json"), TiCMaterialHelper.materialStatsProvider(materialConfig));
            dataPack.putJson(PackType.SERVER_DATA, rl("tinkering/materials/traits/" + material.getName() + ".json"), TiCMaterialHelper.materialTraitsProvider(materialConfig));
        }
    }

    @Override
    public void onCreateResourcePack(IModuleData moduleData, IInMemoryResourcePack resourcePack) {
        HashMap<String, String> materialColors = new HashMap<>();
        for (IMaterial material : moduleData.getMaterials()) {
            materialColors.put(material.getName(), "#" + Integer.toHexString(material.getColor()).substring(2));
            resourcePack.putJson(PackType.CLIENT_RESOURCES, rl("tinkering/materials/" + material.getName() + ".json"), TiCMaterialHelper.materialRenderInfoProvider(material, new MaterialConfig(configs.get(material))));
        }
        resourcePack.putJson(PackType.CLIENT_RESOURCES, rl("mantle/colors.json"), TiCMaterialHelper.mantleColorsProvider(materialColors));
    }

    @SuppressWarnings("removal")
    private Supplier<Fluid> getPreferredFluidSupplier(IMaterial material, List<String> definedFluids, IMiscHelper miscHelper) {
        Supplier<List<Fluid>> fluids;
        if (definedFluids.isEmpty()) {
            fluids = () -> Stream.concat(
                    miscHelper.getFluidTagValues(miscHelper.getTagLocation("molten", material.getName(), "_")).stream(),
                    miscHelper.getFluidTagValues(new ResourceLocation("tconstruct", "molten_" + material.getName())).stream()
            ).toList();
        } else {
            fluids = () -> definedFluids.stream().map(fluidId -> miscHelper.getFluidStack(fluidId, 1).getFluid()).toList();
        }

        return () -> miscHelper.getPreferredFluidStack(fluids.get(), 1).getFluid();
    }
}
