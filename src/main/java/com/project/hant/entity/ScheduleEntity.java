package com.project.hant.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "schedule")
public class ScheduleEntity extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "time_schedule", nullable = false)
    private String timeSchedule;

    @Column(name = "day_schedule", nullable = false)
    private String daySchedule;

    @Column(name = "content_schedule", nullable = false)
    private String contentSchedule;
}
