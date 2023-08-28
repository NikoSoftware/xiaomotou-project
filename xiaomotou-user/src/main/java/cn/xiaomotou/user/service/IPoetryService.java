package cn.xiaomotou.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.xiaomotou.user.entity.Poetry;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author niko
 * @since 2023-03-25
 */
public interface IPoetryService extends IService<Poetry> {


    IPage<Poetry> queryPoetry();

    Boolean deleteInfoByID(Integer id);

}
