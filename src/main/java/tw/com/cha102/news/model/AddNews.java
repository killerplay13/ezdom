package tw.com.cha102.news.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
//為了新增&修改最新消息而創建的class，目的是只讓前端使用者去輸入(更改)消息標題&消息內容&重要性這三個欄位而已
public class AddNews {
    @NotNull
    @Column(name = "IMPORTANCE")
    private Integer importance;

    @NotNull
    @Column(name = "NEWS_TITLE")
    private String newsTitle;

    @NotNull
    @Column(name = "NEWS_CONTENT")
    private String newsContent;

    // 省略其他可能的屬性...

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}