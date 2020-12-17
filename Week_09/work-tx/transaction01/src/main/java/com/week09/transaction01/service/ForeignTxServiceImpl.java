package com.week09.transaction01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dependency.exception.InvalidDataException;
import dependency.repo.ForeignUserTxMapper;
import dependency.repo.UserTxDO;
import dependency.service.ForeignUserTxService;
import dependency.service.LocalUserTxService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("foreignService")
@DubboService
public class ForeignTxServiceImpl implements ForeignUserTxService {

    @Autowired(required = false)
    private ForeignUserTxMapper userTxMapper;

    @Autowired(required = false)
    @DubboReference(lazy = true)
    private LocalUserTxService localUserTxService;

//    public ForeignTxServiceImpl(LocalUserTxService localUserTxService, ForeignUserTxMapper userTxMapper) {
//        this.localUserTxService = localUserTxService;
//        this.userTxMapper = userTxMapper;
//    }

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    @Transactional(rollbackFor = Exception.class)
    public UserTxDO getRmbForDollars(UserTxDO newRecord) throws InvalidDataException {
        int update = userTxMapper.initiativeFreeze(newRecord);
        if (update > 0) {
            localUserTxService.passiveFreeze(newRecord);
        }
        return userTxMapper.selectList(new QueryWrapper<>(newRecord)).get(0);
    }

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    @Transactional(rollbackFor = Exception.class)
    public UserTxDO passiveFreeze(UserTxDO freeze) {
        userTxMapper.initiativeFreeze(freeze);
        return userTxMapper.selectById(freeze.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public UserTxDO confirm(UserTxDO newRecord) throws InvalidDataException {
        System.out.println("=====================confirm执行");
        userTxMapper.txSuccess(newRecord);
        return newRecord;
    }

    @Transactional(rollbackFor = Exception.class)
    public UserTxDO cancel(UserTxDO newRecord) throws InvalidDataException {
        System.out.println("=====================cancel执行");
        userTxMapper.txFailed(newRecord);
        return userTxMapper.selectById(newRecord.getId());
    }
}
