package tw.com.cha102.forum.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostDTO {
    @NotNull
    private String forumPostContent;
    @NotNull
    private String forumPostTitle;
    @NotNull
    private Integer forumPostType;
}
