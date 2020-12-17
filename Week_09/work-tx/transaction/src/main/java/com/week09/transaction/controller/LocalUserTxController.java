package com.week09.transaction.controller;

import dependency.exception.InvalidDataException;
import dependency.repo.UserTxDO;
import com.week09.transaction.service.LocalUserTxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LocalUserTxController {

    private final LocalUserTxServiceImpl userTxServiceImpl;

    @Autowired
    public LocalUserTxController(LocalUserTxServiceImpl userTxServiceImpl) {
        this.userTxServiceImpl = userTxServiceImpl;
    }

    @PostMapping("/add")
    public UserTxDO insertNewRecord(@RequestBody UserTxDO newRecord) throws InvalidDataException {
        return userTxServiceImpl.getDollarsForRmb(newRecord);
    }
}
