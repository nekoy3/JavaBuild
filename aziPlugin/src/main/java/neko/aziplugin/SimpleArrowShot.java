package neko.aziplugin;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SimpleArrowShot extends Skill{
	private int delay;
	static int amount;
	private int power;
	private int cost;
	private int spread;
	private float speed;
	private int startTick;
	private int tick;
	//private int continueTime;
	public Player p;
	public SimpleArrowShot (AziServer plugin, int[] arg, Player player) {
		/*
		 * arg[1] -> power 一撃の威力 4~24 1dmgに2 14以上でcritical
		 * arg[2] -> delay 間隔 1~17tick cost 2tick減に1
		 * arg[3] -> amount 矢の数 10~85 cost 2本に1
		 * arg[4] -> spread 拡散減衰率 (拡散率19~4%) cost 2%に1
		 * arg[5] -> speed 発射速度 1~2.5 0.2に1
		 * all -> cost 発動にかかるコスト（発動時間、デバフ、消耗） 15~139
		 * startTick -> 発動にかかる時間 cost/2
		 */
		super(plugin);
		this.p = player;
		try {
			tick = 0;
			power = (int) (4 + (arg[1] * 1.3 ));
			delay = 17 - arg[2];
			amount = 10 + arg[3] * 5;
			spread = 19 - arg[4];
			speed = 1 + (float)arg[5]/10;
			cost = amount/2 + arg[2]/2 + power*2 + arg[4]/2 + arg[5]/2;
			startTick = cost/2;
			runTaskTimer(plugin,startTick,delay); 
			//continueTime = startTick + delay * amount;
			
		} catch (Exception e) {
			player.getServer().broadcastMessage(ChatColor.RED + "魔法式発動に失敗");
			Location loc = p.getLocation().add(0,1.5f,0);
			loc.getWorld().createExplosion(loc, 5f, false, false);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
			}
			cancel();
		}
		player.getServer().broadcastMessage(ChatColor.GOLD + "ぬん");
	}
	
	protected void fire(){
		ArrayList<Entity> arrows = new ArrayList<Entity>();
		Location loc = p.getLocation().add(0,1.5f,0);
		boolean critical = power >= 14;
		Arrow arrow = loc.getWorld().spawnArrow(loc.add(loc.getDirection()),loc.getDirection(),speed,spread); //場所, 方向, 速度, 拡散率
		arrows.add(arrow);
		arrow.setShooter(p); //発射者を設定
		arrow.setDamage(power);
		arrow.setCritical(critical);
		amount--;
		if(amount < 0){
			cancel();
		}
		new RemoveEntities(arrows).runTaskLater(plugin, 7 * 20); //tick後に実行
		new TimeoutCancel(p,plugin).runTaskLater(plugin, 15 * 20); //Timerはtick後からtick周期で実行
	}
}
