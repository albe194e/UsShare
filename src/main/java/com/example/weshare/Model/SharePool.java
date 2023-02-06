package com.example.weshare.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SharePool {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int totalSum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
