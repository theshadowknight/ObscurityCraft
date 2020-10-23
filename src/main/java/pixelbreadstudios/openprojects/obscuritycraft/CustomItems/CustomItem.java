package pixelbreadstudios.openprojects.obscuritycraft.CustomItems;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pixelbreadstudios.openprojects.obscuritycraft.CustomEnchantments.CustomEnchantmentsManager;

import java.util.ArrayList;
import java.util.List;

public class CustomItem {
    public String name;
    public List<String> lore = new ArrayList<>();
    public int CustomModelDataID;
    public ItemStack baseItem;
    public void OnAttack(){}
public  ItemStack MakeItem(){
        ItemStack is = baseItem.clone().asQuantity(1);

    is.addUnsafeEnchantment(CustomEnchantmentsManager.ce,CustomModelDataID+1);

    ItemMeta im = is.getItemMeta();
    im.setDisplayName(ChatColor.RESET+name);

    im.setLore(lore);
    im.setCustomModelData(CustomModelDataID);


    is.setItemMeta(im);

    return  is;
}
public  CustomItem(String n,List<String> l,int id, ItemStack bi ){
        baseItem = bi;
        name =n;
        lore=l;
        CustomModelDataID=id;
}
}
