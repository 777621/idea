package com.lagou.dao;

import com.lagou.entity.Course;
import com.lagou.entity.CourseLesson;
import com.lagou.entity.CourseSection;

import java.util.List;

/**
 * 课程内容管理  dao
 */
public interface CourseContentMapper {

    /**
     * 根据章节id查询章节信息 课时信息
     * @param courseId
     * @return
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /**
     * 回显章节对应的课程名称
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
     * @param section
     */
    public void updateStatus(CourseSection section);

    /**
     * 添加课时信息
     * @param lesson
     */
    public void saveLesson(CourseLesson lesson);
}
