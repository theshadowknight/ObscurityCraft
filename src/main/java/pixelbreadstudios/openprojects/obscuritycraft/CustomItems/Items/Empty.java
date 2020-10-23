package pixelbreadstudios.openprojects.obscuritycraft.CustomItems.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pixelbreadstudios.openprojects.obscuritycraft.CustomEnchantments.CustomEnchantmentsManager;
import pixelbreadstudios.openprojects.obscuritycraft.CustomItems.CustomItem;

import java.util.ArrayList;
import java.util.List;

public class Empty extends CustomItem {
    @Override
    public ItemStack MakeItem() {
        ItemStack is = baseItem.clone().asQuantity(1);


        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.RESET+name);

        im.setLore(lore);
        im.setCustomModelData(CustomModelDataID);


        is.setItemMeta(im);

        return  is;
    }

    public Empty(int id) {
        super(id);
        baseItem = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        name = "";
        lore = new ArrayList<>();

    }
}
