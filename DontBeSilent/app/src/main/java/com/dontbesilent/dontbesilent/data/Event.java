package com.dontbesilent.dontbesilent.data;

import java.util.List;

/**
 * Created by XUAN QUANG on 10/29/2016.
 */
public class Event {
    public String id;
    public String name;
    public String address;
    public Double goalMoney;
    public Double incomeMoney;
    public String image;
    public List<String> campaigns;

    public Event(String name, String address, Double goalMoney, Double incomeMoney) {
        this.name = name;
        this.address = address;
        this.goalMoney = goalMoney;
        this.incomeMoney = incomeMoney;
    }
}
