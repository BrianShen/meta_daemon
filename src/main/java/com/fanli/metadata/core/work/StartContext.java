package com.fanli.metadata.core.work;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wei.shen on 2015/10/27.
 */
public class StartContext {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }
}
