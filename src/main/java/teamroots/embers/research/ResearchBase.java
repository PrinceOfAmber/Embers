package teamroots.embers.research;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ResearchBase {
    public String name = "";
    public double v = 0;
    public ItemStack icon = ItemStack.EMPTY;
    public int x = 0;
    public int y = 0;
    public List<ResearchBase> ancestors = new ArrayList<ResearchBase>();

    public float selectedAmount = 0;
    public float selectionTarget = 0;

    public ResearchBase(String location, ItemStack icon, double x, double y) {
        this.name = location;
        this.icon = icon;
        this.x = 48 + (int) (x * 24);
        this.y = 48 + (int) (y * 24);
    }

    public ResearchBase addAncestor(ResearchBase base) {
        this.ancestors.add(base);
        return this;
    }

    @SideOnly(Side.CLIENT)
    public String getTitle() {
        return I18n.format("embers.research." + name + ".title");
    }

    @SideOnly(Side.CLIENT)
    public static List<String> getLines(String s, int width) {
        return Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(s, width);
    }
}
