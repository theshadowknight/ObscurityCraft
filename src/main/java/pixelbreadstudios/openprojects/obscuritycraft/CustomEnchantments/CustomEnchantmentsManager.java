package pixelbreadstudios.openprojects.obscuritycraft.CustomEnchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import pixelbreadstudios.openprojects.obscuritycraft.CustomEnchantments.Enchantments.CustomEnchantment;
import pixelbreadstudios.openprojects.obscuritycraft.ObscurityCraft;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class CustomEnchantmentsManager {
    public static List<Enchantment> customEnchantments = new ArrayList<>();

    public static CustomEnchantment ce;

    public static void Setup(){

        ce = new CustomEnchantment(new NamespacedKey(ObscurityCraft.plugin,"customenchantment"));
        registerEnchantment(ce);
        customEnchantments.add(ce);
    }
    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if (registered) {
            // It's been registered!
        }
    }
}
