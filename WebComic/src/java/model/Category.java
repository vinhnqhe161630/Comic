/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Category {
    private int ca_id;
    private String ca_name;

    public Category() {
    }

    public Category(int ca_id, String ca_name) {
        this.ca_id = ca_id;
        this.ca_name = ca_name;
    }

    public int getCa_id() {
        return ca_id;
    }

    public void setCa_id(int ca_id) {
        this.ca_id = ca_id;
    }

    public String getCa_name() {
        return ca_name;
    }

    public void setCa_name(String ca_name) {
        this.ca_name = ca_name;
    }
    
}
