/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Country {
    private int co_id;
    private String country;

    public Country() {
    }

    public Country(int co_id, String country) {
        this.co_id = co_id;
        this.country = country;
    }

    public int getCo_id() {
        return co_id;
    }

    public void setCo_id(int co_id) {
        this.co_id = co_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
