/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Story {
    private int s_id,view;
    private String s_name,image,create_at;
    private int country;
    private int status;
    private Author a;

    public Story() {
    }

    public Story(int s_id, int view, String s_name, String image, String create_at, int country, int status, Author a) {
        this.s_id = s_id;
        this.view = view;
        this.s_name = s_name;
        this.image = image;
        this.create_at = create_at;
        this.country = country;
        this.status = status;
        this.a = a;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Author getA() {
        return a;
    }

    public void setA(Author a) {
        this.a = a;
    }

   
   }