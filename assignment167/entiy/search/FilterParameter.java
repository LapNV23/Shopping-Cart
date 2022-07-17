package com.example.assignment167.entiy.search;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterParameter {
    private String keyword;
    private int page;
    private int limit;
    private String userId;
    private int status;
}

