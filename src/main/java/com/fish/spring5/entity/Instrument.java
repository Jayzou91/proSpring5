package com.fish.spring5.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
@Entity
@Table(name = "instrument")
public class Instrument implements Serializable {
    private String instrumentId;

    private Set<Singer> singers = new HashSet<>();

    @Id
    @Column(name = "INSTRUMENT_ID")
    public String getInstrumentId() {
        return this.instrumentId;
    }


    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }


    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
    public Set<Singer> getSingers() {
        return singers;
    }

    public void setSingers(Set<Singer> singers) {
        this.singers = singers;
    }

    @Override
    public String toString() {
        return "Instrument :" + getInstrumentId();
    }
}
