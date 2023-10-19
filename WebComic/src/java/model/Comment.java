/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Comment {
    private int commentid;
    private String content,create_at;
    private int s_id;
    private Account a;
    

    public Comment() {
    }

    public Comment(int commentid, String content, String create_at, int s_id, Account a) {
        this.commentid = commentid;
        this.content = content;
        this.create_at = create_at;
        this.s_id = s_id;
        this.a = a;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public Account getA() {
        return a;
    }

    public void setA(Account a) {
        this.a = a;
    }

  
    
}
