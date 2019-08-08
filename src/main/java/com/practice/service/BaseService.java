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
public interface BaseService<T extends BaseMapper<K>, K> {

    T getMapper();

    default K add(K entity) {
        return getMapper().insert(entity) > 0 ? entity : null;
    }

    default K edit(K entity) {
        return getMapper().updateById(entity) > 0 ? entity : null;
    }

    default int delete(String id) {
        return getMapper().deleteById(id);
    }

    default int deletes(List<String> ids) {
        return getMapper().deleteBatchIds(ids);
    }

    default K findById(String id) {
        return getMapper().selectById(id);
    }

}
