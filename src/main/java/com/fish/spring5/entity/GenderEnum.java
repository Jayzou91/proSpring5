package com.fish.spring5.entity;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
public enum GenderEnum {
    Unknown(0), Male(1), Female(2);

    private int value;

    private GenderEnum(int value) {
        this.value = value;
    }

}
