package teamroots.embers.research;

import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ResearchCategory {
    public String name = "";
    public double u = 192.0;
    public double v = 0;
    public ResourceLocation texture = new ResourceLocation("embers:textures/gui/codex_index.png");
    public ArrayList<ResearchBase> researches = new ArrayList<ResearchBase>();

    public ResearchCategory(String name, double v) {
        this.name = name;
        this.v = v;
    }

    public ResearchCategory(String name, ResourceLocation loc, double u, double v) {
        this.name = name;
        this.v = v;
        this.u = u;
        this.texture = loc;
    }

    public ResearchCategory addResearch(ResearchBase base) {
        researches.add(base);
        return this;
    }
}
