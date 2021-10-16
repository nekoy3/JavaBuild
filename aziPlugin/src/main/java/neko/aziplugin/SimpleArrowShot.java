package neko.aziplugin;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SimpleArrowShot extends Skill{
	private int shot;
	private int amount;
	private Player p;
	public SimpleArrowShot (AziServer plugin, int[] arg, Player player) {
		super(plugin);
		p = player;
		amount = 10 + arg[1] * 5;
		shot = arg[1] <= 20 ? 1 : 1 + (arg[1] - 20) / 20;
		int interval = arg[1] <= 10 ? 2 : 1;
		player.getServer().broadcastMessage(ChatColor.GOLD + "ぬん");
		runTaskTimer(plugin,30,interval);
	}
	protected void fire(){
		ArrayList<Entity> arrows=new ArrayList<Entity>();
		Location loc = p.getLocation().add(0,1f,0);
		for(int n=0;n != shot;n++){
			Arrow arrow=loc.getWorld().spawnArrow(loc.add(loc.getDirection()),loc.getDirection(),1f,12);
			arrows.add(arrow);
			arrow.setShooter(p);
			amount-=shot;
			if(amount <= 0){
				cancel();
			}
		}
		//new RemoveEntities(arrows).runTaskLater(plugin,5 * 20);
	}
}
