package com.homework.saturday.multi.dao;

import com.homework.saturday.multi.domain.Mytbl;

import java.util.List;

public interface MytblDao {

    void batchSave(List<Mytbl> list);

    List<Mytbl>  findAll();
}
