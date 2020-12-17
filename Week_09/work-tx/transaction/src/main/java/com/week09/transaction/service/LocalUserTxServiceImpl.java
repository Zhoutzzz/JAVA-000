package com.week09.transaction.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dependency.exception.InvalidDataException;
import dependency.repo.LocalUserTxMapper;
import dependency.repo.UserTxDO;
import dependency.service.ForeignUserTxService;
import dependency.service.LocalUserTxService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTAC;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("localService")
@DubboService
public class LocalUserTxServiceImpl implements LocalUserTxService {

    @Autowired(required = false)
    private LocalUserTxMapper localUserTxMapper;

    @Autowired(required = false)
    @DubboReference(lazy = true)
    private ForeignUserTxService foreignUserTxService;

//    public LocalUserTxServiceImpl(LocalUserTxMapper localUserTxMapper, ForeignUserTxService foreignUserTxService) {
//        this.foreignUserTxService = foreignUserTxService;
//        this.localUserTxMapper = localUserTxMapper;
//    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    @Transactional(rollbackFor = Exception.class)
    public UserTxDO getDollarsForRmb(UserTxDO newRecord) throws InvalidDataException {
        int update = localUserTxMapper.initiativeFreeze(newRecord);

        if (update != 1) {
            throw new InvalidDataException("数据异常");
        }

        foreignUserTxService.passiveFreeze(newRecord);

        return localUserTxMapper.selectList(new QueryWrapper<UserTxDO>().eq("id", newRecord.getId())).get(0);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    @Transactional(rollbackFor = Exception.class)
    public UserTxDO passiveFreeze(UserTxDO newRecord) {
        localUserTxMapper.initiativeFreeze(newRecord);
        return localUserTxMapper.selectById(newRecord.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public UserTxDO confirm(UserTxDO newRecord) throws InvalidDataException {
        System.out.println("=====================confirm执行");
        int i = localUserTxMapper.txSuccess(newRecord);
        if (i < 1) {
            throw new InvalidDataException("not success");
        }
        return newRecord;
    }

    @Transactional(rollbackFor = Exception.class)
    public UserTxDO cancel(UserTxDO newRecord) throws InvalidDataException {
        System.out.println("=====================cancel执行");
        localUserTxMapper.txFailed(newRecord);
        return localUserTxMapper.selectById(newRecord.getId());
    }
}
