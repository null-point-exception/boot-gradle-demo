package com.practice.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基础业务层封装.
 * <p>
 * 事务传播属性为嵌套：如果没有，就新建一个事务；如果有，就在当前事务中嵌套其他事务
 * solation.DEFAULT，事务隔离级别使用数据库默认
 *
 * @param <T> 继承BaseMapper的dao层映射XxxMapper
 * @param <K> 对应数据库表的entity
 * @author kexin.ding
 */
@Transactional(propagation = Propagation.NESTED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
public class BaseService<T extends BaseMapper<K>, K> {

    @Resource
    protected T mapper;

    public K add(K entity) {
        return mapper.insert(entity) > 0 ? entity : null;
    }

    public K edit(K entity) {
        return mapper.updateById(entity) > 0 ? entity : null;
    }

    public int delete(String id) {
        return mapper.deleteById(id);
    }

    public int deletes(List<String> ids) {
        return mapper.deleteBatchIds(ids);
    }

    public K findById(String id) {
        return mapper.selectById(id);
    }

}
