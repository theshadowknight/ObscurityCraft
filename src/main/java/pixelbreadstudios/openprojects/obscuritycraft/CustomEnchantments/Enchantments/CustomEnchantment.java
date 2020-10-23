package pixelbreadstudios.openprojects.obscuritycraft.CustomEnchantments.Enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class CustomEnchantment extends Enchantment {
    public CustomEnchantment(NamespacedKey key) {
        super(key);
    }
    public static NamespacedKey nk;

    @Override
    public String getName() {
        return "CustomEnchantment";
    }

    @Override
    public int getMaxLevel() {
        return Short.MAX_VALUE;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
