package com.dong.demo.jdk8.trick;

import lombok.Data;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.Optional;

@Data
public class MethodParam {

    /**
     *  为了更好的表明该方法是不是可以传null值，可以通过@NonNull和@Nullable注解来标记。
     *  @NonNull就表示不能传null值，@Nullable就是可以传null值。
     */
    public static void main(String[] args) {
//        testNotNull("OK");
//        testNotNull(null);

        testNull("OK");
        testNull(null);

        System.out.println(getPersonByName("sd").isPresent());
    }

    public static void testNotNull(@NonNull String name){
        System.out.println("--->"+name);
    }
    public static String testNull(@Nullable String name){
        System.out.println("--->"+name);
        return name;
    }

    public static Optional<String> getPersonByName(String name) {
        return Optional.ofNullable(testNull(name));
//        return Optional.of(testNull(name));
    }
}
