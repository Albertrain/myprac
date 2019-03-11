package com.train.amm.bean;

public class Message {
    private String body;
    private String date;
    private String address;
    private String type;

    public Message(){}

    public Message(String body, String date, String address, String type) {
        this.body = body;
        this.date = date;
        this.address = address;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public String toString() {
        return "Message{" +
                "body='" + body + '\'' +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
