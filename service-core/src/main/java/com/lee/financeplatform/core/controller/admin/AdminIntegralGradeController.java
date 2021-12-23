package com.lee.financeplatform.core.controller.admin;


import com.lee.financeplatform.core.pojo.entity.IntegralGrade;
import com.lee.financeplatform.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 管理员页面前端控制器
 * </p>
 *
 * @author HelloYore
 * @since 2021-12-23
 */
@Api(tags = "积分等级管理") //Swagger2中的说明，更清晰
@CrossOrigin //因为要做前后端的联调，所以用这个注解做一个跨域处理
@RestController
@RequestMapping("/admin/core/integralGrade") //因为在com.lee.f**.core下层所以用的是core 而且是admin下
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation(value = "积分等级列表")
    @GetMapping("/list")
    //积分等级列表接口
    public List<IntegralGrade> listALl(){
        return integralGradeService.list();
    }
    //逻辑删除列表接口
    @ApiOperation(value = "根据id删除积分等级", notes = "逻辑删除记录")
    @DeleteMapping("/remove/{id}")
    public boolean removeById(
            @ApiParam(value = "数据id", example = "100", required = true)
            @PathVariable Long id){//通过路径来传一个参数
        return integralGradeService.removeById(id);
    }
}

