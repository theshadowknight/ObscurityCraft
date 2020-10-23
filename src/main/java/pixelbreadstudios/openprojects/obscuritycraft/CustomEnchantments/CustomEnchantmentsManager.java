package pixelbreadstudios.openprojects.obscuritycraft.CustomEnchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import pixelbreadstudios.openprojects.obscuritycraft.CustomEnchantments.Enchantments.CustomEnchantment;
import pixelbreadstudios.openprojects.obscuritycraft.ObscurityCraft;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.enchantments.Enchantment.registerEnchantment;

public class CustomEnchantmentsManager {
    public static List<Enchantment> customEnchantments = new ArrayList<>();

    public static CustomEnchantment ce;

    public static void Setup(){
        ce = new CustomEnchantment(new NamespacedKey(ObscurityCraft.plugin,"customenchantment"));
        registerEnchantment(ce);
        customEnchantments.add(ce);
    }
}
