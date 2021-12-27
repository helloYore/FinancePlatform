package com.lee.financeplatform.core.controller.admin;


import com.lee.common.exception.Assert;
import com.lee.common.exception.BusinessException;
import com.lee.common.result.R;
import com.lee.common.result.ResponseEnum;
import com.lee.financeplatform.core.pojo.entity.IntegralGrade;
import com.lee.financeplatform.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@CrossOrigin //因为要做前后端的联调，所以用这个注解做一个跨域处理 加上后前端就可以调了
@RestController
@RequestMapping("/admin/core/integralGrade") //因为在com.lee.f**.core下层所以用的是core 而且是admin下
@Slf4j
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation(value = "积分等级列表")
    @GetMapping("/list")
    //积分等级列表接口
//    public List<IntegralGrade> listALl(){
    //这里改用R类型，以后的都用R类型，统一返回的结果
    public R listALl() {

        log.info("hi, log info");
        log.warn("hi, log warn");
        log.error("hi, log error");

        List<IntegralGrade> list = integralGradeService.list();
        //链式编程
        return R.ok().data("list", list).message("获取列表成功");
    }

    //逻辑删除列表接口
    @ApiOperation(value = "根据id删除积分等级", notes = "逻辑删除记录")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value = "数据id", example = "100", required = true)
            @PathVariable Long id) {//通过路径来传一个参数
        boolean result = integralGradeService.removeById(id);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade) {

//        if(integralGrade.getBorrowAmount() == null){
//            //抛出异常到UnifiedExceptionhandler进行捕获
//            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
//        }
        //用断言的方式解决异常处理
        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);

        boolean result = integralGradeService.save(integralGrade);
        if (result) {
            return R.ok().message("新增积分等级成功");
        } else {
            return R.error().message("新增积分等级失败");
        }
    }

    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Long id) {
        IntegralGrade getById = integralGradeService.getById(id);
        if (getById != null) {
            return R.ok().message("数据获取成功").data("record", getById);
        } else {
            return R.error().message("数据获取失败");
        }
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade) {
        boolean updateById = integralGradeService.updateById(integralGrade);
        if (updateById) {
            return R.ok().message("数据更新成功");
        } else {
            return R.error().message("数据更新失败");
        }
    }
}

