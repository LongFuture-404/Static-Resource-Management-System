//package com.example.realproject.Selectpage;
//
//import com.example.realproject.entity.Users;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//@Repository("userSelectPage")
//public class UserSelectPageImpl implements UserSelectPage {
//    @Resource(name = "jdbcTemplate")
//    JdbcTemplate template;
//    @Override
//    public int findTotalCount(Map<String, String[]> condition) {
//        String sql="select count(*) from users where 1=1";
//        StringBuilder sb=new StringBuilder(sql);
//
//        Set<String> keySet=condition.keySet();//遍历Map
//        List<Object> prams=new ArrayList<Object>();
//
//        for (String key:keySet){
//            if("currentPage".equals(key)||"rows".equals(key)){
//                continue;
//            }
//
//            String value=condition.get(key)[0];
//            if (value!=null && !"".equals(value)){
//                sb.append(" and "+key+" like ? ");
//                prams.add("%"+value+"%");
//            }
//        }
//
//        sql=sb.toString();
//        return template.queryForObject(sql, Integer.class,prams.toArray());
//    }
//
//    @Override
//    public List<Users> findByPage(int start, int rows, Map<String, String[]> condition) {
//        String sql="select * from users where 1=1";
//        StringBuilder sb=new StringBuilder(sql);
//
//        Set<String> keySet=condition.keySet();//遍历Map
//        List<Object> prams=new ArrayList<Object>();
//
//        for (String key:keySet){
//            if("currentPage".equals(key)||"rows".equals(key)){
//                continue;
//            }
//
//            String value=condition.get(key)[0];
//            if (value!=null && !"".equals(value)){
//                sb.append(" and "+key+" like ? ");
//                prams.add("%"+value+"%");
//            }
//        }
//        sb.append(" limit ?,? ");
//        prams.add(start);
//        prams.add(rows);
//
//        sql=sb.toString();
//        return template.query(sql,new BeanPropertyRowMapper<Users>(Users.class),prams.toArray());
//    }
//
//}
