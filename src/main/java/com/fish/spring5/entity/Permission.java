package com.fish.spring5.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
@Entity
@Table(name = "permission")
@NamedQueries({
        @NamedQuery(name = Permission.FIND_BY_ROLE_IDS,
                query = "select distinct p from Permission p " +
                        "left join fetch p.roles r " +
                        "where r.id in (:roleIds)")
})
public class Permission {

    public static final String FIND_BY_ROLE_IDS = "Permission.findByRoleIds";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer pid;

    @Column(nullable = false, unique = true)
    private String url;

    @Version
    @Column
    private int version;

    @Column(nullable = false)
    private boolean disabled;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
