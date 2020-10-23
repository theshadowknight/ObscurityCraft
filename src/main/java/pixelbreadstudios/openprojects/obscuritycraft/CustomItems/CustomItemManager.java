package pixelbreadstudios.openprojects.obscuritycraft.CustomItems;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import pixelbreadstudios.openprojects.obscuritycraft.CustomItems.Items.CraftingArrow1;
import pixelbreadstudios.openprojects.obscuritycraft.CustomItems.Items.CraftingArrow2;
import pixelbreadstudios.openprojects.obscuritycraft.CustomItems.Items.Empty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class CustomItemManager implements CommandExecutor, Listener {
    public static List<CustomItem> customItems = new ArrayList<>();

    public static void Setup() {
        int id = 1;
        customItems.add(new Empty(id));
        id++;
        customItems.add(new CraftingArrow1(id));
        id++;
        customItems.add(new CraftingArrow2(id));
        id++;
    }

    @EventHandler
    public void OnItemClick(InventoryClickEvent event) {
        if (event.getCursor() != null) {
            if (event.getCursor().getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) {
                if (event.getCursor().getItemMeta() != null) {
                    if (event.getCursor().getItemMeta().getDisplayName().equals("")) {
                        if (!event.getWhoClicked().isOp()) {
                            event.setCancelled(true);
                        }
                    }
                }

            }
        }
        if (event.getCurrentItem() != null) {
            if (event.getCurrentItem().getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) {
                if (event.getCurrentItem().getItemMeta() != null) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("")) {
                        if (!event.getWhoClicked().isOp()) {
                            event.setCancelled(true);
                        }
                    }
                }

            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equals("get")) {
            if (sender instanceof Player) {
                if (args[1].equals("list")) {
                    Player pl = (Player) sender;
                    for (int a = 0; a < customItems.size(); a++) {
                        TextComponent message = new TextComponent(customItems.get(a).name + ChatColor.GREEN + " [Click to give]");
                        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, ("/" + command.getName() + " get " + a)));
                        pl.spigot().sendMessage(message);

                    }
                } else {
                    Player pl = (Player) sender;
                    pl.getInventory().addItem(customItems.get(Integer.parseInt(args[1])).MakeItem());
                }
            }
        }
        return false;
    }

}
