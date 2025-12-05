package com.lxp.tag.domain.service;

import com.lxp.tag.domain.exception.TagErrorCode;
import com.lxp.tag.domain.exception.TagException;
import com.lxp.tag.domain.model.Tag;
import com.lxp.tag.domain.model.TagCategory;
import com.lxp.tag.domain.model.vo.TagCategoryId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TagDomainServiceTest {

    @Nested
    @DisplayName("ChangeCategoryDomainService")
    class ChangeCategoryDomainServiceTest {
        private final ChangeCategoryDomainService changeCategoryDomainService = new ChangeCategoryDomainService();

        @Test
        @DisplayName("태그 카테고리와 태그가 모두 ACTIVE이면 태그의 카테고리가 변경된다")
        void createCategory_whenActive_thenTagCategoryChanged() {
            // given
            Tag tag = Tag.create(new TagCategoryId(1L), "docker");

            TagCategory tagCategory = TagCategory.create("데브옵스");
            TagCategoryId tagCategoryId = new TagCategoryId(2L);
            tagCategory.reconstruct(tagCategoryId);

            // when
            changeCategoryDomainService.changeCategory(tagCategory, tag);

            // then
            assertEquals(tagCategoryId, tag.tagCategoryId());
        }

        @Test
        @DisplayName("태그 카테고리가 INACTIVE이면 태그 카테고리 변경 시 예외가 발생한다")
        void changeCategory_whenCategoryInactive_thenTagException() {
            // given
            Tag tag = Tag.create(new TagCategoryId(1L), "docker");

            TagCategory tagCategory = TagCategory.create("데브옵스");
            TagCategoryId tagCategoryId = new TagCategoryId(2L);
            tagCategory.reconstruct(tagCategoryId);
            tagCategory.deactivate();

            // when
            TagException exception = assertThrows(TagException.class,
                    () -> changeCategoryDomainService.changeCategory(tagCategory, tag));

            // then
            assertEquals(TagErrorCode.INVALID_ASSIGN_TAG, exception.getErrorCode());
        }
    }

    @Nested
    @DisplayName("CreateTagDomainService")
    class CreateTagDomainServiceTest {
        private final CreateTagDomainService createTagDomainService = new CreateTagDomainService();

        @Test
        @DisplayName("태그 카테고리가 ACTIVE이면 Tag를 추가할 수 있다")
        void createTag_whenCategoryActive_thenTagCreated() {
            // given
            TagCategory tagCategory = TagCategory.create("데브옵스");
            TagCategoryId tagCategoryId = new TagCategoryId(2L);
            tagCategory.reconstruct(tagCategoryId);

            // when
            Tag tag = createTagDomainService.create(tagCategory, "docker");

            // then
            assertEquals(tagCategoryId, tag.tagCategoryId());
            assertEquals("docker", tag.name());
        }

        @Test
        @DisplayName("태그 카테고리가 INACTIVE이면 Tag를 추가할 수 있다")
        void createTag_whenCategoryInactive_thenTagException() {
            // given
            TagCategory tagCategory = TagCategory.create("데브옵스");
            TagCategoryId tagCategoryId = new TagCategoryId(2L);
            tagCategory.reconstruct(tagCategoryId);
            tagCategory.deactivate();

            // when
            TagException exception = assertThrows(TagException.class,
                    () -> createTagDomainService.create(tagCategory, "java"));

            // then
            assertEquals(TagErrorCode.FAIL_CREATE_TAG_IN_INVALID_CATEGORY, exception.getErrorCode());
        }
    }
}
