package com.example.dc.utils;

import com.example.dc.bean.PageInfoBean;
import com.example.dc.entity.user.UserRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @ Author     ：duanchao
 * @ Date       ： 10:01 2020/11/2
 * @ Description：
 */
public class PageUtils<T> {

   public static PageRequest toPageRequest(PageInfoBean pageInfoBean, Sort sort){

       if(null==pageInfoBean){
           if(null==sort){
               return  PageRequest.of(0,10);
           }else {
               return  PageRequest.of(0,10,sort);
           }

       }

       int page = pageInfoBean.getPage();
       int pageSize = pageInfoBean.getPageSize();
       int startPage = (page-1);
       if(null==sort){
           return  PageRequest.of(startPage,pageSize);
       }else {
           return  PageRequest.of(startPage,pageSize,sort);
       }
   }


    public static <T> PageInfoBean getPageInfo(Page<T> pageList) {

        long totalElements = pageList.getTotalElements();
        if(totalElements==0){
            return  new PageInfoBean(pageList.getNumber(),pageList.getSize(),0,0);
        }
        int totalPages = pageList.getTotalPages();
        return  new PageInfoBean(pageList.getNumber()+1,pageList.getSize(),(int)totalElements,totalPages);



    }
}