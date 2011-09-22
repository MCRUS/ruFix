package me.repeat.ruFix;

import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListener;

public class ruFixServerListener extends ServerListener{

	public static ruFix plugin; public ruFixServerListener(ruFix instance) {
        plugin = instance;
    }
	
    public void onServerCommand(ServerCommandEvent event) {

    	byte[] message = event.getCommand().getBytes();
		String text = "";
        for (int n = 0; n < message.length; n++) {

            int c = (int)message[n] & 0xFF;
            int c1 = 0;
            if (c > 128 && (int)(n+1) <= (int)(message.length-1)) {
             	c1 = (int)message[n+1] & 0xFF;
//				System.out.print(c);
             	if (c == 195 && c1 == 177 ) { // fix '�'
             		text += (char)((int)1105);
             		n++;
             	} else if (c == 194 && c1 >= 128 && c1 <= 175) { //�-��-�
//	            	System.out.print(c1+"!");
             		c = c1+1040-128; // 1040 - ������ �������� ������, 128 - �
//					System.out.print(c+"@");
             		text += (char)(c); 
             		n++;
             	} else if (c == 195 && c1 >= 160 && c1 <= 175) { // �-�
//					System.out.print(c1+"!");
             		c = c1+1040-128+16; // 1040 - ����������� �������� ������, 160 - �
//            		System.out.print(c+"@");
             		text += (char)(c); 
             		n++;
                } else {
             		text += (char)c;
             	}
            } else {
            	text += (char)c;
            }
        }
        if (System.getProperty("ruFixDebug") != null)
        	System.out.print("[ruFixDebug]:" + event.getCommand() + ":");
		event.setCommand(text);
	}
}