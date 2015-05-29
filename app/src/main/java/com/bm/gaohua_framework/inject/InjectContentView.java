package com.bm.gaohua_framework.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 注解布局测试 
 * @Target 用在哪里 TYPE（类）,FIELD（成员变量）,METHOD(方法) 
 * @Retention 表示：表示需要在什么级别保存该注解信息；这里设置为运行时。
 * @author 高骅
 * @date 2015-3-15 下午6:21:47
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) 
public @interface InjectContentView {
	int value();
}
