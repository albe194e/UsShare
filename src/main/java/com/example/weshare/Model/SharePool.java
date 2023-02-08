package com.example.weshare.Model;

import jakarta.persistence.*;

@Entity
public class SharePool {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int poolId;


    @OneToOne(mappedBy = "sharePool")
    private User user;

    private int totalSum;

    //getters and setters


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPoolId() {
        return poolId;
    }

    public void setPoolId(int id) {
        this.poolId = id;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }
    public void addToTotalSum(int n){
        totalSum += n;
    }
}
