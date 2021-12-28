package com.lee.financeplatform.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.financeplatform.core.listener.ExcelDictDTOListener;
import com.lee.financeplatform.core.pojo.dto.ExcelDictDTO;
import com.lee.financeplatform.core.pojo.entity.Dict;
import com.lee.financeplatform.core.mapper.DictMapper;
import com.lee.financeplatform.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    //注入redisTemplate 后面list方法会使用
    @Resource
    private RedisTemplate redisTemplate;

    //加一个事务，所有异常出现的时候都做回滚，因为就写了一个exception.class所以大括号加不加都行，需要写别的时候要加上
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void importData(InputStream inputStream) {
        //监听器从inputstream中读出的数据自动封装到ExcelDictDTO这个对象里面，具体封装过程在监听器内部实现。
        EasyExcel.read(inputStream, ExcelDictDTO.class, new ExcelDictDTOListener(baseMapper)).sheet().doRead();
        log.info("Excel导入成功");
    }

    @Override
    public List<ExcelDictDTO> listDictData() {

        List<Dict> dictList = baseMapper.selectList(null);
        //创建excel对应的DTO列表，将Dict列表转换成ExcelDictDTO列表
        ArrayList<ExcelDictDTO> excelDictDTOList = new ArrayList<>(dictList.size());
        dictList.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict, excelDictDTO);
            excelDictDTOList.add(excelDictDTO);
        });
        return excelDictDTOList;

    }

    @Override
    public List<Dict> listByParentId(Long parentId) {
        try {
            //1.首先查询redis中是否存在数据列表，如果存在，则从redis中直接返回数据列表
            List<Dict> dictList = (List<Dict>) redisTemplate.opsForValue().get("financeplatform:core:dictList" + parentId);
            if (dictList != null) {
                //如果存在则从redis中直接返回数据列表
                log.info("从redis中获取数据列表");
                return dictList;
            }
        } catch (Exception e) {
            //redis出问题不代表就不行了，还可以用数据库去查，所以不能redis一有问题就停下 把错误内容以字符串的形式写到日志当中 拿到e对象的错误跟踪栈的字符串
            log.error("redis服务器异常:" + ExceptionUtils.getStackTrace(e));
        }

        //2.如果不存在，则查询数据库
        log.info("从数据库中获取数据列表");
            //根据父id来查询当前列表
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
            //组装好查询条件
        dictQueryWrapper.eq("parent_id", parentId);
            //进行查询
        List<Dict> dictList = baseMapper.selectList(dictQueryWrapper);
            //填充hashChildren字段
        dictList.forEach(dict -> {
            //判断当前节点是否有子节点
            boolean hasChildren = this.hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        });
        //3.将数据存入redis 避免出问题try catch一下
        try {
            //将数据存入redis
            log.info("将数据存入redis");
            redisTemplate.opsForValue().set("financeplatform:core:dictList:" + parentId, dictList, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("redis服务器异常:" + ExceptionUtils.getStackTrace(e));
        }
        //4.返回数据列表
        return dictList;
    }

    /**
     * 判断当前id所在的节点下是否有子节点
     */
    private boolean hasChildren(Long id) {
        QueryWrapper<Dict> dictWrapper = new QueryWrapper<>();
        dictWrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(dictWrapper);
        if (count.intValue() > 0) {
            return true;
        }
        return false;
    }
}
