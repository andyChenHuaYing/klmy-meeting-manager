package com.vo.buz.selection;

/**
 * LevelVO 实体类
 * Created by andychen on 2015/3/18.<br>
 * Version 1.0-SNAPSHOT<br>
 */
public class LevelVO {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LevelVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
