package com.main.project.thumbUp.dto.service;

import java.util.List;

public interface ThumbUpService {
    public boolean createThumbUp(long userId, long reviewId);
    public List<String> count(long reviewId, long userId);
    public void deleteThumbUp(long thumbUpId, long userId);
}
