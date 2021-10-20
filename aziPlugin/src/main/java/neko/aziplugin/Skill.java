package neko.aziplugin;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class Skill extends BukkitRunnable{
	protected AziServer plugin;
	private boolean isRunning = true;
	public Skill(AziServer plugin){
		this.plugin = plugin;
	}
	public void run(){
		fire();
	}
	abstract protected void fire();
	public void cancel(){
		super.cancel(); //スケジューラ処理のキャンセル
		isRunning = false;
	}
	public boolean isRunning(){
		return isRunning;
	}
}
