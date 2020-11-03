package com.example.dc.from;

import com.example.dc.bean.PageInfoBean;
import lombok.Data;

/**
 * @ Author     ：duanchao
 * @ Date       ： 9:50 2020/11/2
 * @ Description：
 */

@Data
public class UserListFrom {

    private  String userName;

    private PageInfoBean pageInfo;
}