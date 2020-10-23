package pixelbreadstudios.openprojects.obscuritycraft.CustomBlocks;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.graalvm.compiler.lir.alloc.lsra.LinearScan;

import java.util.ArrayList;
import java.util.List;

public class CustomBlock {
    public String BlockId;
    public String name;
    public int CustomModelDataID;
public List<String> lore = new ArrayList<>();
    public CustomBlock(int a) {
        BlockId = CustomBlockManager.blockIds.get(a);
        CustomModelDataID=a+1;
        lore.add(ChatColor.RESET+""+ChatColor.WHITE+""+a);

    }

    public boolean OnClick(PlayerInteractEvent event, int id) {
        return false;
    }

    public boolean OnMine(BlockBreakEvent event, int id) {
        return false;
    }
}
