package com.school.controller.funtions;

import com.school.entity.Functions;
import com.school.mapper.FunctionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class FunctionController {
    @Autowired
    FunctionsMapper functionsMapper;
    @RequestMapping("/function")
    public List<Functions> functions(){
        List<Functions> functions = functionsMapper.selectList(null);
        return functions;
    }
}
