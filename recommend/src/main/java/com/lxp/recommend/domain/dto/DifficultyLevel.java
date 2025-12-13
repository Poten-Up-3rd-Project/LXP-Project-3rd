package com.lxp.recommend.domain.dto;

import com.lxp.common.enums.Level;

/**
 * 강좌 난이도 (Deprecated)
 *
 * @deprecated common.enums.Level 사용 권장
 *
 * 마이그레이션 가이드:
 * - DifficultyLevel.JUNIOR → Level.JUNIOR
 * - DifficultyLevel.valueOf("JUNIOR") → Level.valueOf("JUNIOR")
 *
 * 삭제 예정: v2.0
 */
@Deprecated(since = "1.1", forRemoval = true)
public enum DifficultyLevel {
    JUNIOR(Level.JUNIOR),
    MIDDLE(Level.MIDDLE),
    SENIOR(Level.SENIOR),
    EXPERT(Level.EXPERT);

    private final Level commonLevel;

    DifficultyLevel(Level commonLevel) {
        this.commonLevel = commonLevel;
    }

    /**
     * common.Level로 변환
     */
    public Level toCommonLevel() {
        return commonLevel;
    }

    /**
     * common.Level에서 변환
     */
    public static DifficultyLevel fromCommonLevel(Level level) {
        return switch (level) {
            case JUNIOR -> JUNIOR;
            case MIDDLE -> MIDDLE;
            case SENIOR -> SENIOR;
            case EXPERT -> EXPERT;
        };
    }

    /**
     * String에서 변환 (common.Level 우선)
     */
    public static DifficultyLevel valueOf(String name) {
        return fromCommonLevel(Level.valueOf(name));
    }
}
