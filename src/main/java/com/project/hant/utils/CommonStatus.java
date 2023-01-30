package com.project.hant.utils;

import lombok.Getter;

@Getter
public enum CommonStatus {

    DELETED("0", 0L), NOT_DELETED("1", 1L),
    WORKING("1", 1L), NOT_WORKING("0", 0L);
    CommonStatus(String strStatus, Long intStatus){
        this.strStatus = strStatus;
        this.intStatus = intStatus;
    }
    private String strStatus;
    private Long intStatus;

}
