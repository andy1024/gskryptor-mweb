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
public abstract class CommandType {
    
    public static final int BLOCK_SIZE = 10;
   
    public abstract void execute(Cursor cursor, StringBuffer sb);
    
    public abstract String getName();

    @Override
    public String toString() {
        return "CommandType{" + getName() + "}";
    }
}
