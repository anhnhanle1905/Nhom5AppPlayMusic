package com.hieunghia.dmt.appnghenhac.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayList implements Serializable {

    @SerializedName("IDPlayList")
    @Expose
    private String iDPlaylist;
    @SerializedName("Ten")
    @Expose
    private String ten;
    @SerializedName("HinhNen")
    @Expose
    private String hinhNen;
    @SerializedName("HinhICon")
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
