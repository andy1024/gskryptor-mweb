/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.warheim.gskryptor.mweb;

/**
 *
 * @author andy
 */
public class Command {
    private CommandType type;
    private int steps;

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Command(CommandType type, int steps) {
        this.type = type;
        this.steps = steps;
    }
    
    public void execute(Cursor cursor, StringBuffer sb) {
        for (int i = 0; i < this.steps; ++i) {
            this.type.execute(cursor, sb);
        }
    }

    @Override
    public String toString() {
        return "Command{" + "type=" + type + ", steps=" + steps + '}';
    }
    
    
}
