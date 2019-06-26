package com.bestom.boxmod.common.bean;

public class netcfg {
    private String ip;      //ip
    private String netmask; //子网掩码
    private String getway;  //网关
    private String dns1;
    private String dns2;
    private String dns3;
    private String dns4;

    public netcfg() {
    }

    public netcfg(String ip, String getway) {
        this.ip = ip;
        this.getway = getway;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getGetway() {
        return getway;
    }

    public void setGetway(String getway) {
        this.getway = getway;
    }

    public String getDns1() {
        return dns1;
    }

    public void setDns1(String dns1) {
        this.dns1 = dns1;
    }

    public String getDns2() {
        return dns2;
    }

    public void setDns2(String dns2) {
        this.dns2 = dns2;
    }

    public String getDns3() {
        return dns3;
    }

    public void setDns3(String dns3) {
        this.dns3 = dns3;
    }

    public String getDns4() {
        return dns4;
    }

    public void setDns4(String dns4) {
        this.dns4 = dns4;
    }

    @Override
    public String toString() {
        return "netcfg{" +
                "ip='" + ip + '\'' +
                ", netmask='" + netmask + '\'' +
                ", getway='" + getway + '\'' +
                ", dns1='" + dns1 + '\'' +
                ", dns2='" + dns2 + '\'' +
                ", dns3='" + dns3 + '\'' +
                ", dns4='" + dns4 + '\'' +
                '}';
    }
}
