package com.example.dc;

import com.alibaba.fastjson.JSON;
import com.example.dc.service.MenuService;
import com.example.dc.utils.ElAdminResultBeans;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
class DcTests {

    @Autowired
    MenuService menuService;

    @Test
    public void test() {
        ElAdminResultBeans menuTree = menuService.getMenuTree("");

        System.out.println(JSON.toJSONString(menuTree));

    }


}
