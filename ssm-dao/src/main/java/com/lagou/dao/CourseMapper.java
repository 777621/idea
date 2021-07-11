package com.lagou.dao;

import com.lagou.entity.Course;
import com.lagou.entity.CourseVo;
import com.lagou.entity.Teacher;
import tk.mybatis.mapper.common.Mapper;



/**
 * 课程信息dao接口
 */
public interface CourseMapper extends Mapper<Course> {

    /**
     * 根据条件查询课程信息
     * @param courseVo
     * @return
     */
    //public List<Course> findCourseByCondition(CourseVo courseVo);

    /**
     * 新增课程信息
     * @param course
     */
    //public void saveCourse(Course course);

    /**
     * 新增讲师信息
     * @param teacher
     */
    //public void saveTeacher(Teacher teacher);

    /**
     * 根据课程id 查询课程 和讲师信息 课程信息回显
     * @param id
     * @return
     */
    //public CourseVo findCourseById(Integer id);

    /**
     * 修改课程信息
     * @param course
     */
    //public void updateCourse(Course course);

    /**
     * 修改讲师信息
     * @param teacher
     */
    //public void updateTeacher(Teacher teacher);

    /**
     * 修改课程状态
     * @param course
     */
    //public void updateCourseStatus(Course course);


}
