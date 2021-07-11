package com.lagou.controller;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lagou.entity.*;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程内容管理
 */
@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 查询课程下的章节和课时信息
     * @param courseId
     * @return
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonBuCourseId(@RequestParam("courseId") int courseId){

        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "查询章节课时信息成功", sectionList);

        return result;
    }

    /**
     * 回显章节对应的课程名称
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam("courseId") int courseId){

        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "回显课程信息成功", course);

        return result;
    }

    /**
     * 添加或修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        if(courseSection.getId() == null) {

            courseContentService.saveSection(courseSection);

            ResponseResult result = new ResponseResult(true, 200, "添加章节信息成功", null);

            return result;
        }else {

            courseContentService.updateSection(courseSection);

            ResponseResult result = new ResponseResult(true, 200, "修改章节信息成功", null);

            return result;
        }
    }

    /**
     * 修改章节状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam("id") int id,@RequestParam("status") int status){

        courseContentService.updateStatus(status,id);

        Map<String,Integer> map = new HashMap<>();

        map.put("status",status);

        ResponseResult result = new ResponseResult(true, 200, "修改章节状态成功", map);

        return result;
    }

    /**
     * 添加课时信息
     * @param lesson
     * @return
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson lesson){

        if(lesson.getId() == null) {

            courseContentService.saveLesson(lesson);

            ResponseResult result = new ResponseResult(true, 200, "添加课时信息成功", null);

            return result;
        }else {

            courseContentService.updateLesson(lesson);

            ResponseResult result = new ResponseResult(true, 200, "修改课时信息成功", null);

            return result;
        }
    }
}
