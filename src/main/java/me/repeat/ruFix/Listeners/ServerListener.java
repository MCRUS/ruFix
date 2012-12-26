package me.repeat.ruFix.Listeners;

import me.repeat.ruFix.ruFix;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ServerListener implements Listener{

	public static ruFix plugin; public ServerListener(ruFix instance) {
        plugin = instance;
    }
	@EventHandler(priority = EventPriority.LOW)
    public void onServerCommand(ServerCommandEvent event) {
		if (ruFix.parseConsole) {
				
	    	byte[] message = event.getCommand().getBytes();
	    	String text = "";
	          for (int n = 0; n < message.length; n++) {
	
	            int c = (int)message[n] & 0xFF;
	            int c1 = 0;
	            if (c > 128 && (int)(n+1) <= (int)(message.length-1)) {
	             	c1 = (int)message[n+1] & 0xFF;
	             	if (c == 195 && c1 == 177 ) { // fix '�'
	             		text += (char)((int)1105);
	             		n++;
	             	} else if (c == 194 && c1 >= 128 && c1 <= 175) { //�-��-�
	             		c = c1+1040-128;
	             		text += (char)(c);
	             		n++;
	             	} else if (c == 195 && c1 >= 160 && c1 <= 175) { // �-�
	             		c = c1+1040-128+16;
	             		text += (char)(c);
	             		n++;
	                } else {
	             		text += (char)c;
	             	}
	            } else {
	            	text += (char)c;
	            }
	        }
	        if (ruFix.ruFixDebug && event.getCommand().length() > 0 )
	        	System.out.print("[ruFixDebug]: " + event.getCommand() + ":" );
			event.setCommand(text);
	
			// correction when sending second of line at chat with option '-nojline'
			// this correction have effect when press Enter without any command
			if (event.getCommand().length() == 0)
				event.setCommand("/");
	    }
	}
}
