package cn.yzaaa.mpdemo1010;

import cn.yzaaa.mpdemo1010.entity.User;
import cn.yzaaa.mpdemo1010.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
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
        user.setName("哈拉少1");
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
    //多个id查询
    @Test
    public void test1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }

    /**
     * 分页查询
     */
    @Test
    public void test2(){
        //常见page对象
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page,null);
        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal()); //总记录数
        System.out.println(page.getPages()); //总页数

        System.out.println(page.hasNext()); //下一页
        System.out.println(page.hasPrevious()); //上一页
    }
    /**
     * 删除操作 物理删除
     */
    @Test
    public void test3(){
        int result = userMapper.deleteById(1L);
        System.out.println(result);
    }
    /**
     * 批量删除
     */
    @Test
    public void test4(){
        int i = userMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(i);
    }
    /**
     * 逻辑删除
     */


    //mp实现复杂查询操作
    @Test
    public void testSelectQuery() {
        //创建QueryWrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //通过QueryWrapper设置条件
        //ge、gt、le、lt
        //查询age>=30记录
        //第一个参数字段名称，第二个参数设置值
//        wrapper.ge("age",30);

        //eq、ne
        //wrapper.eq("name","lilei");
        //wrapper.ne("name","lilei");

        //between
        //查询年龄 20-30
        // wrapper.between("age",20,30);

        //like
        //wrapper.like("name","岳");

        //orderByDesc
        // wrapper.orderByDesc("id");

        //last
        //wrapper.last("limit 1");

        //指定要查询的列
        wrapper.select("id","name");

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }
}
