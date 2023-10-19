/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Chapter {
    private int ch_id,ch_number;
    private String upchat_at;
    private int s_id;

    public Chapter() {
    }

    public Chapter(int ch_id, int ch_number, String upchat_at, int s_id) {
        this.ch_id = ch_id;
        this.ch_number = ch_number;
        this.upchat_at = upchat_at;
        this.s_id = s_id;
    }

    public int getCh_id() {
        return ch_id;
    }

    public void setCh_id(int ch_id) {
        this.ch_id = ch_id;
    }

    public int getCh_number() {
        return ch_number;
    }

    public void setCh_number(int ch_number) {
        this.ch_number = ch_number;
    }

    public String getUpchat_at() {
        return upchat_at;
    }

    public void setUpchat_at(String upchat_at) {
        this.upchat_at = upchat_at;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }
   
    
}
