package com.example.realproject.dao;

import com.example.realproject.entity.Users;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

@Resource(name = "usersDao")
public interface UsersDao {
    Users SelectSingle(String phone);

    Users Flushed(@Param("checkUsers")Users checkUsers,@Param("users")Users users);
    /**
     * 登录接口
     */
    Users Login(@Param("checkUsers")Users checkUsers,@Param("users")Users users);

    void UpdatePW(Users users);

    void Lock(String phone);

    Integer GetResult();

    void SetResult(Map<String,Object> map);

    void NoResult();

    void Insert(Users users);

    Map GetImage(String phone);

    void DeleteU(String phone);

    void UpdateU(Users users);
    int findTotalCount(@Param("map")Map<String, Object> map);
    List<Users> findByPage(@Param("start")int start,@Param("rows")int rows,@Param("map")Map<String, Object> map);
}
