package com.fish.spring5.dto;

import com.fish.spring5.entity.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
public class PermissionDTO {

    private Integer id;
    private Integer pid;
    private String name;
    private String url;
    private boolean disabled;

    private List<PermissionDTO> children = new ArrayList();

    public PermissionDTO() {
    }

    public PermissionDTO(Permission permission) {
        this.id = permission.getId();
        this.name = permission.getName();
        this.pid = permission.getPid();
        this.url = permission.getUrl();
        this.disabled = permission.isDisabled();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<PermissionDTO> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDTO> children) {
        this.children = children;
    }
}
