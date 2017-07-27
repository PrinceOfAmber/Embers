package teamroots.embers.item;

import net.minecraft.item.ItemStack;

public interface IEmberItem {
    double getEmber(ItemStack stack);

    double getEmberCapacity(ItemStack stack);

    void setEmber(ItemStack stack, double value);

    void setEmberCapacity(ItemStack stack, double value);

    double addAmount(ItemStack stack, double value, boolean doAdd);

    double removeAmount(ItemStack stack, double value, boolean doRemove);
}
