package com.practice.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 自定义公共字段处理器
 *
 * @author kexin.ding
 */
public class SolarMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SolarMetaObjectHandler.class);
    private static final String CREATE_TIME = "createDate";
    private static final String UPDATE_TIME = "updateDate";

    /**
     * 插入操作自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ....");
        Date now = new Date();
        Object createTimeObj = this.getFieldValByName(CREATE_TIME, metaObject);
        if (null == createTimeObj) {
            this.setFieldValByName(CREATE_TIME, now, metaObject);
        }
        Object updateTimeObj = this.getFieldValByName(UPDATE_TIME, metaObject);
        if (null == updateTimeObj) {
            this.preUpdate(metaObject, now);
        }
    }

    /**
     * 更新操作自动填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start update fill ....");
        Date now = new Date();
        Object updateTimeObj = this.getFieldValByName(UPDATE_TIME, metaObject);
        if (null == updateTimeObj) {
            this.preUpdate(metaObject, now);
        }
    }

    /**
     * 封装更新的统一处理 * * @param metaObject MetaObject
     * @param now Date
     */
    private void preUpdate(MetaObject metaObject, Date now) {
        // 强制维护时间
        this.setFieldValByName(UPDATE_TIME, now, metaObject);
    }
}