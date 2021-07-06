package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.entity.Course;
import com.lagou.entity.CourseVo;
import com.lagou.entity.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    /**
     * 根据条件查询课程信息
     */
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {

        List<Course> courseList = courseMapper.findCourseByCondition(courseVo);

        return courseList;
    }

    /**
     * 添加课程 讲师信息
     * @param courseVo
     */
    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) {

        try {
            Course course = new Course();

            BeanUtils.copyProperties(course,courseVo);

            //补全课程信息
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            //保存课程信息
            courseMapper.saveCourse(course);

            //获取主键
            int id = course.getId();

            //补全讲师信息
            Teacher teacher = new Teacher();

            BeanUtils.copyProperties(teacher,courseVo);

            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setIsDel(0);
            teacher.setCourseId(id);

            courseMapper.saveTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 课程信息回显
     * @param id
     * @return
     */
    @Override
    public CourseVo findCourseById(Integer id) {

        return courseMapper.findCourseById(id);
    }

    /**
     * 修改课程信息
     * @param courseVo
     */
    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) {

        try {
            Course course = new Course();

            BeanUtils.copyProperties(course,courseVo);

            //补全信息
            Date date = new Date();
            course.setUpdateTime(date);

            courseMapper.updateCourse(course);

            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);

            //补全信息
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);

            courseMapper.updateTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改课程状态
     * @param id
     * @param status
     */
    @Override
    public void updateCourseStatus(int id, int status) {

        //封装数据
        Course course = new Course();
        course.setId(id);
        course.setUpdateTime(new Date());
        course.setStatus(status);

        //调用业务
        courseMapper.updateCourseStatus(course);
    }
}
