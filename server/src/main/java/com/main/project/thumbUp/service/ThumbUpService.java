package com.main.project.thumbUp.service;

import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.user.entity.WebUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ThumbUpService {
    public boolean createThumbUp(WebUser user, long reviewId);
    public List<String> count(long reviewId);
    public void deleteThumbUp(long thumbUpId, long userId);
    public Page<ThumbUp> findUserLike(WebUser user, int page);
}
