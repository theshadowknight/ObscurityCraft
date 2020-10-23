package pixelbreadstudios.openprojects.obscuritycraft.CustomBlocks;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pixelbreadstudios.openprojects.obscuritycraft.CustomBlocks.CustomBlockClasses.TestBlock;
import pixelbreadstudios.openprojects.obscuritycraft.CustomBlocks.CustomBlockClasses.TestBlock2;
import pixelbreadstudios.openprojects.obscuritycraft.ObscurityCraft;

import javax.xml.crypto.Data;
import java.sql.Array;
import java.util.*;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class CustomBlockManager implements Listener, CommandExecutor {
    public static HashMap<Location, DataClass> customBlocksStorage = new HashMap<>();
    public static List<CustomBlock> customBlocks = new ArrayList();
    public static List<Location> buffercheckted = new ArrayList();

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void CustomBlockPace(BlockPlaceEvent event) {
        if (event.getBlock().getBlockData().getMaterial() != Material.MUSHROOM_STEM) {
            return;
        }
        if (event.getItemInHand().getItemMeta() == null) {
            return;
        }
        if (event.getItemInHand().getItemMeta().getLore() == null) {
            return;
        }
        if (event.getItemInHand().getItemMeta().getLore().size() < 1) {
            return;
        }

        int id = Integer.valueOf(ChatColor.stripColor(event.getItemInHand().getItemMeta().getLore().get(0)));

        customBlocksStorage.put(event.getBlock().getLocation(), new DataClass(id, false));
        Block block = event.getBlock();
        BlockData blockData = block.getBlockData();
        blockData = getServer().createBlockData(customBlocks.get(customBlocksStorage.get(event.getBlock().getLocation()).id).BlockId);

        block.setBlockData(blockData, false);


    }

    public static BlockData CustomBlockDataMaker(CustomBlockBase baseblock, int FaceID) {

        String str = "minecraft:";
        switch (baseblock) {
            case STEM:
                str += "mushroom_stem";
                break;
            case RED:
                str += "red_mushroom_block";
                break;
            case BROWN:
                str += "brown_mushroom_block";
                break;

        }
        BlockData blockData = getServer().createBlockData(str);
        // str+="[";
        MultipleFacing mf = (MultipleFacing) blockData;
        String bits = Integer.toBinaryString(FaceID);
        // getLogger().info(bits);

        for (int a = bits.length(); a < 6; a++) {

            bits = "0" + bits;
        }
        //  getLogger().info(bits);

        for (int a = 0; a < bits.length(); a++) {
            mf.setFace(BlockFace.values()[a], bits.charAt(a) == '1');

        }

        // str+="]";
        String magic = mf.getAsString();
        magic = magic.replace("minecraft:mushroom_stem[", "").replace("]", "");
        magic = '"' + magic + "\": [\n" +
                "        { \"model\": \"block/testblock\" }\n" +
                "           \n" +
                "        ]";
        // getLogger().info(magic);

        return mf;

    }

    /*   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
       public void onPrePlacingCustomBlock(PlayerInteractEvent event) {
           if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
               return;

           ItemStack item = event.getItem();


           Player player = event.getPlayer();
           Block placedAgainst = event.getClickedBlock();
           Block target;
           Material type = placedAgainst.getType();

           target = placedAgainst.getRelative(event.getBlockFace());
           if (target.getType() != Material.AIR)
               return;


           // determines the old informations of the block
           BlockData curentBlockData = target.getBlockData();
           BlockState currentBlockState = target.getState();

           // determines the new block data of the block
           int id = Integer.valueOf(event.getItem().getItemMeta().getLore().get(0));

           customBlocksStorage.put(target.getLocation(), new DataClass(id, false));
           // set the new block
           target.setBlockData(getServer().createBlockData(customBlocks.get(customBlocksStorage.get(target.getLocation()).id).BlockId), false); // false to cancel physic

           BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(target, currentBlockState, placedAgainst, item, player,
                   true, event.getHand());
           Bukkit.getPluginManager().callEvent(blockPlaceEvent);
           if (!blockPlaceEvent.canBuild() || blockPlaceEvent.isCancelled()) {
               target.setBlockData(curentBlockData, false); // false to cancel physic
           }
           event.setCancelled(true);
           if (!player.getGameMode().equals(GameMode.CREATIVE))
               item.setAmount(item.getAmount() - 1);

       }
   */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPrePlacingCustomBlock(PlayerInteractEvent event) {


        if (event.getClickedBlock() == null) {
            return;
        }
        if (event.getClickedBlock().getBlockData().getMaterial() != Material.MUSHROOM_STEM) {
            return;
        }
        if (customBlocksStorage.containsKey(event.getClickedBlock().getLocation())) {
            Block block = event.getClickedBlock();
            BlockData blockData = block.getBlockData();
            blockData = getServer().createBlockData(customBlocks.get(customBlocksStorage.get(event.getClickedBlock().getLocation()).id).BlockId);
            block.setBlockData(blockData, false);
            event.getClickedBlock().getState().update(true, false);
            customBlocks.get(customBlocksStorage.get(event.getClickedBlock().getLocation()).id).OnClick(event, customBlocksStorage.get(event.getClickedBlock().getLocation()).id);
        }
    }

    /* @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
     public void CustomBlockChange(BlockPhysicsEvent event) {
         if (event.getBlock().getBlockData().getMaterial() == Material.MUSHROOM_STEM) {
             if (customBlocksStorage.containsKey(event.getBlock().getLocation())) {
                 //Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ObscurityCraft.plugin, () -> {
                 RefreshBlock(event.getBlock().getLocation());


                 // }, 5);



                 // ObscurityCraft.plugin.getLogger().info("canceled" + event.getBlock().getLocation());
                 getLogger().info("size=" + String.valueOf(buffercheckted.size()));
                 for (DataClass dc : customBlocksStorage.values()) {
                    // dc.checked = false;
                 }
                 event.setCancelled(true);
                 //  }
             }


         }
     }
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onMushroomPhysics(BlockPhysicsEvent event) {
        if (event.getBlock() == null) {
            return;
        }
        if (event.getBlock().getBlockData().getMaterial() != Material.MUSHROOM_STEM) {
            return;
        }
        if (customBlocksStorage.containsKey(event.getBlock().getLocation())) {
            Block block = event.getBlock();
            BlockData blockData = block.getBlockData();
            blockData = getServer().createBlockData(customBlocks.get(customBlocksStorage.get(event.getBlock().getLocation()).id).BlockId);
            block.setBlockData(blockData, false);
            event.getBlock().getState().update(true, false);
        }

    }

    public static void RefreshBlock(Location l) {
        if (l.getBlock() != null) {
            if (customBlocksStorage.get(l) != null) {
                if (!customBlocksStorage.get(l).checked) {
                    customBlocksStorage.get(l).checked = true;
                    if (l.getBlock().getBlockData().getMaterial() == Material.MUSHROOM_STEM) {

                        getLogger().info("At" + l + " set " + customBlocks.get(customBlocksStorage.get(l).id).BlockId);
                        l.getBlock().setBlockData(
                                getServer().createBlockData(
                                        customBlocks.get(
                                                customBlocksStorage.get(l).id
                                        ).BlockId
                                )
                                , false);
                        l.getBlock().getState().update(true, false);
                        for (int x = -1; x < 2; x++) {
                            for (int y = -1; y < 2; y++) {
                                for (int z = -1; z < 2; z++) {
                                    RefreshBlock(l.add(x, y, z));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*   @EventHandler
       public void CustomBlockClick(PlayerInteractEvent event) {
           if (event.getClickedBlock() != null) {
               if (event.getClickedBlock().getBlockData() != null) {

                   if (Objects.requireNonNull(event.getClickedBlock()).getBlockData().getMaterial() == Material.MUSHROOM_STEM) {

                       if (customBlocksStorage.containsKey(event.getClickedBlock().getLocation())) {
                           event.setCancelled(customBlocks.get(
                                   blockIds.indexOf(event.getClickedBlock().getBlockData().getAsString())

                                   ).OnClick(event, customBlocksStorage.get(event.getClickedBlock().getLocation()).id)
                           );
                       }


                   }
               }
           }
       }
   */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void CustomBlockMine(BlockBreakEvent event) {

        if (event.getBlock().getBlockData().getMaterial() != Material.MUSHROOM_STEM) {
            return;
        }

        if (customBlocksStorage.containsKey(event.getBlock().getLocation())) {
            event.setCancelled(customBlocks.get(
                    blockIds.indexOf(event.getBlock().getBlockData().getAsString())

                    ).OnMine(event, customBlocksStorage.get(event.getBlock().getLocation()).id)
            );
            customBlocksStorage.remove(event.getBlock().getLocation());
        }


    }

    public static void Setup() {
        for (int a = 0; a < 64; a++) {
            blockIds.add(CustomBlockDataMaker(CustomBlockBase.STEM, a).getAsString());
        }
        int index = 0;
        customBlocks.add(new TestBlock(index));
        index++;
        customBlocks.add(new TestBlock2(index));
        index++;

    }

    public static List<String> blockIds = new ArrayList();// = Arrays.asList("minecraft:mushroom_stem[down=true,east=true,north=true,south=true,up=true,west=true]", "minecraft:mushroom_stem[down=false,east=true,north=true,south=true,up=true,west=true]");

    public static ItemStack MakeBlock(int id) {
        ItemStack is = new ItemStack(Material.MUSHROOM_STEM);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.RESET + customBlocks.get(id).name);
        im.setLore(customBlocks.get(id).lore);
        im.setCustomModelData(customBlocks.get(id).CustomModelDataID);
        is.setItemMeta(im);
        return is.clone();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equals("get")) {
            if (sender instanceof Player) {
                if (args[1].equals("list")) {
                    Player pl = (Player) sender;
                    for (int a = 0; a < customBlocks.size(); a++) {
                        TextComponent message = new TextComponent(customBlocks.get(a).name + ChatColor.GREEN + " [Click to give]");
                        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, ("/" + command.getName() + " get " + a)));
                        pl.spigot().sendMessage(message);

                        //  pl.getInventory().addItem(MakeBlock(a));
                    }
                } else {
                    Player pl = (Player) sender;
                    pl.getInventory().addItem(MakeBlock(Integer.parseInt(args[1])));
                }
            }
        }
        if (args[0].equals("try")) {
            if (sender instanceof Player) {
                Player pl = (Player) sender;
                pl.sendRawMessage(pl.getLocation().add(0, -1, 0).getBlock().getBlockData().getAsString());
            }
        }
        return true;
    }

    public static void OpenInventoryWithCustomBlocks(Player pl) {
        //  Inventory inv =
    }
}
