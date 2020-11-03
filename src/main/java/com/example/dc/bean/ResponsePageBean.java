package com.example.dc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:48 2020/11/2
 * @ Description：
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePageBean<T> {

    private List<T> list;

    private PageInfoBean pageInfo;



}