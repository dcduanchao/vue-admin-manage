package com.example.dc.query;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:35 2020/10/30
 * @ Description：
 */
public class QueryHelp {


    public <R, Q> Predicate getPredicate(Root<R> root, Q query, CriteriaBuilder cb) {

        List<Predicate> list = new ArrayList<>();
        if (query == null) {
            return cb.and(list.toArray(new Predicate[0]));
        }
        try {
            List<Field> fields = getAllFields(query.getClass(), new ArrayList<>());

            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                JpaQuery jpaQuery = field.getAnnotation(JpaQuery.class);
                if (null != jpaQuery) {
                    String propName = StringUtils.isNotBlank(jpaQuery.propName()) ? jpaQuery.propName() : field.getName();
                    Class<?> fieldType = field.getType();
                    Object val = field.get(query);
                    if (val == null || val.equals((Object) null) || "".equals(val)) {
                        continue;
                    }

                    JpaQuery.Type type = jpaQuery.type();
//                    Class<? extends Comparable> fieldType1 = (Class<? extends Comparable>) fieldType;
                    switch (type) {
                        case EQUAL:
                            
                            list.add(cb.equal(root.get(propName).as(fieldType), val));
                            break;
                        case GREATER_THAN:
                            list.add(cb.greaterThanOrEqualTo(root.get(propName)
                                    .as((Class<? extends Comparable>) fieldType), (Comparable) val));
                            break;
                        case LESS_THAN:
                            list.add(cb.lessThanOrEqualTo(root.get(propName)
                                    .as((Class<? extends Comparable>) fieldType), (Comparable) val));
                            break;
                        case LESS_THAN_NQ:
                            list.add(cb.lessThan(root.get(propName)
                                    .as((Class<? extends Comparable>) fieldType), (Comparable) val));
                            break;
                        case INNER_LIKE:
                            list.add(cb.like(root.get(propName).as(String.class), "%" + val.toString() + "%"));
                            break;
                        case LEFT_LIKE:
                            list.add(cb.like(root.get(propName).as(String.class), "%" + val.toString()));
                            break;
                        case RIGHT_LIKE:
                            list.add(cb.like(root.get(propName).as(String.class), val.toString() + "%"));
                            break;
                        case IN:
                            if (CollectionUtils.isNotEmpty((Collection<Long>) val)) {
                                list.add(root.get(propName).in((Collection<Long>) val));
                            }
                            break;
                        case NOT_EQUAL:
                            list.add(cb.notEqual(root.get(propName), val));
                            break;
                        case NOT_NULL:
                            list.add(cb.isNotNull(root.get(propName)));
                            break;
                        case BETWEEN:
                            List<Object> between = new ArrayList<>((List<Object>) val);
                            list.add(cb.between(root.get(propName).as((Class<? extends Comparable>) between.get(0).getClass()),
                                    (Comparable) between.get(0), (Comparable) between.get(1)));
                            break;
                        default:
                            break;
                    }


                }


                field.setAccessible(accessible);

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return cb.and(list.toArray(new Predicate[list.size()]));
    }

    
    private  List<Field> getAllFields(Class clazz, List<Field> fields) {

        if (null != clazz) {
            Field[] declaredFields = clazz.getDeclaredFields();
            fields.addAll(Arrays.asList(declaredFields));
            getAllFields(clazz.getSuperclass(), fields);
        }

        return fields;
    }
}