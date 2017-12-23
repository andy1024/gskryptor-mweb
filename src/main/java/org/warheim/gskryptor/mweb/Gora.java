/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.warheim.gskryptor.mweb;

import java.awt.Graphics2D;

/**
 *
 * @author andy
 */
public class Gora extends CommandType {

    @Override
    public void execute(Cursor cursor, StringBuffer sb) {
        cursor.y -= (1 * BLOCK_SIZE);
        sb.append(String.format("%d,%d ",
                cursor.x, cursor.y));
    }

    @Override
    public String getName() {
        return "G";
    }
    
}
