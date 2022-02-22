//package com.cloud.ribbon.consumer.service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.cloud.ribbon.consumer.util.EADataBaseUtil;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.*;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.util.HashMap;
//import java.util.Map;
//@Service
//public class EFFService {
//    @Resource
//    DataSource dataSource;
//
//    protected Connection getConn() {
//        Connection conn=null;
//        try {
//            conn= dataSource.getConnection();
//        } catch (Exception e) {
//
//        }
//        return conn;
//    }
//
//    @RequestMapping(value = "/Login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public Map<String, Object> Login(@RequestBody JSONObject json, HttpServletRequest request) {
//        EADataBaseUtil db = new EADataBaseUtil();
//        Map<String, Object> map = new HashMap<String, Object>();
//        String sql = "";
//     /*   JSONObject js= JSON.parseObject(json);
//        String id=js.getString("id");
//        String name=js.getString("name");*/
//        String usrid = json.getString("usrid");
//        String passwd = json.getString("passwd");
//        String org = json.getString("org");
//        Object[] object = {usrid,org};
//        try {
//            sql="select id,name,passwd,org,guid,imgguid,mobile,post,sex,idcard,deptid from usr where id=? and org=?";
//            Map<String, Object> usrmap= db.querySql(sql, object, getConn());
//            if (usrmap.size() > 0) {
//                String usrPwd=usrmap.get("PASSWD").toString();
//                if(usrPwd.equals(passwd)){
//                    map.put("code", 1);
//                    map.put("message", "成功");
//                    map.put("data", usrmap);
//                }
//                else{
//                    map.put("code", -1);
//                    map.put("message", "您输入的密码错误，请重新输入");
//                }
//            } else {
//                map.put("code", -1);
//                map.put("message", "此用户不存在");
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        return map;
//    }
//
//
//    @RequestMapping(value = "/selectId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public Map<String, Object> selectId(@RequestParam("usrid") String usrid) {
//        EADataBaseUtil db = new EADataBaseUtil();
//        Map<String, Object> map = new HashMap<String, Object>();
//        String sql = "";
//        try {
//            sql = "select id,name from usr where id=? ";
//            Object[] object = {usrid};
//            map = db.querySql(sql, object,getConn());
//            if (map.size() > 0) {
//                map.put("code", 1);
//            } else {
//                map.put("code", -1);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return map;
//    }
//}
