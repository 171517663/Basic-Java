package com.test.annotation.importAnno;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjConfigrationAnno {
    @Override
    public String toString() {
        return "classname:" + this.getClass().toString();
    }
}
