package neko.aziplugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class AziServer extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getLogger().info("Hello, sushi!");
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player my = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("ranking")) {
			//Player p = getPlayer(args[0]);
			if(args.length == 0) {
				sender.sendMessage("usage: /ranking <gameID>\n詳しくはインフォメーションを参照してください。");
			} else if (args[0].equalsIgnoreCase("all")) {
				sender.sendMessage("ランキングを参照します。");
				getLogger().info(my.getName() + " has called [All] ranking list."); //とりあえずログ出力で本処理をbashに渡す
			} else {
				sender.sendMessage("引数が正しくありません。");
			}
			return true;
		} else if (cmd.getName().equalsIgnoreCase("ping")) {
			int ping = my.getPing();
			sender.sendMessage("ping = " + ping + "ms");
			if (ping <= 30) {
				sender.sendMessage("§a接続は極めて良好です。");
			} else if (ping <= 120) {
				sender.sendMessage("§e接続は良好です。");
			} else if (ping <= 300) {
				sender.sendMessage("§6接続はやや不安定です。\n統合版プレイヤーは常時このレベルになります。");
			} else {
				sender.sendMessage("§c接続はかなり不安定です。\nプレイに支障が生じる可能性が高いです。");
			}
			return true;
		}
		return false;
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		Entity entity = e.getEntity();
		entity.setVelocity(new Vector(0f, 3f, 0f));
	}

	private Skill skill = null;
	@EventHandler
	public void book(PlayerInteractEvent e) {
		if(e.getItem().getType() != Material.WRITTEN_BOOK) return;
		Player player = e.getPlayer();
		BookMeta book = (BookMeta) e.getItem().getItemMeta();
		//player.sendMessage("Title: " + book.getTitle());
		//player.sendMessage("page: " + book.getPageCount());
		//player.sendMessage("Author: " + book.getAuthor());
		//player.sendMessage(book.getPage(1));
		if (book.getTitle().contains("magic")) {
			String magic[] = book.getPage(1).split("\r\n|\n");
			int magicInt[] = new int[magic.length];
			for (int i = 0; i < magic.length; i++) {
				try {
					magicInt[i] = Integer.parseInt(magic[i],2);
				} catch (Exception e1) {
					player.getServer().broadcastMessage(ChatColor.RED + "Format Error");
					try {
						Thread.sleep(20);
					} catch (InterruptedException e2) {
					}
					return;
				}
				if (magicInt[i] >= 16) {
					player.getServer().broadcastMessage(ChatColor.RED + "禁忌の扉を開こうとしたな？(5ケタ以上の値指定は禁止です！");
					return;
				}
			}

			if(skill == null || !skill.isRunning()){
				switch(magicInt[0]){
				case 1:
					skill = new SimpleArrowShot(this,magicInt,player);
					break;
				}
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e1) {
			}
		}
	}

	@EventHandler
	public void stairs_sit(PlayerInteractEvent e) {
		Player player = e.getPlayer();

	}
}