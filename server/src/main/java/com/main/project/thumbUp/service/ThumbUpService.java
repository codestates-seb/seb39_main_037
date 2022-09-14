package com.main.project.thumbUp.service;

import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.thumbUp.repository.ThumbUpRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ThumbUpService {

    private final ThumbUpRepository thumbUpRepository;

    public ThumbUpService(ThumbUpRepository thumbUpRepository) {
        this.thumbUpRepository = thumbUpRepository;
    }

    public ThumbUp createThumbUp(String input) {

        return null;
    }

    public List<String> count(long reviewId, long userId) {

        return null;
    }

    public void deleteThumbUp(long thumbUpId) {

    }
}
