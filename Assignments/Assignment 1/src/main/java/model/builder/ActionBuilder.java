package model.builder;

import model.Action;

import java.util.Date;

public class ActionBuilder {
    private Long id;
    private Long userId;
    private String action;
    private Date date;

    public ActionBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ActionBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public ActionBuilder setAction(String action) {
        this.action = action;
        return this;
    }

    public ActionBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public Action createAction() {
        return new Action(id, userId, action, date);
    }
}