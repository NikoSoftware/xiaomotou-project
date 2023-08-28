package cn.xiaomotou.user.entity;

import cn.xiaomotou.user.enumtype.GradeEnum;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author niko
 * @since 2023-03-27
 */

@Data
public class Poetry {


    private Integer id;

    private Integer authorId;

    private GradeEnum type;

    private String title;

    private String content;

    private String yunlvRule;

    private String author;

    private String dynasty;

    private Integer deleted;


}
