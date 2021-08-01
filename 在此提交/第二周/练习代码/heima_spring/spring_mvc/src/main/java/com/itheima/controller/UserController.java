package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.User;
import com.itheima.domain.VO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/xxx")
public class UserController {

    @RequestMapping(value = "/quick19")
    @ResponseBody
    public void save19(String username, MultipartFile[] uploadFile)throws Exception{
        System.out.println(username);
        //获得上传文件的名称
        for(MultipartFile multipartFile:uploadFile){
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("C:\\upload\\"+originalFilename));
        }
    }

    @RequestMapping(value = "/quick18")
    @ResponseBody
    public void save18(String username, MultipartFile uploadFile,MultipartFile uploadFile2)throws Exception{
        System.out.println(username);
        //获得上传文件的名称
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("C:\\upload\\"+originalFilename));
        String originalFilename2 = uploadFile2.getOriginalFilename();
        uploadFile2.transferTo(new File("C:\\upload\\"+originalFilename2));
    }

    @RequestMapping(value = "/quick17")
    @ResponseBody
    public void save17(@CookieValue(value = "JESSIONID",required = false) String jsessionId)throws Exception{
        System.out.println(jsessionId);
    }

    @RequestMapping(value = "/quick16")
    @ResponseBody
    public void save16(@RequestHeader(value="User-Agent",required = false) String user_agent)throws Exception{
        System.out.println(user_agent);
    }

    @RequestMapping(value = "/quick15")
    @ResponseBody
    public void save15(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
    }

    @RequestMapping(value = "/quick14")
    @ResponseBody
    public void save14(VO vo)throws Exception{
        System.out.println(vo);
    }

    @RequestMapping(value = "/quick13")
    @ResponseBody
    public void save13(String[] strs)throws Exception{
        System.out.println(Arrays.asList(strs));
    }

    @RequestMapping(value = "/quick12")
    @ResponseBody
    public void save12(User user)throws Exception{
        System.out.println(user);
    }

    @RequestMapping(value = "/quick11")
    @ResponseBody
    public void save11(String username,int age)throws Exception{
        System.out.println(username);
        System.out.println(age);
    }

    @RequestMapping(value = "/quick10")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    //期望SpringMVC自动将User转换成Json格式的字符串
    public User save10()throws Exception{
        User user = new User();
        user.setUsername("lisi");
        user.setAge(30);
        return user;
    }

    @RequestMapping(value = "/quick9")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public String save9()throws Exception{
        User user = new User();
        user.setUsername("lisi");
        user.setAge(30);
        //使用json的转换工具将对象转换成json格式字符串返回
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        return json;
    }

    @RequestMapping(value = "/quick8")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public String save8()throws Exception{
        return "{\"username\":\"zhangsan\",\"age\":18";
    }

    @RequestMapping(value = "/quick7")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public String save7()throws Exception{
        return "hello ii";
    }


    @RequestMapping(value = "/quick6")
    public void save6(HttpServletResponse response)throws Exception{
        response.getWriter().print("hello itcast");
    }

    @RequestMapping(value = "/quick5")
    public String save5(HttpServletRequest request){
        request.setAttribute("username","sdas");
        return  "success";
    }

    @RequestMapping(value = "/quick4")
    public String save4(Model model){
        model.addAttribute("username","剥削谷");
        return  "success";
    }


    @RequestMapping(value = "/quick3")
    public ModelAndView save3(ModelAndView modelAndView){
        //设置模型数据
        modelAndView.addObject("username","itca");
        //设置视图名称
        modelAndView.setViewName("success");
        return  modelAndView;
    }

    @RequestMapping(value = "/quick2")
    public ModelAndView save2(){
        /*
        Model:模型：作用封装数据
        View：视图 作用展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username","itcast");
        //设置视图名称
        modelAndView.setViewName("success");
        return  modelAndView;
    }


    //请求地址 http://localhost:8080/xxx/quick
    @RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"})
    public String save(){
        System.out.println("Controller save running....");
        return "/jsp/success.jsp";//redirect重定向
    }
}
