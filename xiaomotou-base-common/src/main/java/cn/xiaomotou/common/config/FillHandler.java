package cn.xiaomotou.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class FillHandler implements MetaObjectHandler {
    /* 添加的时候填充策略 */
    @Override
    public void insertFill(MetaObject metaObject) {
        /**
         * MetaObjectHandler的方法
         * default MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
         * 就是用来这是，填充那个字段，填充的类容，metaObject不变
         */
        this.setFieldValByName("insertTime",new Date(),metaObject);
        // 添加的时候这个字段肯定也要有数据，所以加一个添加的时候填充
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    /* 修改的时候填充策略 */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

}

