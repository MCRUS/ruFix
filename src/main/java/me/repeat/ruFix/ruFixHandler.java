package me.repeat.ruFix;

import java.io.UnsupportedEncodingException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class ruFixHandler {
    public static void handlerChange() throws Exception {

        Logger globalLogger = ruFix.plugin.getLogger();
        Handler[] handlers = globalLogger.getParent().getHandlers();
        if (handlers.length == 0)
            throw new Exception("Can't inject to console or log handler. No handlers found!");
        for (Handler handler : handlers) {
            if (ruFix.ruFixDebug) {
                globalLogger.info("[ruFixDebug]:" + handler.toString() + " - handler start: " + handler.getEncoding());
            }
            //globalLogger.removeHandler(handler);
            if (handler instanceof ConsoleHandler) {
                try {
                    //console
                    if (ruFix.parseConsole) {
                        handler.setEncoding(ruFix.ruFixConsole);
                        globalLogger.info("ruFixConsole: " + ruFix.ruFixConsole);
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if (handler instanceof FileHandler) {
                try {
                    if (ruFix.parseLogFile) {
                        handler.setEncoding(ruFix.ruFixLogFile);
                        globalLogger.info("ruFixLogFile: " + ruFix.ruFixLogFile);
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (ruFix.ruFixDebug) {
                globalLogger.info("[ruFixDebug]:" + handler.toString() + " - handler end: " + handler.getEncoding());
            }
        }
    }
}
