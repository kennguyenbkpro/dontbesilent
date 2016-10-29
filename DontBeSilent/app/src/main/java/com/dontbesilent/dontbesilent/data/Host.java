package com.dontbesilent.dontbesilent.data;

import java.util.List;
import java.util.Random;

/**
 * Created by XUAN QUANG on 10/29/2016.
 */
public class Host {
    public String id;
    public String user;
    public String name;
    public Boolean isVerified;
    public String avatar = "http://www.nguoiviettre.org.vn/uploads/News/pic/small_nvn_1363749099.jpg";
    public String cover = "http://www.thiennguyen.org/wp-content/uploads/2014/12/45674_139609939413758_4710068_n.jpg";
    public String desception;
    public List<String> createCampaigns;
    public List<String> followCampaigns;
    public int numberOfCampaign = new Random().nextInt(20);

    public Host() {
    }

    public Host(String user, String name, Boolean isVerified, String desception) {
        this.user = user;
        this.name = name;
        this.isVerified = isVerified;
        this.desception = desception;
    }
}
