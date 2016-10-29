package com.dontbesilent.dontbesilent.data;

/**
 * Created by CuTi on 10/29/2016.
 */

public class Campaign {
    public String id;
    public String name;
    public String hostId;
    public String eventId;
    public String image;
    public String desception;
    public String startTime;
    public String endTime;
    public Double goalMoney;
    public Double incomeMoney;
    public Long numFollower;
    public String mLatitude;
    public String mLongtitude;

    public Campaign() {
    }

    public Campaign(String name, String hostId, String eventId, String desception, String startTime, String endTime, Double goalMoney, Double incomeMoney, Long numFollower) {
        this.name = name;
        this.hostId = hostId;
        this.eventId = eventId;
        this.desception = desception;
        this.startTime = startTime;
        this.endTime = endTime;
        this.goalMoney = goalMoney;
        this.incomeMoney = incomeMoney;
        this.numFollower = numFollower;
    }
}
