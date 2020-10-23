package pixelbreadstudios.openprojects.obscuritycraft;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pixelbreadstudios.openprojects.obscuritycraft.CustomBlocks.CustomBlockManager;
import pixelbreadstudios.openprojects.obscuritycraft.CustomEnchantments.CustomEnchantmentsManager;
import pixelbreadstudios.openprojects.obscuritycraft.CustomItems.CustomItem;
import pixelbreadstudios.openprojects.obscuritycraft.CustomItems.CustomItemManager;

import java.lang.reflect.Field;
import java.util.HashMap;

public final class ObscurityCraft extends JavaPlugin implements Listener, CommandExecutor {

    public static ObscurityCraft plugin;
public  static CustomEnchantmentsManager CEM;
    @Override
    public void onEnable() {
        getLogger().info("ObscurityCraft is starting up");

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new CustomBlockManager(), this);
        getServer().getPluginManager().registerEvents(new CustomItemManager(), this);

        getCommand("customblock").setExecutor(new CustomBlockManager());
        getCommand("customitem").setExecutor(new CustomItemManager());

        CustomBlockManager.Setup();
        CustomItemManager.Setup();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        plugin = this;

        CEM = new CustomEnchantmentsManager();
        CustomEnchantmentsManager.Setup();
    }

    @Override
    public void onDisable() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            for (Enchantment enchantment : CustomEnchantmentsManager.customEnchantments) {
                if (byKey.containsKey(enchantment.getKey())) {
                    byKey.remove(enchantment.getKey());
                }
            }

            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            for (Enchantment enchantment : CustomEnchantmentsManager.customEnchantments) {
                if (byName.containsKey(enchantment.getName())) {
                    byName.remove(enchantment.getName());
                }
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("test")) {

        }
        return super.onCommand(sender, command, label, args);
    }

}
