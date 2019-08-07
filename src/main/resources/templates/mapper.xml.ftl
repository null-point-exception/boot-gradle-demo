<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackage}.${model + mapperSuffix}">

    <#if selectAll == true || findPage == true>
        <select id="select${model}s" resultType="${model + entitySuffix}">
            SELECT * FROM ${tableName} WHERE 1=1
            <if test="query != null">
                <include refid="com.practice.dao.CommonMapper.sortSql"></include>
            </if>
        </select>
    </#if>

</mapper>