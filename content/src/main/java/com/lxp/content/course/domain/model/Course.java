package com.lxp.content.course.domain.model;

import com.lxp.common.domain.event.AggregateRoot;
import com.lxp.content.course.domain.model.collection.CourseSections;
import com.lxp.content.course.domain.model.collection.CourseTags;
import com.lxp.content.course.domain.model.enums.CourseDifficulty;
import com.lxp.content.course.domain.model.id.*;
import com.lxp.content.course.domain.model.vo.duration.CourseDuration;
import com.lxp.content.course.domain.model.vo.duration.LectureDuration;

import java.util.Objects;

public class Course extends AggregateRoot {
    private Long id;
    private final CourseUUID uuid;
    private final InstructorUUID instructorUUID;
    private String thumbnailUrl;
    private String title;
    private String description;
    private CourseSections sections;
    private CourseDifficulty difficulty;
    private CourseTags tags;

    private Course(
            Long id,
            CourseUUID uuid,
            InstructorUUID instructorUUID,
            String thumbnailUrl,
            String title,
            String description,
            CourseDifficulty difficulty,
            CourseSections sections,
            CourseTags tags
    ) {
        this.id = id;
        this.uuid = uuid;
        this.instructorUUID = Objects.requireNonNull(instructorUUID);
        this.thumbnailUrl = thumbnailUrl;
        this.title = Objects.requireNonNull(title);
        this.description = description;
        this.difficulty = Objects.requireNonNull(difficulty);
        this.sections = Objects.requireNonNull(sections);
        this.tags = tags;
    }

    public static Course create(
            InstructorUUID instructorUUID,
            CourseUUID uuid,
            String thumbnailUrl,
            String title,
            String description,
            CourseDifficulty difficulty,
            CourseSections sections,
            CourseTags tags)
    {
        return new Course(
                null,
                uuid,
                instructorUUID,
                thumbnailUrl,
                title,
                description,
                difficulty,
                sections,
                tags
        );
    }

    public static Course reconstruct(
            Long id,
            CourseUUID uuid,
            InstructorUUID instructorUUID,
            String thumbnailUrl,
            String title,
            String description,
            CourseDifficulty difficulty,
            CourseSections sections,
            CourseTags tags
    ) {
        return new Course(id, uuid, instructorUUID, thumbnailUrl, title, description, difficulty, sections, tags);
    }

    //setters
    public void rename(String title) {
        this.title = Objects.requireNonNull(title,"title cannot be null");
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeDifficulty(CourseDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void changeThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    //section
    public void addSection(SectionUUID uuid, String title) {
        this.sections = sections.addSection(uuid, title);
    }

    public void removeSection(SectionUUID uuid) {
        this.sections = sections.removeSection(uuid);
    }

    public void renameSection(SectionUUID uuid, String title) {
        this.sections = sections.renameSection(uuid, title);
    }

    public void reorderSection(SectionUUID uuid, int newOrder) {
        this.sections = sections.reorderSection(uuid, newOrder);
    }

    //lecture
    public void addLecture(
            SectionUUID sectionUUID,
            LectureUUID lectureUUID,
            String title,
            LectureDuration duration,
            String videoUrl
    ) {
        this.sections = sections.addLecture(sectionUUID, lectureUUID, title, duration, videoUrl);
    }

    public void removeLecture(SectionUUID sectionUUID, LectureUUID lectureUUID) {
        this.sections = sections.removeLecture(sectionUUID, lectureUUID);
    }

    public void renameLecture(SectionUUID sectionUUID, LectureUUID lectureUUID, String newTitle) {
        this.sections = sections.renameLecture(sectionUUID, lectureUUID, newTitle);
    }

    public void changeLectureVideoUrl(SectionUUID sectionUUID, LectureUUID lectureUUID, String url) {
        this.sections = sections.changeLectureVideoUrl(sectionUUID, lectureUUID, url);
    }

    // tag
    public void addTag(TagId tag) {
            this.tags = this.tags.add(tag);
    }

    public void removeTag(TagId tag) {
        this.tags = this.tags.remove(tag);
    }

    public boolean hasTag(TagId tag) {
        return this.tags.contains(tag);
    }


    public CourseUUID uuid() { return uuid; }
    public Long id() { return id; }
    public String title() { return title; }
    public String description() { return description; }
    public CourseDifficulty difficulty() { return difficulty; }
    public CourseSections sections() { return sections; }
    public String thumbnailUrl() { return thumbnailUrl; }
    public InstructorUUID instructorUUID() { return instructorUUID; }

    public CourseDuration totalDuration() {
        return sections.totalDuration();
    }

}
