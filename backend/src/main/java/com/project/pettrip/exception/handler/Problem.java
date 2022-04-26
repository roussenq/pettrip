package com.project.pettrip.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;
    private String dateAndTime;
    private String title;

    public Problem(Integer status, String dateAndTime, String title) {
        this.status = status;
        this.dateAndTime = dateAndTime;
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
