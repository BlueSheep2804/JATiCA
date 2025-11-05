# JATiCA
JATiCA (**J**ust **A** **Ti**nker's **C**onstruct/**C**ompatibility **A**ttempt) is a mod that adds materials registered in JAOPCA as Tinker's Construct materials.  
Since this is an addon mod developed by a third party, please direct any questions or bug reports about this mod to me, not to the developers of JAOPCA or Tinker's Construct.

🌐[日本語](README_ja.md)

## Setup
1. Install JAOPCA, Tinker's Construct, and JATiCA.
2. Launch Minecraft.
3. Open `config/jaopca/modules/jatica_materials.toml` and add the names of the JAOPCA materials you want to add as Tinker's Construct materials to `passiveMaterialWhitelist = []`.  
    Setting it to `["*"]` will add all materials registered in JAOPCA as Tinker's Construct materials.
4. Restart Minecraft.
5. Open `<material_name>.toml` in `config/jaopca/materials/` and configure the material properties in the `[jatica]` section.  
    By default, no parts are generated, so set the required parts to `true`.
6. Restart Minecraft, and the Tinker's Construct materials will be added.

## Changing Material Names
If you want to change the material names displayed in Tinker's Construct, add a key like `material.jatica.<material_name>`.  
> <material_name> Small Blade, <material_name> Mattock, etc.
```json
{
    "material.jatica.amethyst": "Cool Amethyst"
}
```
If you want to change the material names displayed in JAOPCA, add a key like `jaopca.material.<material_name>`.  
> Molten <material_name>, Block of <material_name>, etc.
```json
{
    "jaopca.material.amethyst": "Cool Amethyst"
}
```

## Acknowledgments
Thanks to the developers of JAOPCA and Tinker's Construct.
- [JAOPCA](https://github.com/TheLMiffy1111/JAOPCA)
- [Tinker's Construct](https://github.com/SlimeKnights/TinkersConstruct)
