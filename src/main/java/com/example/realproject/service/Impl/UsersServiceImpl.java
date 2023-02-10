package com.example.realproject.service.Impl;

//import com.example.realproject.Selectpage.UserSelectPage;
import com.example.realproject.dao.PriDao;
import com.example.realproject.dao.UsersDao;
import com.example.realproject.entity.PageBean;
import com.example.realproject.entity.Users;
import com.example.realproject.entity.permissions;
import com.example.realproject.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("usersServiceImpl")
public class UsersServiceImpl implements UsersService {

    @Resource(name = "usersDao")
    UsersDao userdao;
    @Resource(name = "priDao")
    PriDao pridao;

//    @Resource(name = "userSelectPage")
//    UserSelectPage userSelectPage;

    @Override
    public Users UsersSelect(String phone){
        return userdao.SelectSingle(phone);
    }
    @Override
    public Users UsersLogin(Users users) {
        Users checkUsers=userdao.SelectSingle(users.getPhone());
        if(checkUsers!=null){
            if (checkUsers.getTryCount() <= 0) {
                userdao.Flushed(checkUsers, users);
                if (users.getTryCount() == 0) {
                    return users;
                }
            }
            return userdao.Login(checkUsers, users);
        } else {
            return null;
        }//如果提示账户不存在则return null,在LoginController中判断提示
    }

    @Override
    public void UsersUpdatePW(Users users) {
        userdao.UpdatePW(users);
    }

    @Override
    public void UsersLock(String phone) {
        userdao.Lock(phone);
    }

    @Override
    public List<permissions> getUserPri (Integer R_id){

        List<permissions> permissions=pridao.PriSelect(R_id);

        for (permissions permission:permissions){
            Map<String, Object> paramMap=new HashMap<>();
            paramMap. put("p_id", permission.getP_id());
            paramMap. put("R_id", R_id);
            List<permissions> sonPriList = pridao.UserPriSelect(paramMap);
            permission.setPriList(sonPriList);
        }

        return permissions;
    }

    @Override
    public Integer UsersResult() {
        return userdao.GetResult();
    }

    @Override
    public void UsersAddResult(Map<String,Object> map) {
        userdao.SetResult(map);
    }

    @Override
    public void RemoveResult() {
        userdao.NoResult();
    }

    @Override
    public PageBean<Users> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);

//        if(currentPage<=0){
//            currentPage=1;
//        }
        PageBean<Users> pb=new PageBean<Users>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //将Map<String, String[]>的StringBuilder转换为Map<String, Object>的String类型
        Map<String, Object> map=new HashMap<>();
        //将String[]类型转换为String类型
        String temp= Arrays.toString(condition.get("name"));
        //去除String中的[]
        String name=temp.substring(1,temp.length()-1);
        map.put("name",name);

        int totalCount=userdao.findTotalCount(map);
        pb.setTotalCount(totalCount);
        int start=(currentPage-1)*rows;

        List<Users> list=userdao.findByPage(start,rows,map);
        pb.setList(list);

        int totalPage=(totalCount % rows) ==0 ? totalCount/rows : totalCount/rows+1;
        pb.setTotalPage(totalPage);
        return pb;
    }
    @Override
    public void UsersInsert(Users users){
        userdao.Insert(users);
    }

    @Override
    public Map UsersGetImage(String phone){
        return userdao.GetImage(phone);
    }

    public void DeleUsers(String phone){
        userdao.DeleteU(phone);
    }

    public void UpdateUsers(Users users){
        userdao.UpdateU(users);
    }
}
