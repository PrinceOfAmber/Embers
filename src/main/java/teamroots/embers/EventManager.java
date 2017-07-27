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

    @SubscribeEvent
    public void onServerTick(WorldTickEvent event) {
        if (event.world.provider.getDimensionType() == DimensionType.OVERWORLD) {
//            if (Misc.random.nextInt(400) == 0) {
//                EmberGenUtil.offX++;
//                EmberWorldData.get(event.world).markDirty();
//            }
//            if (Misc.random.nextInt(400) == 0) {
//                EmberGenUtil.offZ++;
//                EmberWorldData.get(event.world).markDirty();
//            }
            //PacketHandler.INSTANCE.sendToAll(new MessageEmberGenOffset(EmberGenUtil.offX, EmberGenUtil.offZ));
        }
    }
 

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onGameOverlayRender(RenderGameOverlayEvent.Post e) {
        if (e.getType() == ElementType.TEXT) {
            EventManager.frameCounter++;
            EventManager.frameTime = (System.nanoTime() - prevTime) / 1000000000.0f;
            EventManager.prevTime = System.nanoTime();
        }
        EntityPlayer player = Minecraft.getMinecraft().player;
        boolean showBar = false;

        int w = e.getResolution().getScaledWidth();
        int h = e.getResolution().getScaledHeight();

        int x = w / 2;
        int y = h / 2;
//        if (player.getHeldItemMainhand() != ItemStack.EMPTY) {
//            if (player.getHeldItemMainhand().getItem() instanceof ItemEmberGauge) {
//                showBar = true;
//            }
//        }
//        if (player.getHeldItemOffhand() != ItemStack.EMPTY) {
//            if (player.getHeldItemOffhand().getItem() instanceof ItemEmberGauge) {
//                showBar = true;
//            }
//        }

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder b = tess.getBuffer();
        if (showBar) {
            World world = player.getEntityWorld();
            if (e.getType() == ElementType.TEXT) {
                GlStateManager.disableDepth();
                GlStateManager.disableCull();
                GlStateManager.pushMatrix();
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("embers:textures/gui/ember_meter_overlay.png"));
                GlStateManager.color(1f, 1f, 1f, 1f);

                int offsetX = 0;

                b.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
                RenderUtil.drawQuadGui(b, 0, x - 16, y - 4, x + 16, y - 4, x + 16, y - 36, x - 16, y - 36, 0, 0, 1, 1);
                tess.draw();

                double angle = 195.0;
              //  EmberWorldData data = EmberWorldData.get(world);
                if (player != null) {
                    //if (data.emberData != null){
                    //if (data.emberData.containsKey(""+((int)player.posX) / 16 + " " + ((int)player.posZ) / 16)){
                   // double ratio = EmberGenUtil.getEmberDensity(world.getSeed(), player.getPosition().getX(), player.getPosition().getZ());
                    if (gaugeAngle == 0) {
                 //       gaugeAngle = 165.0 + 210.0 * ratio;
                    } else {
                   //     gaugeAngle = gaugeAngle * 0.99 + 0.01 * (165.0 + 210.0 * ratio);
                    }
                    //}
                    //}
                }

                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("embers:textures/gui/ember_meter_pointer.png"));
                GlStateManager.translate(x, y - 20, 0);
                GlStateManager.rotate((float) gaugeAngle, 0, 0, 1);
                b.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
                RenderUtil.drawQuadGui(b, 0.0, -2.5f, 13.5f, 13.5f, 13.5f, 13.5f, -2.5f, -2.5f, -2.5f, 0, 0, 1, 1);
                tess.draw();

                GlStateManager.popMatrix();
                GlStateManager.enableCull();
                GlStateManager.enableDepth();
            }
        }
        World world = player.getEntityWorld();
        RayTraceResult result = player.rayTrace(6.0, e.getPartialTicks());

//        if (result != null) {
//            if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
//                IBlockState state = world.getBlockState(result.getBlockPos());
//                if (state.getBlock() instanceof IDial) {
//                    List<String> text = ((IDial) state.getBlock()).getDisplayInfo(world, result.getBlockPos(), state);
//                    for (int i = 0; i < text.size(); i++) {
//                        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text.get(i), x - Minecraft.getMinecraft().fontRenderer.getStringWidth(text.get(i)) / 2, y + 40 + 11 * i, 0xFFFFFF);
//                    }
//                }
//            }
//        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("minecraft:textures/gui/icons.png"));
        GlStateManager.enableDepth();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.START) {
            ticks++;
            ClientProxy.particleRenderer.updateParticles();

//            EntityPlayer player = Minecraft.getMinecraft().player;
//            if (player != null) {
//                World world = player.getEntityWorld();
//                RayTraceResult result = player.rayTrace(6.0, Minecraft.getMinecraft().getRenderPartialTicks());
//                if (result != null) {
//                    if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
//                        IBlockState state = world.getBlockState(result.getBlockPos());
//                        if (state.getBlock() instanceof IDial) {
//                            ((IDial) state.getBlock()).updateTEData(world, state, result.getBlockPos());
//                        }
//                    }
//                }
//            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPlayerRender(RenderPlayerEvent.Pre event) {
        if (event.getEntityPlayer() != null) {
            if (Minecraft.getMinecraft().inGameHasFocus || event.getEntityPlayer().getUniqueID().compareTo(Minecraft.getMinecraft().player.getUniqueID()) != 0) {
                event.setCanceled(!allowPlayerRenderEvent);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onEntityDamaged(LivingHurtEvent event) {
        if (event.getSource().damageType == RegistryManager.damage_ember.damageType) {
            if (event.getEntityLiving().isPotionActive(Potion.getPotionFromResourceLocation("fire_resistance"))) {
                event.setAmount(event.getAmount() * 0.5f);
            }
        }
        if (event.getSource().getTrueSource() != null) {
            if (event.getSource().getTrueSource() instanceof EntityPlayer) {
//                if (((EntityPlayer) event.getSource().getTrueSource()).getHeldItemMainhand().getItem() == RegistryManager.tyrfing) {
//                    if (!event.getEntity().world.isRemote) {
//                        PacketHandler.INSTANCE.sendToAll(new MessageTyrfingBurstFX(event.getEntity().posX, event.getEntity().posY + event.getEntity().height / 2.0f, event.getEntity().posZ));
//                    }
//                    EntityPlayer p = ((EntityPlayer) event.getSource().getTrueSource());
//                    event.setAmount((event.getAmount() / 4.0f) * (4.0f + (float) event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue() * 1.0f));
//                }
//                if (((EntityPlayer) event.getSource().getTrueSource()).getHeldItemMainhand() != ItemStack.EMPTY) {
//                    if (((EntityPlayer) event.getSource().getTrueSource()).getHeldItemMainhand().getItem() instanceof IEmberChargedTool) {
//                        if (((IEmberChargedTool) ((EntityPlayer) event.getSource().getTrueSource()).getHeldItemMainhand().getItem()).hasEmber(((EntityPlayer) event.getSource().getTrueSource()).getHeldItemMainhand()) || ((EntityPlayer) event.getSource().getTrueSource()).capabilities.isCreativeMode) {
//                            event.getEntityLiving().setFire(1);
//                            if (!event.getEntityLiving().getEntityWorld().isRemote) {
//                                PacketHandler.INSTANCE.sendToAll(new MessageEmberBurstFX(event.getEntityLiving().posX, event.getEntityLiving().posY + event.getEntityLiving().getEyeHeight() / 1.5, event.getEntityLiving().posZ));
//                                ((EntityPlayer) event.getSource().getTrueSource()).getHeldItemMainhand().getTagCompound().setBoolean("didUse", true);
//                            }
//                        } else {
//                            event.setCanceled(true);
//                        }
//                    }
//                }
            }
        }
    } 
 

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderAfterWorld(RenderWorldLastEvent event) {
        tickCounter++;
        if (Embers.proxy instanceof ClientProxy) {
            GlStateManager.pushMatrix();
            ClientProxy.particleRenderer.renderParticles(clientPlayer, event.getPartialTicks());
            GlStateManager.popMatrix();
        }
        List<TileEntity> list = Minecraft.getMinecraft().world.loadedTileEntityList;
        GlStateManager.pushMatrix();
        for (int i = 0; i < list.size(); i++) {
            TileEntitySpecialRenderer render = TileEntityRendererDispatcher.instance.getRenderer(list.get(i));
//            if (render instanceof ITileEntitySpecialRendererLater) {
//                double x = Minecraft.getMinecraft().player.lastTickPosX + Minecraft.getMinecraft().getRenderPartialTicks() * (Minecraft.getMinecraft().player.posX - Minecraft.getMinecraft().player.lastTickPosX);
//                double y = Minecraft.getMinecraft().player.lastTickPosY + Minecraft.getMinecraft().getRenderPartialTicks() * (Minecraft.getMinecraft().player.posY - Minecraft.getMinecraft().player.lastTickPosY);
//                double z = Minecraft.getMinecraft().player.lastTickPosZ + Minecraft.getMinecraft().getRenderPartialTicks() * (Minecraft.getMinecraft().player.posZ - Minecraft.getMinecraft().player.lastTickPosZ);
//                ((ITileEntitySpecialRendererLater) render).renderLater(list.get(i), list.get(i).getPos().getX() - x, list.get(i).getPos().getY() - y, list.get(i).getPos().getZ() - z, Minecraft.getMinecraft().getRenderPartialTicks());
//            }
        }
        GlStateManager.popMatrix();
    }

    @SubscribeEvent
    public void onBlockBreak(BreakSpeed event) {
        event.getOriginalSpeed();
    }

//    @SubscribeEvent
//    public void onWorldTick(TickEvent.WorldTickEvent event) {
//        if (!event.world.isRemote && event.phase == TickEvent.Phase.END) {
//            NBTTagList list = new NBTTagList();
//            acceptUpdates = false;
//            TileEntity[] updateArray = toUpdate.values().toArray(new TileEntity[0]);
//            acceptUpdates = true;
//            for (Entry<BlockPos, TileEntity> e : overflow.entrySet()) {
//                toUpdate.put(e.getKey(), e.getValue());
//            }
//            overflow.clear();
//            for (int i = 0; i < updateArray.length; i++) {
//                TileEntity t = updateArray[i];
//                list.appendTag(t.getUpdateTag());
//            }
////            if (!list.hasNoTags()) {
////                NBTTagCompound tag = new NBTTagCompound();
////                tag.setTag("data", list);
////                PacketHandler.INSTANCE.sendToAll(new MessageTEUpdate(tag));
////            }
//            toUpdate.clear();
//        }
//    }

//    public static void markTEForUpdate(BlockPos pos, TileEntity tile) {
//        if (!tile.getWorld().isRemote && acceptUpdates) {
//            if (!toUpdate.containsKey(pos)) {
//                toUpdate.put(pos, tile);
//            } else {
//                toUpdate.replace(pos, tile);
//            }
//        } else if (!tile.getWorld().isRemote) {
//            if (!overflow.containsKey(pos)) {
//                overflow.put(pos, tile);
//            } else {
//                overflow.replace(pos, tile);
//            }
//        }
//    }

    @SideOnly(Side.CLIENT)
    public static void drawScaledCustomSizeModalRect(double x, double y, float u, float v, float uWidth, float vHeight, double width, double height, float tileWidth, float tileHeight) {
        float f = 1.0F / tileWidth;
        float f1 = 1.0F / tileHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder BufferBuilder = tessellator.getBuffer();
        BufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        BufferBuilder.pos(x, y + height, 0.0D).tex((double) (u * f), (double) ((v + vHeight) * f1)).endVertex();
        BufferBuilder.pos(x + width, y + height, 0.0D).tex((double) ((u + uWidth) * f), (double) ((v + vHeight) * f1)).endVertex();
        BufferBuilder.pos(x + width, y, 0.0D).tex((double) ((u + uWidth) * f), (double) (v * f1)).endVertex();
        BufferBuilder.pos(x, y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();
        tessellator.draw();
    }
}
