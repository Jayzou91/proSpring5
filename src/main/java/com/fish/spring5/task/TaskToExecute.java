package com.fish.spring5.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
//@Component
public class TaskToExecute {
    private final Logger logger =  LoggerFactory.getLogger(TaskToExecute.class);

        @Autowired
        private TaskExecutor taskExecutor;

        public void executeTask() {
            for (int i = 0; i < 10; ++i) {
                taskExecutor.execute(() ->
                        logger.info("Hello from thread: " +
                                Thread.currentThread().getName()));
            }
    }
}
