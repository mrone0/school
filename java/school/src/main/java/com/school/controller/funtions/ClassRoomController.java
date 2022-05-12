package com.school.controller.funtions;

import com.school.entity.Classroom;
import com.school.mapper.ClassroomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class ClassRoomController {
    @Autowired
    ClassroomMapper classroomMapper;
    @RequestMapping("/class")
    public List<Classroom> Class(){
        List<Classroom> classrooms = classroomMapper.selectList(null);
        return classrooms;
    }
}
