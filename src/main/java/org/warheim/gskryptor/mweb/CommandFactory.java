/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.warheim.gskryptor.mweb;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andy
 */
public class CommandFactory {
    static Map<String, CommandType> availableCommands = new HashMap<>();
    static {
        registerCommand("G", new Gora());
        registerCommand("D", new Dol());
        registerCommand("L", new Lewo());
        registerCommand("P", new Prawo());
    }
            
    public static void registerCommand(String moniker, CommandType cmd) {
        availableCommands.put(moniker, cmd);
    }
    
    public static Command getCommand(String sCmdLine) throws InstantiationException, IllegalAccessException {
        System.out.println(sCmdLine);
        int charScannerPosition = 0;
        for (int i=0; i<sCmdLine.length(); ++i) {
            if (!Character.isDigit(sCmdLine.charAt(i))) {
                charScannerPosition = i;
                break;
            }
        }
        String sSteps = sCmdLine.substring(0, charScannerPosition);
        Integer iSteps = Integer.parseInt(sSteps);
        String sCmd = sCmdLine.substring(charScannerPosition, sCmdLine.length());
        CommandType x = availableCommands.get(sCmd);
        Command cmd = new Command(x, iSteps);
        if (cmd != null) {
            return cmd;
        }
        return null;
    }
}
