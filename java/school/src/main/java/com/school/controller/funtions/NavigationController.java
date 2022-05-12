package com.school.controller.funtions;

import com.school.entity.Navigation;
import com.school.mapper.NavigationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class NavigationController {
    @Autowired
    NavigationMapper navigationMapper;
    @RequestMapping("/daohang")
    public List<Navigation> navigation(){
        List<Navigation> navigations = navigationMapper.selectList(null);
        return navigations;
    }

}
