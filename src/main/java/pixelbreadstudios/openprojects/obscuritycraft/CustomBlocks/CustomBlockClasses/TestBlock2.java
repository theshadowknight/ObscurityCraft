package pixelbreadstudios.openprojects.obscuritycraft.CustomBlocks.CustomBlockClasses;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import pixelbreadstudios.openprojects.obscuritycraft.CustomBlocks.CustomBlock;

public class TestBlock2 extends CustomBlock {
    public TestBlock2(int a) {
        super(a);
        name = "Test Block2";
    }

    @Override
    public boolean OnClick(PlayerInteractEvent event, int id) {
        event.getPlayer().sendRawMessage("You clicked "+name+" with id " + id);
        return false;
    }

    @Override
    public boolean OnMine(BlockBreakEvent event, int id) {
        event.getPlayer().sendRawMessage("You broke "+name+" with id " + id);
        return false;
    }
}
