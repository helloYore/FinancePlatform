package com.lee.financeplatform.core.service;

import com.lee.financeplatform.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author HelloYore
 * @since 2021-12-23
 */
public interface DictService extends IService<Dict> {
    void importData(InputStream inputStream);
}
