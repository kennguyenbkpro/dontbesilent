package com.dontbesilent.dontbesilent.data;

import java.util.List;

/**
 * Created by XUAN QUANG on 10/29/2016.
 */
public class Host {
    public String id;
    public String user;
    public String name;
    public Boolean isVerified;
    public String avatar;
    public String desception;
    public List<String> createCampaigns;
    public List<String> followCampaigns;

    public Host(String user, String name, Boolean isVerified, String desception) {
        this.user = user;
        this.name = name;
        this.isVerified = isVerified;
        this.desception = desception;
    }
}
