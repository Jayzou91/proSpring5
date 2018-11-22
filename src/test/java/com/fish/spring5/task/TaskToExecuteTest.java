package com.fish.spring5.task;

import com.fish.spring5.config.AppConfig;
import com.fish.spring5.dao.SingerDaoTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
public class TaskToExecuteTest {
    private static final Logger logger = LoggerFactory.getLogger(SingerDaoTest.class);

    private GenericApplicationContext context;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    public void test() {
        TaskToExecute taskToExecute = context.getBean(TaskToExecute.class);
        taskToExecute.executeTask();
        context.close();
    }

}