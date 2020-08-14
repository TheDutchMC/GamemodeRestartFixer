package nl.thedutchmc.GamemodeRestartFixer;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;

public class SetHardcoreForWorld_1_16_R2 implements SetHardcoreForWorld {
	
	@SuppressWarnings("resource")
	@Override
	public void setHardcore(World world, boolean hardcore) {
		((CraftWorld) world).getHandle().worldDataServer.b.hardcore = hardcore;
	}
}
