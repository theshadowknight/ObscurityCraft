package pixelbreadstudios.openprojects.obscuritycraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pixelbreadstudios.openprojects.obscuritycraft.CustomBlocks.CustomBlockManager;
import pixelbreadstudios.openprojects.obscuritycraft.CustomEnchantments.CustomEnchantmentsManager;

public final class ObscurityCraft extends JavaPlugin implements Listener, CommandExecutor {

    public static ObscurityCraft plugin;
public  static CustomEnchantmentsManager CEM;
    @Override
    public void onEnable() {
        getLogger().info("ObscurityCraft is starting up");
        plugin = this;
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new CustomBlockManager(), this);
        getCommand("customblock").setExecutor(new CustomBlockManager());
        CustomBlockManager.Setup();
       CEM = new CustomEnchantmentsManager();
        CustomEnchantmentsManager.Setup();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("test")) {

        }
        return super.onCommand(sender, command, label, args);
    }

}
