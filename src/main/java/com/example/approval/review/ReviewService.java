package com.example.approval.review;

import com.example.approval.review.entity.Review;

public interface ReviewService {

    void review(Review review);

    void reject(Review review);

}
