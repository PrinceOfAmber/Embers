package teamroots.embers.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;
import teamroots.embers.EventManager;

public class RenderUtil {
    public static final float root2over2 = (float) Math.sqrt(2.0f) / 2.0f;
    public static int lightx = 0xF000F0;
    public static int lighty = 0xF000F0;
 
 
    

    @SideOnly(Side.CLIENT)
    public static void drawQuadGui(BufferBuilder BufferBuilder, double zLevel, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, double minU, double minV, double maxU, double maxV) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        BufferBuilder.pos(x1 + 0.0F, y1 + 0.0F, zLevel).tex(minU, maxV).endVertex();
        BufferBuilder.pos(x2 + 0.0F, y2 + 0.0F, zLevel).tex(maxU, maxV).endVertex();
        BufferBuilder.pos(x3 + 0.0F, y3 + 0.0F, zLevel).tex(maxU, minV).endVertex();
        BufferBuilder.pos(x4 + 0.0F, y4 + 0.0F, zLevel).tex(minU, minV).endVertex();
    }
 
 
}
