package com.fish.spring5;

import com.fish.spring5.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
public class BaseTest {

    protected GenericApplicationContext context;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @After
    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }

}
