package tw.com.cha102.forum.model.entity;

import lombok.*;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;
//import java.util.List;


@Entity
@Data
@Table(name="FORUMCATEGORY",catalog ="cha102g4_test")
public class ForumCategoryVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORUMCATEGORY_ID")
    private Integer forumCategoryId;

    @Column(name = "FORUMCATEGORY_NAME")
    private String forumCategoryName;


//     @OneToMany(mappedBy = "forumCategory")
//     private List<ForumPostVO> forumPosts;
}

