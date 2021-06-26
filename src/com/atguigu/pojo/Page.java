package com.atguigu.pojo;

import java.util.List;

/**
 * @author LiuJun
 * @create 2021-06-16-18:58
 * @description 分页对象模型Page类
 */

/**
 * page是分页的模型对象
 *
 * @param <T> 具体的javaBean类
 */
public class Page<T> {

    //每页显示数量
    public static final Integer PAGE_SIZE = 4;

    //当前页码
    private Integer pageNo;

    //总页码
    private Integer pageTotal;

    //每页显示数量
    private Integer pageSize = PAGE_SIZE;

    //总记录数
    private Integer pageTotalCount;

    //当前页数据
    private List<T> items;

    //分页条的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        //数据边界的有效检查，设置当前页码
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > this.pageTotal) {
            pageNo = this.pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}