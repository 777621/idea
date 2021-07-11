package com.lagou.entity;

import java.io.Serializable;

public class CourseTeacherVo implements Serializable {

    private Course course;

    private Teacher teacher;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
