package model;

import java.util.Date;

/**
 * Created by Paul on 01/04/2018.
 */
public class Action {

    private Long id;
    private Long userId;
    private String action;
    private Date date;


    public Action(Long id, Long userId, String action, Date date) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.date = date;
    }

    public Action() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
