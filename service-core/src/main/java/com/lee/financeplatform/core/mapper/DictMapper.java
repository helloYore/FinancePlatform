package com.lee.financeplatform.core.mapper;

import com.lee.financeplatform.core.pojo.dto.ExcelDictDTO;
import com.lee.financeplatform.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author HelloYore
 * @since 2021-12-23
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDictDTO> list);

}
