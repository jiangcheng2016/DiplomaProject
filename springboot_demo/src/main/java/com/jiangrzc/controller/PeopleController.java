package com.jiangrzc.controller;

import com.jiangrzc.model.People;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PeopleController {

    @RequestMapping("/")

    /**
     * 1.底层 Jackson 进行转换
     *
     * 2.怎么转？
     *  2.1 判断返回值是否满足 key-value 形式(类，map，List<类或map>).
     *  如果满足
     *      2.1.1 设置响应头Content-type:allplication/json;charset=utf-8
     *      2.1.2 把返回结果转换为 json 字符串
     *      2.1.3 把json字符串设置到响应体中
     *  如果不满足
     *      直接把返回结果设置到响应体中。
     *      不修改Content-type。结果:text/html 或 text/plain
     */

    @ResponseBody
    public People show(){

        return new People("张三","男");
    }


}
