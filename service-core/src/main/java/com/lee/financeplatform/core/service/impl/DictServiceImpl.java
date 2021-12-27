package com.lee.financeplatform.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.lee.financeplatform.core.listener.ExcelDictDTOListener;
import com.lee.financeplatform.core.pojo.dto.ExcelDictDTO;
import com.lee.financeplatform.core.pojo.entity.Dict;
import com.lee.financeplatform.core.mapper.DictMapper;
import com.lee.financeplatform.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author HelloYore
 * @since 2021-12-23
 */
@Service
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
//    //当前注入的mapper就是这个service下的mapper，可以省略，直接写basemapper就行
//    @Resource
//    private DictMapper dictMapper;

    //加一个事务，所有异常出现的时候都做回滚，因为就写了一个exception.class所以大括号加不加都行，需要写别的时候要加上
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void importData(InputStream inputStream) {
        //监听器从inputstream中读出的数据自动封装到ExcelDictDTO这个对象里面，具体封装过程在监听器内部实现。
        EasyExcel.read(inputStream, ExcelDictDTO.class, new ExcelDictDTOListener(baseMapper)).sheet().doRead();
        log.info("Excel导入成功");
    }
}
