package teamroots.embers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import teamroots.embers.damage.DamageEmber;
import teamroots.embers.entity.*;
 
import teamroots.embers.power.DefaultEmberCapability;
import teamroots.embers.power.EmberCapabilityStorage;
import teamroots.embers.power.IEmberCapability;
import teamroots.embers.util.EmbersFuelHandler;
import teamroots.embers.util.Misc; 

import java.util.ArrayList;
import java.util.List;

public class RegistryManager {
    public static ArrayList<Block> blocks = new ArrayList<Block>();
    public static ArrayList<Item> items = new ArrayList<Item>();

    public static ToolMaterial tool_mat_tyrfing, tool_mat_copper, tool_mat_silver, tool_mat_lead, tool_mat_dawnstone;
    public static ToolMaterial tool_mat_aluminum, tool_mat_bronze, tool_mat_tin, tool_mat_electrum, tool_mat_nickel;
    public static ArmorMaterial armor_mat_ashen_cloak;
 

    public static Fluid fluid_molten_dawnstone, fluid_molten_gold, fluid_molten_copper, fluid_molten_lead, fluid_molten_silver, fluid_molten_iron,
            fluid_molten_aluminum, fluid_molten_tin, fluid_molten_bronze, fluid_molten_electrum, fluid_molten_nickel;

 
    public static DamageSource damage_ember;

    public static Material unpushable;

    public static Biome biome_cave;

    public static DimensionType dimension_cave;
 
    public static IWorldGenerator world_gen_small_ruin;

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block b : blocks) {
            event.getRegistry().register(b);
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        for (Item i : items) {
            event.getRegistry().register(i);
        }
  
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerRendering(ModelRegistryEvent event) {

 
//        for (int i = 0; i < items.size(); i++) {
//            if (items.get(i) instanceof IModeledItem) {
//                ((IModeledItem) items.get(i)).initModel();
//            }
//        }
    }

    public static void registerAll() {

        CapabilityManager.INSTANCE.register(IEmberCapability.class, new EmberCapabilityStorage(), DefaultEmberCapability.class);

        damage_ember = new DamageEmber();

        tool_mat_copper = EnumHelper.addToolMaterial(Embers.MODID + ":copper", 2, 181, 5.4f, 1.5f, 16);
        tool_mat_silver = EnumHelper.addToolMaterial(Embers.MODID + ":silver", 2, 202, 7.6f, 2.0f, 20);
        tool_mat_lead = EnumHelper.addToolMaterial(Embers.MODID + ":lead", 2, 168, 6.0f, 2.0f, 4);
        tool_mat_dawnstone = EnumHelper.addToolMaterial(Embers.MODID + ":dawnstone", 2, 644, 7.5f, 2.5f, 18);
        tool_mat_tyrfing = EnumHelper.addToolMaterial(Embers.MODID + ":tyrfing", 2, 512, 7.5f, 0.0f, 24);

        armor_mat_ashen_cloak = EnumHelper.addArmorMaterial(Embers.MODID + ":ashen_cloak", Embers.MODID + ":ashen_cloak", 19, new int[]{3, 5, 7, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
    

		/*
		items.add(ingotAstralite = new ItemBase("ingotAstralite",true));
		items.add(ingotUmberSteel = new ItemBase("ingotUmberSteel",true));*/

        registerFluids();
  

        int id = 0;

        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID + ":ember_packet"), EntityEmberPacket.class, "ember_packet", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID + ":ember_projectile"), EntityEmberProjectile.class, "ember_projectile", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID + ":ancient_golem"), EntityAncientGolem.class, "ancient_golem", id++, Embers.instance, 64, 1, true);
        EntityRegistry.registerEgg(new ResourceLocation(Embers.MODID + ":ancient_golem"), Misc.intColor(48, 38, 35), Misc.intColor(79, 66, 61));
        EntityRegistry.registerModEntity(new ResourceLocation(Embers.MODID + ":ember_light"), EntityEmberLight.class, "ember_light", id++, Embers.instance, 64, 1, true);

        List<BiomeEntry> biomeEntries = new ArrayList<BiomeEntry>();
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.COOL));
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.DESERT));
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.ICY));
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeType.WARM));
        List<Biome> biomes = new ArrayList<Biome>();
        for (BiomeEntry b : biomeEntries) {
            biomes.add(b.biome);
        }
        biomes.addAll(BiomeManager.oceanBiomes);

        EntityRegistry.addSpawn(EntityAncientGolem.class, ConfigManager.ancientGolemSpawnWeight, 1, 1, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
 
   
        //GameRegistry.register(biomeCave = new BiomeCave());

        //dimensionCave = DimensionType.register("cave", "cave", 90, CaveProvider.class, false);
        //BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(biomeCave, 10000));

        GameRegistry.registerFuelHandler(new EmbersFuelHandler());
    }

    public static void registerFluids() { 
    }

    @SideOnly(Side.CLIENT)
    public static void registerColorHandlers() {
    //    Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemEmberJar.ColorHandler(), ember_jar);
       // Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemEmberCartridge.ColorHandler(), ember_cartridge);
       // Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemTyrfing.ColorHandler(), tyrfing);
        //Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemEmberBulb.ColorHandler(), mantle_bulb);
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRendering() { 

        RenderingRegistry.registerEntityRenderingHandler(EntityEmberPacket.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityEmberProjectile.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityAncientGolem.class, new RenderAncientGolem.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityEmberLight.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
    }
}
