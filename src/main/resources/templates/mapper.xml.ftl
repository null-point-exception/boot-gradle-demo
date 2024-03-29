<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

    <#if enableCache>
        <!-- 开启二级缓存 -->
        <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

    </#if>
    <#if baseResultMap>
        <!-- 通用查询映射结果 -->
        <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
            <#list table.fields as field>
                <#if field.keyFlag><#--生成主键排在第一位-->
                    <id column="${field.name}" property="${field.propertyName}" />
                </#if>
            </#list>
            <#list table.commonFields as field><#--生成公共字段 -->
                <result column="${field.name}" property="${field.propertyName}" />
            </#list>
            <#list table.fields as field>
                <#if !field.keyFlag><#--生成普通字段 -->
                    <result column="${field.name}" property="${field.propertyName}" />
                </#if>
            </#list>
        </resultMap>

    </#if>
    <#if baseColumnList>
        <!-- 通用查询结果列 -->
        <sql id="Base_Column_List">
            <#list table.commonFields as field>
                ${field.name},
            </#list>
            ${table.fieldNames}
        </sql>

    </#if>
    <#if cfg.selectAll == true || cfg.findPage == true>
        <select id="select${entity}s" resultType="${package.Entity}.${entity}">
            SELECT <#list table.fields as field>${field.name}<#if ((field_index + 1) < table.fields?size)>, </#if></#list> FROM ${table.name}
            WHERE 1=1
            <if test="query != null">
                <#list table.fields as field>
                    <#if !field.keyFlag><#--生成普通字段 -->
                <if test="<#noparse>query.</#noparse>${field.propertyName} != null and <#noparse>query.</#noparse>${field.propertyName} !=''">
                    AND ${field.name} = <#noparse>#{query.</#noparse>${field.propertyName}<#noparse>}</#noparse>
                </if>
                    </#if>
                </#list>
                <include refid="com.practice.dao.CommonMapper.sortSql"></include>
            </if>
        </select>
    </#if>
</mapper>
