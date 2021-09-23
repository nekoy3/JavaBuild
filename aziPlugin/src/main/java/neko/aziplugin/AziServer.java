package neko.aziplugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AziServer extends JavaPlugin {
    //↓ onEnableはロードされた時に実行されるメソッド
    @Override
    public void onEnable() {
        // ↓ サーバー上にログを残す
        getLogger().info("Hello, sushi!");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	sender.sendMessage(cmd + " --> test command from " + sender);
        return true;
    }
}
