package com.fish.spring5.dao.impl;

import com.fish.spring5.dao.InstrumentDao;
import com.fish.spring5.entity.Instrument;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
@Repository("instrumentDao")
public class InstrumentDaoImpl implements InstrumentDao {

    @Override
    public void save(Instrument instrument) {

    }
}
