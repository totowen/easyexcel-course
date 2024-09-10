package com.geeksss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geeksss.entity.UserInfoEntity;
import com.geeksss.mapper.UserInfoMapper;
import com.geeksss.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户信息，Service 实现类。
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements IUserInfoService {
}
