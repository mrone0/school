package com.school.controller.funtions;

import com.school.entity.Library;
import com.school.mapper.LibraryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class LibraryController {
    @Autowired
    LibraryMapper libraryMapper;
    @RequestMapping("/library")
    public List<Library> ErShou(){
        List<Library> list = libraryMapper.selectList(null);
        return list;
    }
}
