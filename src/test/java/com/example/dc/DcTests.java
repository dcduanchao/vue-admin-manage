package com.example.dc;

import com.alibaba.fastjson.JSON;
import com.example.dc.from.UserListFrom;
import com.example.dc.service.MenuService;
import com.example.dc.service.UserService;
import com.example.dc.utils.ElAdminResultBeans;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

@SpringBootTest
@WebAppConfiguration
class DcTests {

    @Autowired
    MenuService menuService;

    @Test
    public void test() {
//        ElAdminResultBeans menuTree = menuService.getMenuTree("");
//
//        System.out.println(JSON.toJSONString(menuTree));

//        String a = "1";
//        Integer fanxing = fanxing(a);
//
//        System.out.println(fanxing);
//
//        List<Integer> list = Arrays.asList(3,2,1,4,0);
//        list.sort((e1,e2)->Integer.compare(e1,e2));
//
//        Comparator<Integer> a1 = (e1, e2) -> {
//            System.out.println("函数式接口");
//            int i = e1 - e2;
//            return e1 - e2;
//        };
//
//        int compare = a1.compare(1, 3);
//
//
//        Comparator<StoreDto> cmp1 = (e1, e2)->{
//            System.out.println("函数式接口");
//            int i = e1.getName().compareTo(e2.getName());
//            return  i;
//        };

//        Predicate<Integer>  p = n-> n>3;
//        boolean test = p.test(9);
//        Predicate<Integer> negate = p.negate();
//        boolean test1 = negate.test(1);
//        System.out.println(test);
//        System.out.println(test1);
//        Predicate<Integer> and = p.and(n -> n > 6);
//
//        boolean test2 = and.test(5);
//        System.out.println(test2);
//        Predicate<Integer> or = p.or(n -> n < 2);
//        System.out.println(or.test(1));

        Function<Integer,String> f = e->String.valueOf(e)+"p";
        String apply = f.apply(9);
        System.out.println(apply);

        Function<Integer,String> f1 = e->{
            int i = e + 10;

            String s = i + "1111";

            return s;
        };

        System.out.println(f1.apply(7));

        StoreDto storeDto = new StoreDto("aa","icon");

        Function<String,String> ff = e->{
            return e;
        };
        Function<StoreDto,String> s =  e -> e.getName();
        Function<StoreDto, String> compose = ff.compose(s);



        String wwwwww = compose.apply(storeDto);
        System.out.println("--"+wwwwww);


        String name = getName(storeDto, e -> e.getName());

        String name1 = getName(storeDto, StoreDto::getIcon);

        System.out.println(name+"======"+name1);


    }



    public <T,U> U getName(T t ,Function<T,U> f){
        return  f.apply(t);
    }


    public  <T,U> U fanxing(T t){
        System.out.println(t);
        String t1 = (String) t;
        return (U) Integer.valueOf(t1);
    }


    
    @Autowired
    UserService userService;
    
    @Test
    public  void  userTest(){
        UserListFrom userListFrom = new UserListFrom();
        ElAdminResultBeans beans = userService.userPageList(userListFrom);

        System.out.println(JSON.toJSONString(beans));
    }

}
