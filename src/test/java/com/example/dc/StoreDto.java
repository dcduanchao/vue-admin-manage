package com.example.dc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ Author     ：duanchao
 * @ Date       ： 11:09 2020/9/15
 * @ Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto   {

    private  String  name;

    private String icon;
}