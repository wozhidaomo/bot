package com.zhuoyao.service;

import com.alibaba.fastjson.JSONArray;
import com.zhuoyao.mapper.DataMapper;
import com.zhuoyao.model.Yaoling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YaolingDataDeal {

    @Autowired
    private DataMapper dataMapper;

    /**
     * 处理传回的妖灵数据
     * @param datas
     * @return
     */
    public String batchSave(List<Yaoling> datas){
        int re=0;
        try{
            re=dataMapper.inserts(datas);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(re);
    }
}
