package com.school.controller.funtions;

import com.school.entity.Meishi;
import com.school.mapper.MeishiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class MeiShiController {
    @Autowired
    MeishiMapper meishiMapper;
    @RequestMapping("/meishi")
    public List<Meishi> MeiShi(){
        List<Meishi> meishis = meishiMapper.selectList(null);
        return meishis;
    }
}
