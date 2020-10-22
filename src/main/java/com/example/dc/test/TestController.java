package com.example.dc.test;

import com.example.dc.ann.AnonymousAccess;
import com.example.dc.utils.ElAdminResultBeans;
import com.example.dc.utils.ResponseUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:25 2020/9/28
 * @ Description：
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @AnonymousAccess
    @GetMapping("data1")
    public ElAdminResultBeans dataInfo() {

        List<DataVo> dataVoList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            DataVo dataVo = new DataVo();
            dataVo.setDate("2020-02-06");
            dataVo.setName("数据" + i);
            dataVo.setAddress("地址" + i);
            dataVoList.add(dataVo);
        }
        return ResponseUtils.success(dataVoList);
    }



    @GetMapping("data2")
    @PreAuthorize("hasAuthority('test')")
    public ElAdminResultBeans dataInfo1() {

        List<DataVo> dataVoList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            DataVo dataVo = new DataVo();
            dataVo.setDate("2020-02-06");
            dataVo.setName("数据" + i);
            dataVo.setAddress("地址" + i);
            dataVoList.add(dataVo);
        }
        return ResponseUtils.success(dataVoList);
    }

    @GetMapping("data3")
    @PreAuthorize("@el.check('test1')")
    public ElAdminResultBeans dataInfo2() {

//        int i1 = 1 / 0;

        List<DataVo> dataVoList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            DataVo dataVo = new DataVo();
            dataVo.setDate("2020-02-06");
            dataVo.setName("数据" + i);
            dataVo.setAddress("地址" + i);
            dataVoList.add(dataVo);
        }
        return ResponseUtils.success(dataVoList);
    }

}