package com.gft.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReviewStatusCount {
    private int toBeReviewed;
    private int inReview;
    private int  reviewed;
}
