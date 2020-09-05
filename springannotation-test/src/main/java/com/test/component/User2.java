package com.test.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//单例
@Scope("singleton")
public class User2 {
}
