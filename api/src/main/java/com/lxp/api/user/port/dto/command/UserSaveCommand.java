<<<<<<<< HEAD:user/src/main/java/com/lxp/user/application/command/UserSaveCommand.java
package com.lxp.user.application.command;
========
package com.lxp.api.user.port.dto.command;
>>>>>>>> f5a2578 (hotfix):api/src/main/java/com/lxp/api/user/port/dto/command/UserSaveCommand.java

import com.lxp.common.application.cqrs.Command;

import java.util.List;
import java.util.UUID;

public record UserSaveCommand(
    UUID userId,
    String name,
    String email,
    String role,

    List<Long> Tags,
    String level,
    String job
) implements Command {
}
