package com.bin.utils;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.*;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/7/24 10:23 PM
 */
public class BinFactoryBeanImpl implements FactoryBean<String> {

    @Override
    public String getObject() throws Exception {
        return "binfactorybean";
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
