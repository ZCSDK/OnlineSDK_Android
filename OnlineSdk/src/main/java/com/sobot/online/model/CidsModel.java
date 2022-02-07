package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

public class CidsModel implements Serializable {

    private int size;
    private List<String> cids;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getCids() {
        return cids;
    }

    public void setCids(List<String> cids) {
        this.cids = cids;
    }
}
