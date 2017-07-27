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
  
import teamroots.embers.util.Misc; 

import java.util.ArrayList;
import java.util.List;

public class RegistryManager {
 
    public static DamageSource damage_ember;

    public static Material unpushable;

    public static Biome biome_cave;

    
    public static IWorldGenerator world_gen_small_ruin;
 
 
 

    public static void registerAll() {

    //    CapabilityManager.INSTANCE.register(IEmberCapability.class, new EmberCapabilityStorage(), DefaultEmberCapability.class);

        damage_ember = new DamageEmber();

 
  

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

        EntityRegistry.addSpawn(EntityAncientGolem.class, 25, 1, 1, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
 
 
    }
 

    @SideOnly(Side.CLIENT)
    public static void registerEntityRendering() { 

        RenderingRegistry.registerEntityRenderingHandler(EntityEmberPacket.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityEmberProjectile.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityAncientGolem.class, new RenderAncientGolem.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityEmberLight.class, new RenderEmberPacket(Minecraft.getMinecraft().getRenderManager()));
    }
}
