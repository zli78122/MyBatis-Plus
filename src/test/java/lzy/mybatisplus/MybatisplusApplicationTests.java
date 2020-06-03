package lzy.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lzy.mybatisplus.entity.User;
import lzy.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    // 查询user表所有数据
    @Test
    void contextLoads() {
        // UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        // 所以不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    // 添加操作
    @Test
    void addUser() {
        User user = new User();
        user.setName("Zachary");
        user.setAge(24);
        user.setEmail("zachary@usc.edu");

        int insert = userMapper.insert(user);
        System.out.println("insert:" + insert);
    }

    // 修改操作
    @Test
    void updateUser() {
        User user = new User();
        user.setId(1267991346510049282L);
        user.setEmail("lizhengyang@hd.com");

        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    // 测试乐观锁: 先查询再修改
    @Test
    void testOptimisticLocker() {
        // 根据id查询数据
        User user = userMapper.selectById(1268003847792570370L);
        // 进行修改
        user.setAge(18);
        userMapper.updateById(user);
    }

    // 多个id批量查询
    @Test
    void testSelectDemo1() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }

    @Test
    void testSelectByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jone");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);

        System.out.println(users);
    }

    // 分页查询
    @Test
    void testPage() {
        // 创建page对象
        // 传入两个参数：当前页 和 每页显示记录数
        Page<User> page = new Page<>(1, 3);

        // 调用mp分页查询的方法
        // 调用mp分页查询过程中，底层封装，把分页所有数据封装到page对象里面
        userMapper.selectPage(page, null);

        // 通过page对象获取分页数据
        System.out.println(page.getCurrent());  //当前页
        System.out.println(page.getRecords());  //每页数据list集合
        System.out.println(page.getSize());     //每页显示记录数
        System.out.println(page.getTotal());    //总记录数
        System.out.println(page.getPages());    //总页数
        System.out.println(page.hasNext());     //是否有下一页
        System.out.println(page.hasPrevious()); //是否有上一页
    }

    // 删除操作
    @Test
    void testDeleteById(){
        int result = userMapper.deleteById(1268017755915939842L);
        System.out.println(result);
    }

    // 批量删除
    @Test
    void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(result);
    }

    // 条件查询
    @Test
    void testSelectQuery() {
        // 创建QueryWrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 通过QueryWrapper对象设置查询条件
        wrapper.ge("age", 25);
        wrapper.eq("name", "moon");
        wrapper.between("age", 20, 30);
        wrapper.like("name", "oo");
        wrapper.orderByDesc("id");
        wrapper.last("limit 1");
        wrapper.select("id","name");

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
}
