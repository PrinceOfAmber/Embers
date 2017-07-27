package teamroots.embers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import teamroots.embers.proxy.ClientProxy;
import teamroots.embers.util.*;
import java.util.*;

public class EventManager {
    public static boolean hasRenderedParticles = false;
    public static float emberEyeView = 0; 
    public static float frameTime = 0;
    public static float frameCounter = 0;
    public static long prevTime = 0;
    public static EnumHand lastHand = EnumHand.MAIN_HAND;
    public static float starlightRed = 255;
    public static float starlightGreen = 32;
    public static float starlightBlue = 255;
    public static float tickCounter = 0;
    public static double currentEmber = 0;
    public static boolean allowPlayerRenderEvent = true;
    public static int ticks = 0;
    public static float prevCooledStrength = 0;
    public static boolean acceptUpdates = true;
    public static Map<BlockPos, TileEntity> toUpdate = new HashMap<BlockPos, TileEntity>();
    public static Map<BlockPos, TileEntity> overflow = new HashMap<BlockPos, TileEntity>();
    static EntityPlayer clientPlayer = null;
    double gaugeAngle = 0;
    Random random = new Random();
 
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent event) {
        ResourceLocation particleGlow = new ResourceLocation("embers:entity/particle_mote");
        event.getMap().registerSprite(particleGlow);
        ResourceLocation particleSparkle = new ResourceLocation("embers:entity/particle_star");
        event.getMap().registerSprite(particleSparkle);
        ResourceLocation particleSmoke = new ResourceLocation("embers:entity/particle_smoke");
        event.getMap().registerSprite(particleSmoke);
    }

 
 
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.START) {
            ticks++;
            ClientProxy.particleRenderer.updateParticles();
 
        }
    }

//    @SubscribeEvent
//    @SideOnly(Side.CLIENT)
//    public void onPlayerRender(RenderPlayerEvent.Pre event) {
//        if (event.getEntityPlayer() != null) {
//            if (Minecraft.getMinecraft().inGameHasFocus || event.getEntityPlayer().getUniqueID().compareTo(Minecraft.getMinecraft().player.getUniqueID()) != 0) {
//                event.setCanceled(!allowPlayerRenderEvent);
//            }
//        }
//    }
//
//    @SubscribeEvent(priority = EventPriority.LOW)
//    public void onEntityDamaged(LivingHurtEvent event) {
//        if (event.getSource().damageType == RegistryManager.damage_ember.damageType) {
//            if (event.getEntityLiving().isPotionActive(Potion.getPotionFromResourceLocation("fire_resistance"))) {
//                event.setAmount(event.getAmount() * 0.5f);
//            }
//        }
//        
//    } 
// 

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderAfterWorld(RenderWorldLastEvent event) {
        tickCounter++;
        if (Embers.proxy instanceof ClientProxy) {
            GlStateManager.pushMatrix();
            ClientProxy.particleRenderer.renderParticles(clientPlayer, event.getPartialTicks());
            GlStateManager.popMatrix();
        }
 
    }
 
}
