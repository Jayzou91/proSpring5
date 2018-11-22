package com.fish.spring5.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    protected Integer id;

    @Version
    @Column(name = "VERSION")
    private int version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
