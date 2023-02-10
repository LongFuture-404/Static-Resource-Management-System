package com.example.realproject.service;

import com.example.realproject.entity.PageBean;
import com.example.realproject.entity.Users;
import com.example.realproject.entity.permissions;

import java.util.List;
import java.util.Map;

public interface UsersService {
    Users UsersSelect(String phone);
    Users UsersLogin(Users users);

    void UsersUpdatePW(Users users);

    void UsersLock(String phone);

    /**
     * 权限查询
     */
    List<permissions> getUserPri (Integer R_id);

    Integer UsersResult();

    void UsersAddResult(Map<String,Object> map);

    void RemoveResult();

    PageBean<Users> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition);

    void UsersInsert(Users users);

    Map UsersGetImage(String phone);

    void DeleUsers(String phone);

    void UpdateUsers(Users users);
}
