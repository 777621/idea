package com.lagou.service;

import com.lagou.entity.Course;
import com.lagou.entity.CourseLesson;
import com.lagou.entity.CourseSection;

import java.util.List;

/**
 * 课程内容管理 service
 */
public interface CourseContentService {

    /**
     * 根据章节id 查询课程下的章节和内容信息
     * @param courseId
     * @return
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /**
     * 回显课程信息
     * @param courseId
     * @return
     */
    public Course findCourseByCourseId(int courseId);

    /**
     * 添加章节信息
     * @param section
     */
    public void saveSection(CourseSection section);

    /**
     * 修改章节信息
     * @param section
     */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     * @param status
     * @param id
     */
    public void updateStatus(int status,int id);

    /**
     * 添加课时信息
     * @param lesson
     */
    public void saveLesson(CourseLesson lesson);

    /**
     * 修改课时信息
     * @param lesson
     */
    public void updateLesson(CourseLesson lesson);
}
