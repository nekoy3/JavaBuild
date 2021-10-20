package neko.aziplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeoutCancel extends BukkitRunnable {
	public Player p;
	public AziServer plugin;
	static boolean flag = false;
	public TimeoutCancel(Player p,AziServer plugin){
		this.p = p;
		this.plugin = plugin;
	}

	@Override
	public void run() {
	    chat(p,flag);
	    flag = true;
	}
	static void chat(Player p,boolean flag) {
		if (flag == false) {
	    	p.getServer().broadcastMessage(ChatColor.RED + "魔法実行時間がタイムアウトしました。");
			SimpleArrowShot.amount = 0;
	    }
	}
}