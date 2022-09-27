package com.main.project.thumbUp.service;

import com.main.project.user.entity.WebUser;

import java.util.List;

public interface ThumbUpService {
    public boolean createThumbUp(WebUser user, long reviewId);
    public List<String> count(long reviewId);
    public void deleteThumbUp(WebUser user, long reviewId);
}
