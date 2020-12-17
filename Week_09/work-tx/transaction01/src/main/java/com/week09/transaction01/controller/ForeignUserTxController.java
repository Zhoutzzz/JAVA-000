package com.week09.transaction01.controller;

import dependency.exception.InvalidDataException;
import dependency.repo.UserTxDO;
import com.week09.transaction01.service.ForeignTxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ForeignUserTxController {

    private final ForeignTxServiceImpl foreignTxServiceImpl;

    @Autowired
    public ForeignUserTxController(ForeignTxServiceImpl foreignTxServiceImpl) {
        this.foreignTxServiceImpl = foreignTxServiceImpl;
    }

    @PostMapping("/add")
    public UserTxDO insertNewRecord(@RequestBody UserTxDO newRecord) throws InvalidDataException {
        return foreignTxServiceImpl.getRmbForDollars(newRecord);
    }
}
