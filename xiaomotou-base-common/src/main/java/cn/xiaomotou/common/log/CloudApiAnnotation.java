package cn.xiaomotou.common.log;
 
import java.lang.annotation.*;

/**
 * @Program: aop
 * @ClassName CloudApiAnnotation
 * @Description: 自定义系统操作注解
 * @Author: liutao
 * @DateTime: 2022/10/5 2:32
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CloudApiAnnotation {
    String operMoudle() default "";//操作模块
    String operMethod() default "";//操作方法
    String operDes() default "";//操作描述
}
