package dev.bluesheep.jatica;

import net.minecraft.server.packs.PackType;
import thelm.jaopca.api.JAOPCAApi;
import thelm.jaopca.api.config.IDynamicSpecConfig;
import thelm.jaopca.api.materials.IMaterial;
import thelm.jaopca.api.materials.MaterialType;
import thelm.jaopca.api.modules.IModule;
import thelm.jaopca.api.modules.IModuleData;
import thelm.jaopca.api.modules.JAOPCAModule;
import thelm.jaopca.api.resources.IInMemoryResourcePack;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import static dev.bluesheep.jatica.JATiCA.rl;

@JAOPCAModule(modDependencies = "tconstruct")
public class TiCMaterialModule implements IModule {
    // TODO: ticで定義されているものを除外するようにする
    private static final Set<String> BLACKLIST = Set.of("amethyst_bronze", "cinderslime", "cobalt", "copper", "hepatizon", "iron", "knightslime", "manyullyn", "pig_iron", "queens_slime", "rose_gold", "slimesteel", "soulsteel", "steel");
    private final JAOPCAApi api = JAOPCAApi.instance();
    private Map<IMaterial, IDynamicSpecConfig> configs;

    @Override
    public String getName() {
        return "jatica_materials";
    }

    @Override
    public Set<MaterialType> getMaterialTypes() {
        return EnumSet.allOf(MaterialType.class);
    }

    @Override
    public Set<String> getDefaultMaterialBlacklist() {
        return BLACKLIST;
    }

    @Override
    public void defineMaterialConfig(IModuleData moduleData, Map<IMaterial, IDynamicSpecConfig> configs) {
        this.configs = configs;
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
        for (IMaterial material : moduleData.getMaterials()) {
            resourcePack.putJson(PackType.CLIENT_RESOURCES, rl("tinkering/materials/" + material.getName() + ".json"), TiCMaterialHelper.materialRenderInfoProvider(material, new MaterialConfig(configs.get(material))));
        }
    }
}
