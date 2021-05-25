package com.online.controller;

import com.online.entity.OnlineUser;
import com.online.service.OnlineUserService;
import com.online.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OnlineUserController {
    @Autowired
    private OnlineUserService onlineUserService;

    /**
     * 登录
     * 根据工号或者学号 密码登录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultSet login(OnlineUser user, HttpServletRequest request){
        String loginName = user.getSerialNumber();
        String pwd = user.getPassword();
        if(loginName == null || loginName == "" || pwd == null || pwd == ""){
            return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("登录失败");
        }
        OnlineUser u = onlineUserService.getUserByLoginNameAndPassword(loginName, pwd);
        if(u != null){
            request.getSession().setAttribute("user", u);
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(u).setMsg("登录成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("登录失败");
    }

    /**
     * 老师登录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    public ResultSet loginAdmin(OnlineUser user, HttpServletRequest request){
        String loginName = user.getSerialNumber();
        String pwd = user.getPassword();
        if(loginName == null || loginName == "" || pwd == null || pwd == ""){
            return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("登录失败");
        }
        OnlineUser u = onlineUserService.getAdminUserByLoginNameAndPassword(loginName, pwd);
        if(u != null){
            request.getSession().setAttribute("user", u);
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(u).setMsg("登录成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("登录失败");
    }

    /**
     * 注册
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResultSet registerUser(OnlineUser user){
        int temp = onlineUserService.register(user);
        if(temp == 1){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(1).setMsg("注册成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(0).setMsg("注册失败");
    }

    /**
     * 获取所有用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    public ResultSet getUsers(){
        List<OnlineUser> users = onlineUserService.getUsers();
        if(users != null){
            return new ResultSet().setCode(HttpStatus.OK.value()).setData(users).setMsg("查询成功");
        }
        return new ResultSet().setCode(HttpStatus.NOT_ACCEPTABLE.value()).setData(null).setMsg("查询失败");
    }

    /**
     * 审核用户
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public String changeStatus(String id, int status){
        int temp = onlineUserService.changeStatus(id, status);
        if(temp == 1){
            return "ok";
        }
        return "no";
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delUser", method = RequestMethod.POST)
    public int delUser(String id){
        int temp = onlineUserService.delUser(id);
        return temp;
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "ok";
    }

}
