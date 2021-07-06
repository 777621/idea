package com.lagou.service;

import com.lagou.entity.Course;
import com.lagou.entity.CourseVo;

import java.util.List;

public interface CourseService {

    /**
     * 根据条件查询课程信息
     * @param courseVo
     * @return
     */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /**
     * 添加课程信息 讲师信息
     * @param courseVo
     */
    public void saveCourseOrTeacher(CourseVo courseVo);

    /**
     * 根据课程id 查询课程 和讲师信息
     * @param id
     * @return
     */
    public CourseVo findCourseById(Integer id);

    /**
     * 修改课程信息
     * @param courseVo
     */
    public void updateCourseOrTeacher(CourseVo courseVo);

    /**
     * 修改课程状态
     * @param id
     * @param status
     */
    public void updateCourseStatus(int id,int status);
}
