package com.grow.demo.model;


import lombok.Builder;
import lombok.Data;

/**
 * 文章，标签关联表
 * @author liuxw
 * @since 1.0
 */
@Data
@Builder
public class ArticleTags {

    private Integer uid;

    private Integer tagId;

    private Integer artiId;

}
