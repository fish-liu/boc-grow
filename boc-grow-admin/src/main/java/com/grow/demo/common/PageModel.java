package com.grow.demo.common;

import java.util.List;

/**
 *
 * @author yw.zhang02
 * @date 2018/1/2 下午6:05
 */
public class PageModel<T> {

    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 每页数据列表
     */
    private List<T> list;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 当前第几页
     */
    private Integer current = 1;

    /**
     * 每页多少条
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    public PageModel(){

    }

    public PageModel(List<T> list, Integer total, Integer current){
        this.list = list;
        this.total = total;
        this.current = current;

        this.totalPages =  (int)Math.ceil((double)total/pageSize);
    }

    public PageModel(List<T> list, Integer total, Integer current, Integer pageSize){
        this.list = list;
        this.total = total;
        this.current = current;
        this.pageSize = pageSize;

        this.totalPages =  (int)Math.ceil((double)total/pageSize);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
