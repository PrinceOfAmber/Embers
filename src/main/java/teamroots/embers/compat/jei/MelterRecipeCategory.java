package teamroots.embers.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import teamroots.embers.Embers;

import java.util.List;

public class MelterRecipeCategory extends BlankRecipeCategory {
    public static ResourceLocation texture = new ResourceLocation("embers:textures/gui/jei_melter.png");
    private final IDrawable background;
    private final String name;

    public MelterRecipeCategory(IGuiHelper helper) {

        this.background = helper.createDrawable(texture, 0, 0, 108, 128);
        this.name = I18n.format("embers.jei.recipe.melter");
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public String getUid() {
        return "embers.melter";
    }

    @Override
    public void setRecipe(IRecipeLayout layout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = layout.getItemStacks();

        stacks.init(0, true, 7, 27);

        if (ingredients.getInputs(ItemStack.class) instanceof List && ingredients.getInputs(ItemStack.class).size() > 0) {
            stacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
        }

        IGuiFluidStackGroup fluid = layout.getFluidStacks();
        fluid.init(1, true, 84, 20, 16, 32, 1500, true, null);
        fluid.set(1, ingredients.getOutputs(FluidStack.class).get(0));
    }

    @Override
    public String getModName() {
        return Embers.MODID;
    }
}
