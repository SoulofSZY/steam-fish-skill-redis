/**
 * Copyright (C), 2014-2019, 深圳兔展智能科技有限公司
 * FileName: JedisTest
 * Author:   EDZ
 * Date:     2019/6/18 13:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szy.skill.redis.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/6/18
 * @since 1.0.0
 */
@Slf4j
public class JedisTest {

    @Test
    public void test4Conn() {
        Jedis jedis = new Jedis("localhost");
        log.info("----" + jedis.ping());
    }

    Jedis jedis;

    @Before
    public void init() {
        jedis = new Jedis("localhost");
        log.info("----" + jedis.ping());
    }

    @After
    public void end() {
        jedis.close();
    }

    @Test
    public void test4String() {
        jedis.set("szy:alpha:2", "javaAPI");

        log.info(jedis.get("szy:alpha:2"));
    }

    @Test
    public void test4List() {
        // 存储数据到列表
        jedis.lpush("szy:language", "java");
        jedis.lpush("szy:language", "python");
        jedis.lpush("szy:language", "go");
        jedis.lpush("szy:language", "c");

        List<String> list = jedis.lrange("szy:language", 0, 2);
        list.stream().forEach(item -> log.info("---{}", item));
    }

    @Test
    public void test4Keys() {
        Set<String> keys = jedis.keys("*");
        keys.stream().forEach(log::info);
    }


    @Test
    public void test(){
        List list =  new ArrayList();
        list.add(null);
    }

}