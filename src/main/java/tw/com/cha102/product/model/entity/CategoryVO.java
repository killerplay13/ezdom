package tw.com.cha102.product.model.entity;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class CategoryVO extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "TYPE_NAME", nullable = false)
    private String typeName;
    public CategoryVO() {
    }

    public CategoryVO(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

