package cn.yzaaa.mpdemo1010;

import cn.yzaaa.mpdemo1010.entity.User;
import cn.yzaaa.mpdemo1010.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Mpdemo1010ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //查询user所有数据
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    //添加操作
    @Test
    public void addUser(){
        User user = new User();
        user.setAge(28);
        user.setName("xixixi");
        user.setEmail("12345ss6@qq.com");

        int insert = userMapper.insert(user);
        System.out.println("insert:" + insert);
    }
    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        //根据id查询数据
        User user = userMapper.selectById(1384871068545196033L);
        //进行修改
        user.setAge(200);
        userMapper.updateById(user);
    }

}
