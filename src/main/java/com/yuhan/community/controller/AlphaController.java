package com.yuhan.community.controller;

import com.yuhan.community.entity.Page;
import com.yuhan.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuhan
 * @create 2022-07-21 21:06
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping(path = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getData2(@PathVariable("id") int id){
        return "data" + id;
    }


    @RequestMapping(path = "/post",method = RequestMethod.POST)
    @ResponseBody
    public String getData3(int id, String name){
        return "data:" + id + " " + name;
    }

    @RequestMapping(path = "/gethtml1",method = RequestMethod.GET)
    public ModelAndView getHtml1(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name","张三");
        mv.addObject("age",30);
        mv.setViewName("/demo/view");
        return mv;
    }

    @RequestMapping(path = "/gethtml2",method = RequestMethod.GET)
    public String getHtml2(Model model){
        model.addAttribute("name","lishi");
        model.addAttribute("age",30);
        return "/demo/view";
    }

    @RequestMapping(path = "/getjson",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getJson(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(16);
        map.put("name","张三");
        map.put("age",30);
        list.add(map);

        Map<String, Object> map2 = new HashMap<>(16);
        map2.put("name","李四");
        map2.put("age",40);
        list.add(map2);
        return list;
    }
}
