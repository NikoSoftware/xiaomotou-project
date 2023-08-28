package cn.xiaomotou.user.service.impl;

import cn.xiaomotou.user.service.IPoetryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xiaomotou.user.entity.Poetry;
import cn.xiaomotou.user.mapper.PoetryMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author niko
 * @since 2023-03-25
 */
@Service
public class PoetryServiceImpl extends ServiceImpl<PoetryMapper, Poetry> implements IPoetryService {


    @Override
    public IPage<Poetry> queryPoetry(){
        Page<Poetry> page = new Page<>();
        page.setCurrent(1);
        Page<Poetry> pages = this.page(page, new QueryWrapper<Poetry>().lambda().orderBy(true,true,Poetry::getId));


        return pages;

    }

    @Override
    public Boolean deleteInfoByID(Integer id) {
        baseMapper.deleteById(id);
       return true;
    }


}
