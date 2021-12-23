package com.lee.financeplatform.core.service.impl;

import com.lee.financeplatform.core.pojo.entity.UserLoginRecord;
import com.lee.financeplatform.core.mapper.UserLoginRecordMapper;
import com.lee.financeplatform.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author HelloYore
 * @since 2021-12-23
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
