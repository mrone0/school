package com.school.controller.funtions;

import com.school.entity.Daohang;
import com.school.mapper.DaohangMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class DaoHangController {
    @Autowired
    DaohangMapper daohangMapper;
    @RequestMapping("/daohang1")
    public List<Daohang> DaoHang(){
        List<Daohang> daohangs = daohangMapper.selectList(null);
         return daohangs;
    }
}
