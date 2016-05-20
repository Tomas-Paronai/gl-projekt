/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api.dataholders;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;



public class Shift {
    private Timestamp enter;
    private Timestamp exit;
    private int hours;

    public Shift(Timestamp enter, Timestamp exit) {
        this.enter = enter;
        this.exit = exit;
        countHours();
    }
    
    private void countHours(){
        hours = (int) TimeUnit.MILLISECONDS.toHours(exit.getTime() - enter.getTime());
        
    }
    
    public Timestamp getEnter() {
        return enter;
    }

    public void setEnter(Timestamp enter) {
        this.enter = enter;
    }

    public Timestamp getExit() {
        return exit;
    }

    public void setExit(Timestamp exit) {
        this.exit = exit;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    
    
}
