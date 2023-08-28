package cn.xiaomotou.user.enumtype;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum GradeEnum implements IEnum<Integer> {

    KINDER(0,"幼儿"), PRIMARY(1, "小学"),  SECONDORY(2, "中学"),  HIGH(3, "高中");

    GradeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

//    @EnumValue//标记数据库存的值是code
    @JsonValue
    private final int code;
    //。。。


    private String descp;

    @Override
    public Integer getValue() {
        return code;
    }
}