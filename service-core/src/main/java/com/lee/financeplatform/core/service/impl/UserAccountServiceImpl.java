package com.lee.financeplatform.core.service.impl;

import com.lee.financeplatform.core.pojo.entity.UserAccount;
import com.lee.financeplatform.core.mapper.UserAccountMapper;
import com.lee.financeplatform.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author HelloYore
 * @since 2021-12-23
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
