package com.example.approval.review.repository;

import com.example.approval.review.entity.Review;

public interface ReviewRepository {

    Review findOne(long id);

    void create(Review review);

    void update(Review review);

}
