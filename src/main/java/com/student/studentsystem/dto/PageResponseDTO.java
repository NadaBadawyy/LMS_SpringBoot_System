package com.student.studentsystem.dto;

import java.util.List;

public class PageResponseDTO<T> {
    private List<T> content;
    private Integer page;
    private Integer totalPages;
    private Integer size;
    private long totalElements;

    public PageResponseDTO(
            List<T> content,
            int page,
            int size,
            long totalElements,
            int totalPages) {

        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }


}
