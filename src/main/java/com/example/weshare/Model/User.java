package com.example.weshare.Model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private String username, password;

    private int contribution, toPay;




    @OneToOne
    @JoinColumn(name = "pool_id")
    private SharePool sharePool;


    public User(String username, String password, SharePool sharePool) {
        this.username = username;
        this.password = password;
        this.sharePool = sharePool;
    }
    public User (String username, String password){
        this.username = username;
        this.password = password;
    }

    public SharePool getSharePool() {
        return sharePool;
    }

    public void setSharePool(SharePool sharePool) {
        this.sharePool = sharePool;
    }

    public int getContribution() {
        return contribution;
    }

    public void setContribution(int contribution) {
        this.contribution = contribution;
    }

    public int getToPay() {
        return toPay;
    }

    public void setToPay(int toPay) {
        this.toPay = toPay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User() {}

}
