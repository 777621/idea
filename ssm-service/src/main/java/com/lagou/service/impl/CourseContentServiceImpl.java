package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.entity.Course;
import com.lagou.entity.CourseLesson;
import com.lagou.entity.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    /**
     * 查询课程下的章节和课时信息
     * @param courseId
     * @return
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {

        return courseContentMapper.findSectionAndLessonByCourseId(courseId);
    }

    /**
     * 回显课程信息
     * @param courseId
     * @return
     */
    @Override
    public Course findCourseByCourseId(int courseId) {

        return courseContentMapper.findCourseByCourseId(courseId);
    }

    /**
     * 添加章节信息
     * @param section
     */
    @Override
    public void saveSection(CourseSection section) {

        //封装数据
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        //调用业务
        courseContentMapper.saveSection(section);
    }

    /**
     * 修改章节信息
     * @param section
     */
    @Override
    public void updateSection(CourseSection section) {

        //封装数据
        section.setUpdateTime(new Date());

        //调用业务
        courseContentMapper.updateSection(section);
    }

    /**
     * 修改章节状态
     * @param status
     * @param id
     */
    @Override
    public void updateStatus(int status, int id) {

        //封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);
        courseSection.setStatus(status);

        //调用业务
        courseContentMapper.updateStatus(courseSection);
    }

    /**
     * 添加课时信息
     * @param lesson
     */
    @Override
    public void saveLesson(CourseLesson lesson) {

        Date date = new Date();

        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);
        lesson.setIsDel(0);
        lesson.setStatus(2);

        courseContentMapper.saveLesson(lesson);
    }
}
