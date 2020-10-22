package com.example.dc.vo.detail;

import com.example.dc.vo.KeyValueVo;
import lombok.Data;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:49 2020/9/15
 * @ Description：
 */
@Data
public class DetailParamsVo {

   private List<List<String>> rule;

   private List<KeyValueVo> info;
}