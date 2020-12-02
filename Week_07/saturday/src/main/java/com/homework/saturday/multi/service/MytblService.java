package com.homework.saturday.multi.service;

import com.homework.saturday.multi.annotation.DataSource;
import com.homework.saturday.multi.dao.MytblDao;
import com.homework.saturday.multi.domain.Mytbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class MytblService {

    @Autowired
    private MytblDao mytblDao;

    @DataSource
    @Transactional
    public void batchSave(){
        List<Mytbl> list = createData(1,5);
        mytblDao.batchSave(list);
    }

    private List<Mytbl> createData(int start, int count){
        List<Mytbl> list = new LinkedList<>();
        for(int i = 0; i < count;i++){
            Mytbl mytbl = new Mytbl();
            mytbl.setId(i + start);
            list.add(mytbl);
        }
        return list;
    }

    /**
     * 标记读取数据的数据源
     */
    @DataSource(value = "slave1")
    @Transactional
    public void show(){
        List<Mytbl> list = mytblDao.findAll();
        for (Mytbl mytbl : list) {
            System.out.println(mytbl);
        }
    }
}
