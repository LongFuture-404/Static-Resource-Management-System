package com.example.realproject.dao;

import com.example.realproject.entity.permissions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository("priDao")
public interface PriDao {

    /**
     * 查询用户的模块权限
     */
    List<permissions> PriSelect(Integer R_id);

    /**
     * 查询用户的模块按钮权限
     * Integer p_id 和 Integer R_id
     */
    List<permissions> UserPriSelect(Map<String,Object> map);
}
