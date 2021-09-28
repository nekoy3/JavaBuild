package neko.aziplugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class AziServer extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("Hello, sushi!");
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    
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

    private Player getPlayer(String name) {
    	for ( Player player : Bukkit.getOnlinePlayers() ) {
    		if ( player.getName().equals(name) ) {
    			return player;
    		}
    	}
    	return null;
    }
}