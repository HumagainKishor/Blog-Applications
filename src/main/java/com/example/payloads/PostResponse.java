package com.example.payloads;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;
@Serdeable
@Introspected

public class PostResponse {
    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalElement;
    private int totalELements;
    private boolean lastpage;

    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public int getTotalELements() {
        return totalELements;
    }

    public void setTotalELements(int totalELements) {
        this.totalELements = totalELements;
    }

    public boolean isLastpage() {
        return lastpage;
    }

    public void setLastpage(boolean lastpage) {
        this.lastpage = lastpage;
    }
}
