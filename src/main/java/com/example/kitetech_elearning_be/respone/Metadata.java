package com.example.kitetech_elearning_be.respone;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Metadata {
    private int count;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private boolean hasMore;
}
