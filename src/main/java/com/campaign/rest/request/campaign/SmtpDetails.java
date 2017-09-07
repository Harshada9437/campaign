package com.campaign.rest.request.campaign;

public class SmtpDetails {
    private int id;
    private int port;
    private String host;
    private String email;
    private String password;
    private String from;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String  toString() {
        return "SmtpDetails{" +
                "id=" + id +
                ", port=" + port +
                ", host='" + host + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
