package com.fish.spring5.vo;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
public class RoleVO {

    private int id;
    private String name;
    private String users;
    private String description;

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

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
