/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.warheim.gskryptor.mweb;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author andy
 */
public class Renderer {
    private int width;
    private int height;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public String createImage(List<Command> cmdList) {
        StringBuffer sb = new StringBuffer();
//        sb.append(String.format("<svg width=\"%d\" height=\"%d\">", width, height));
        sb.append("<polyline points=\"");
//        g2d.setBackground(Color.WHITE);
//        g2d.setColor(Color.BLACK);
//        BasicStroke bs = new BasicStroke(2);
//        g2d.setStroke(bs);
        Cursor cursor = new Cursor();
        cursor.x = 200;
        cursor.y = 100;
        sb.append(String.format("%d,%d ",
                cursor.x, cursor.y));
        for (Command cmd: cmdList) {
            cmd.execute(cursor, sb);
        }
        sb.append("\" style=\"fill:none;stroke:black;stroke-width:3\" />");
//        sb.append("</svg>");
        return sb.toString();
    }
    
    public List<Command> readCommand(String input) throws Exception {
        List<Command> result = new ArrayList<>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("=")) {
                //definition
                String[] arr = line.split("=");
                final String name = arr[0];
                String def = arr[1];
                final String[] defList = def.split(",");
                CommandFactory.registerCommand(name, new CommandType() {
                    @Override
                    public void execute(Cursor cursor, StringBuffer sb) {
                        for (String subCmd: defList) {
                            try {
                                Command cmd = CommandFactory.getCommand(subCmd);
                                cmd.execute(cursor, sb);
                            } catch (InstantiationException|IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public String getName() {
                        return name;
                    }
                    
                });
            } else if (line.startsWith("#")) {
                //comment
                continue;
            } else {
                //command execution
                Command cmd = CommandFactory.getCommand(line);
                result.add(cmd);
            }
        }
        return result;
    }
    
    public void printout(List<Command> cmdList) {
        for (Command cmd: cmdList) {
            System.out.println(cmd);
        }
    }
    
//    public void run() throws Exception {
//        List<Command> cmdList = readCommand("/home/andy/src/skrypty-graficzne/dino2.dat");
//        //printout(cmdList);
//        createImage(cmdList);
//    }
    
//    public static void main(String... args) throws Exception {
//        //new Main().run();
//    }
}
