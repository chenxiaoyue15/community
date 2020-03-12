package student.community.community.model;

public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCout;
    private Integer commentCout;
    private Integer likeCout;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getViewCout() {
        return viewCout;
    }

    public void setViewCout(Integer viewCout) {
        this.viewCout = viewCout;
    }

    public Integer getCommentCout() {
        return commentCout;
    }

    public void setCommentCout(Integer commentCout) {
        this.commentCout = commentCout;
    }

    public Integer getLikeCout() {
        return likeCout;
    }

    public void setLikeCout(Integer likeCout) {
        this.likeCout = likeCout;
    }
}
