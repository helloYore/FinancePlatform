package com.lee.financeplatform.core.controller.admin;


import com.lee.common.exception.BusinessException;
import com.lee.common.result.R;
import com.lee.common.result.ResponseEnum;
import com.lee.financeplatform.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author HelloYore
 * @since 2021-12-23
 */
@Api(tags = "数据字典管理")//Swagger2中的说明，更清晰
@CrossOrigin //因为要做前后端的联调，所以用这个注解做一个跨域处理 加上后前端就可以调了
@RestController //Spring4之后新加入的注解，原来返回json需要@ResponseBody和@Controller配合。即@RestController是@ResponseBody和@Controller的组合注解。
@RequestMapping("/admin/core/dict")
@Slf4j
public class AdminDictController {

    @Resource
    DictService dictService;

    @ApiOperation("excel数据的批量导入")
    @PostMapping("/import")

    //加一个@RequestParam("file") 表示前端对应的组件名称叫file 不写也行
    public R batchImport(
            @ApiParam(value = "Excel数据字典文件", required = true)
            @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            dictService.importData(inputStream);
            return R.ok().message("数据字典数据批量导入成功");
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);

    }
}}

