package com.example.dc.vo.detail;

import com.example.dc.vo.UserVo;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:22 2020/9/15
 * @ Description：
 */
@Data
public class DetailCommentVo {
   private UserVo user;

   private  String content;

   private Timestamp created;

   private List<String> images;

}