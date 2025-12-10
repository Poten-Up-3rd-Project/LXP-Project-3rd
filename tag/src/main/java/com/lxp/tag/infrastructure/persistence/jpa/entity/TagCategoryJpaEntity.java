package com.lxp.tag.infrastructure.persistence.jpa.entity;

import com.lxp.tag.application.port.dto.CategoryView;
import com.lxp.tag.infrastructure.persistence.jpa.enums.TagState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "tag_category")
@NoArgsConstructor
public class TagCategoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TagState state;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private List<TagJpaEntity> tags = new ArrayList<>();


    public static CategoryView from(TagCategoryJpaEntity entity) {
        return new CategoryView(
                entity.id,
                entity.name,
                entity.state.toString(),
                entity.tags.stream().map(TagJpaEntity::from).toList()
        );
    }
}
