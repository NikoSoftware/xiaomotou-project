package cn.xiaomotou.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.xiaomotou.common.result.R;
import cn.xiaomotou.user.entity.Poetry;
import cn.xiaomotou.user.service.IPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author niko
 * @since 2023-03-25
 */
@RestController
@RequestMapping("/poetry")
public class PoetryController {

    @Autowired
    IPoetryService iPoetryService;


    @GetMapping("/page")
    public R<IPage<Poetry>> queryPoetry(){

        return R.data(iPoetryService.queryPoetry());

    }

    @GetMapping("/delete")
    public R queryPoetry(Integer id){

        return R.data(iPoetryService.deleteInfoByID(id));

    }

    @GetMapping("/select")
    public R select(Integer id){

        return R.data(iPoetryService.getBaseMapper().selectById(id));

    }


}

