package com.vo;

/**
 * NORMAL_AREA表实体类
 * Created by andychen on 2015/3/24.<br>
 * Version 1.0-SNAPSHOT<br>
 */
public class TbBaseNormalArea {
    private long id;
    private String areaName;

    public TbBaseNormalArea() {
    }

    public TbBaseNormalArea(long id, String areaName) {
        this.id = id;
        this.areaName = areaName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "TbBaseNormalArea{" +
                "id=" + id +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
