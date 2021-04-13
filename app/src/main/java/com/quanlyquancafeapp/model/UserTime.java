package com.quanlyquancafeapp.model;

public class UserTime extends User{
    private Long idUserTime;
    private String timeStart;
    private String timeEnd;

    public Long getIdUserTime() {
        return idUserTime;
    }
    public void setIdUserTime(Long idUserTime) {
        this.idUserTime = idUserTime;
    }
    public String getTimeStart() {
        return timeStart;
    }
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }
    public String getTimeEnd() {
        return timeEnd;
    }
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
