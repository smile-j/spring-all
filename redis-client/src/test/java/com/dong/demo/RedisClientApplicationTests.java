package com.dong.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;

@SpringBootTest(classes = RedisClientApplication.class)
@Slf4j
class RedisClientApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    //zset 相关操作

    /*
    Boolean add(K key, V value, double score);
    新增一个有序集合，存在的话为false，不存在的话为true
     */
    @Test
    void testAdd() {
        System.out.println(redisTemplate.opsForZSet().add("zset1","zset-1",1.0));
    }

    /**
     * Long add(K key, Set<TypedTuple<V>> tuples);
     * 新增一个有序集合
     */
    @Test
    public void testAddTuples(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-5",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset1",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
    }

    /**
     * Long remove(K key, Object... values);
     * 从有序集合中移除一个或者多个元素
     */
    public void testRemove(){
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        System.out.println(redisTemplate.opsForZSet().remove("zset1","zset-6"));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));

    }

    /**
     * Long rank(K key, Object o);
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    void testSort(){
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        System.out.println(redisTemplate.opsForZSet().rank("zset1","zset-6"));
        System.out.println(redisTemplate.opsForZSet().range("zset1",1,-1));

    }

    /**
     * Long count(K key, double min, double max);
     * 通过分数返回有序集合指定区间内的成员个数
     */
    @Test
    public void test1(){
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1",0,5));
        System.out.println(redisTemplate.opsForZSet().count("zset1",0,5));

    }

    /**
     * Long size(K key);
     * 获取有序集合的成员数，内部调用的就是zCard方法
     */
    @Test
    void test2(){
        System.out.println(redisTemplate.opsForZSet().size("zset1"));
    }
    void test3(){
//        Double score(K key, Object o);
//        获取指定成员的score值
        System.out.println(redisTemplate.opsForZSet().score("zset1","zset-1"));


//        Long removeRange(K key, long start, long end);
//        移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));
        System.out.println(redisTemplate.opsForZSet().removeRange("zset2",1,2));
        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));

    }


    @Test
    public void testExample() throws InterruptedException {

        System.out.println(this.getRecentBrowsingPositionIds(11L));
//        for(long i=1;i<10;i++){
//            Thread.sleep(1000);
//            this.addRecentBrowsingPosition(11L,i);
//        }

    }

    public void addRecentBrowsingPosition(long userId, long positionId) {
        String key = "mls_AnswerIdsByQuersionId:" + 123;
        // 获取已缓存的最近浏览的职位
        ZSetOperations<String, Long> zSetOperations = redisTemplate.opsForZSet();
        // zset内部是按分数来排序的，这里用当前时间做分数
        zSetOperations.add(key, positionId, System.currentTimeMillis());
        // 环形结构--4,-3,-2,-1,0,1,2,3,4
//        zSetOperations.removeRange(key, 0, -6);
    }

    public List<Long> getRecentBrowsingPositionIds(long userId) {
        if (userId <= 0) {
            return Collections.emptyList();
        }
        // 获取用户最近浏览的职位id
        String key = "mls_AnswerIdsByQuersionId:" + 123;
//        Set<Long> positionIds = redisTemplate.opsForZSet().reverseRange(key, 0, 4);
        Set<Long> positionIds = redisTemplate.opsForZSet().range(key, 0, 4);
        return new ArrayList<>(positionIds);
    }
}
