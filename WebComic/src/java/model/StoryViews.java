/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class StoryViews {
    private int sv_id,view_count;
   private String view_date;

    public StoryViews(int sv_id, int view_count, String view_date) {
        this.sv_id = sv_id;
        
        this.view_count = view_count;
        this.view_date = view_date;
    }

    public StoryViews() {
    }

    public int getSv_id() {
        return sv_id;
    }

    public void setSv_id(int sv_id) {
        this.sv_id = sv_id;
    }

    
    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getView_date() {
        return view_date;
    }

    public void setView_date(String view_date) {
        this.view_date = view_date;
    }
   
   
   
}