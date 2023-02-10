//package com.example.realproject.dao.Impl;
//
//import com.example.realproject.dao.UsersDao;
//import com.example.realproject.entity.Users;
//import com.example.realproject.util.MyBatisUtil;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.stereotype.Repository;
//
//@Repository("usersDao")
//public class UsersDaoImpl implements UsersDao {
//
//    SqlSession sqlSession= MyBatisUtil.getSqlSession();
//    @Override
//    public Users SelectSingle(String phone){
//        return sqlSession.selectOne( "com.example.realproject.dao.UsersDao.SelectSingle",phone);
//    }
//
//    /**
//     * 登陆时一天刷新一次登录次数【若刷新则登录操作的时间】
//     */
//    @Override
//    public Users Flushed(Users checkUsers,Users users) {
//        //登录次数为0则返回次数为零
//        if (checkUsers.getLastTime() != null && (users.getLastTime().getTime() - checkUsers.getLastTime().getTime()) / 1000 >= 60 * 60 * 24) {
//            sqlSession.update("com.example.realproject.dao.UsersDao.AddLastTime", checkUsers);
//            sqlSession.update("com.example.realproject.dao.UsersDao.AddTryCount", users);
//        }
//        else {
//            users.setTryCount(0);
//            return users;
//        }
//        return null;
//    }
//
//    /**
//     * 登陆时验证密码【有登录次数时记录最后一次登录操作的时间】
//     */
//    //checkUsers为查找到的用户，users为输入用户信息
//    @Override
//    public Users Login(Users checkUsers,Users users){
//        sqlSession.update("com.example.realproject.dao.UsersDao.AddLastTime", users);
//        if(checkUsers!=null) {
//            //验证密码是否正确，正确登录成功，不正确报用户或密码错误
//            if (users.getPassword().equals(checkUsers.getPassword())) {
//                sqlSession.update("com.example.realproject.dao.UsersDao.SuccessLogin", checkUsers);
//                return sqlSession.selectOne("com.example.realproject.dao.UsersDao.SelectSingle", checkUsers.getPhone());
//            } else {
//                sqlSession.update("com.example.realproject.dao.UsersDao.RemoveTryCount", checkUsers);
//                users.setTryCount(checkUsers.getTryCount() - 1);
//                return users;
//            }
//        }else {
//            return null;
//        }
//    }
//
//
//    @Override
//    public void UpdatePW(Users users) {
//        sqlSession.update("com.example.realproject.dao.UsersDao.UpdatePW",users);
//    }
//}
