package com.main.project.entity;


import com.main.project.entity.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@Getter
public class Multi_ResponseDTOwithPageInfo<T> {
    private List<T> data;
    private PageInfo pageInfo;


    public Multi_ResponseDTOwithPageInfo(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber()+1, page.getSize(), page.getTotalElements(), page.getTotalPages()) ;
    }
}