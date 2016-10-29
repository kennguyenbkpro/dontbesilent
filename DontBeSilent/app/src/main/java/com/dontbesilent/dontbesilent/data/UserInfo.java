package com.dontbesilent.dontbesilent.data;

/**
 * Created by khanhnq2 on 30/10/2016.
 */

public class UserInfo {
    private String coverUrl;
    private String avtUrl;
    private String name;
    private String description;
    private boolean isVerified;

    public UserInfo(String coverUrl, String avtUrl, String name, String description, boolean isVerified) {
        this.coverUrl = coverUrl;
        this.avtUrl = avtUrl;
        this.name = name;
        this.description = description;
        this.isVerified = isVerified;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getAvtUrl() {
        return avtUrl;
    }

    public void setAvtUrl(String avtUrl) {
        this.avtUrl = avtUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
