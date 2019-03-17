package com.train.amm.domain;

public class SystemMessage {
    private String address;
    private String type;
    private String body;
    private long date;

    public SystemMessage(String address, String type, String body, long date) {
        this.address = address;
        this.type = type;
        this.body = body;
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SystemMessage{" +
                "address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
