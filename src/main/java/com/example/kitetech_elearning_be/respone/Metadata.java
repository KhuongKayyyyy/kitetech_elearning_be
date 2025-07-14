package com.example.kitetech_elearning_be.respone;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Metadata {
    private int total;
    private int count;
    private int page;
    private int size;
    private boolean hasMore;
}
