package com.dong.demo.jdk8.trick.ifesle;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

   /* public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
        if (ObjectUtil.isEmpty(source)) {}
        if (ObjectUtil.isNotEmpty(source) && source instanceof List) {
            if (optional.isPresent()) {
                if (object instanceof CouponDetail) {
                    if (ObjectUtil.isNotEmpty(couponDetails)) {
                        if (ObjectUtil.isEmpty(couponDetail)) {}
                        if (ObjectUtil.isNotEmpty(couponDetail){}
                    }
                    return String.valueOf(platformDiscount);
                }
            }
            if (object instanceof com...enGet.CouponDetail) {
                if (ObjectUtil.isNotEmpty(couponDetails)) {
                    if (ObjectUtil.isEmpty(couponDetail)) {}
                    if (ObjectUtil.isNotEmpty(couponDetail)
                }
                return String.valueOf(platformDiscount);
            }
        }
    }
            return String.valueOf(platformDiscount);
}
        return String.valueOf(platformDiscount);
}
*/
    public static String getUserinfo1(Object source){
        StringBuilder sb = new StringBuilder("v1:");
        List<UserEntry> users = (List<UserEntry>) source;
        for (UserEntry user : users) {
           sb.append( Optional.ofNullable(user).map(UserEntry::getName)
                   .orElse("null_null"));
        }
    return sb.toString();
    }
    public static String getUserinfo2(Object source){
        StringBuilder sb = new StringBuilder("v2:");
        List<UserEntry2> users = (List<UserEntry2>) source;
        for (UserEntry2 user : users) {
            sb.append(" ").append(Optional.ofNullable(user).map(UserEntry2::getName)
                    .orElse("null_null"));
        }
        return sb.toString();
    }
public static void main(String[] args) {
//   List source  source = Lists.newArrayList(new UserEntry().setName("aa"),new UserEntry().setName("bb"));
    List source = Lists.newArrayList(new UserEntry2().setName("aa"),new UserEntry2().setName("bb"),null);

    String haha = Optional.ofNullable(source)
            .filter(fSource -> fSource instanceof List)
            .map(mSource -> {
                List<?> objectList = (List<?>) source;
                return objectList
                        .stream()
                        .findFirst()
                        .map(firstSource ->
                                FunctionBuilder
                                        .returnIf(new HashMap<Object, FunctionReturn<String>>())
                                        .addReturn(UserEntry.class, () -> getUserinfo1(source))
                                        .addReturn(UserEntry2.class, () -> getUserinfo2(source))
                                        .doIfInstance(firstSource)
                                        .defaultValue("没有找到合适的处理")
                        ).orElse(null);
            })
            .orElse(String.valueOf("haha"));
    System.out.println("....................end:"+haha);


}

}
