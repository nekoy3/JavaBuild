package neko.aziplugin;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveEntities extends BukkitRunnable {
	private List<Entity> entities;
	public RemoveEntities(List<Entity> entities){
		this.entities = entities;
	}

	@Override
	public void run(){
		for(Entity entity : entities){
			entity.remove();
		}
	}
}