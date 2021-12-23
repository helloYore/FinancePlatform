package com.lee.financeplatform.core.service.impl;

import com.lee.financeplatform.core.pojo.entity.UserInfo;
import com.lee.financeplatform.core.mapper.UserInfoMapper;
import com.lee.financeplatform.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author HelloYore
 * @since 2021-12-23
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
