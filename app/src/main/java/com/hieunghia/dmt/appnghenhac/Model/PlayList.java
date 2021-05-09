package com.hieunghia.dmt.appnghenhac.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayList {

    @SerializedName("IDPlaylist")
    @Expose
    private String iDPlaylist;
    @SerializedName("Ten")
    @Expose
    private String ten;
    @SerializedName("HinhNen")
    @Expose
    private String hinhNen;
    @SerializedName("HinhIcon")
    @Expose
    private String hinhIcon;

    public String getIDPlaylist() { return iDPlaylist; }

    public void setIDPlaylist(String iDPlaylist) { this.iDPlaylist = iDPlaylist; }

    public String getTen() { return ten; }

    public void setTen(String ten) { this.ten = ten; }

    public String getHinhNen() { return hinhNen; }

    public void setHinhNen(String hinhNen) { this.hinhNen = hinhNen; }

    public String getHinhIcon() { return hinhIcon; }

    public void setHinhIcon(String hinhIcon) { this.hinhIcon = hinhIcon; }

}
