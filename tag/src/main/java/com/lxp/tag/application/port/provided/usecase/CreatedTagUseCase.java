package com.lxp.tag.application.port.provided.usecase;

import com.lxp.api.tag.port.dto.command.CreatedTagCommand;
import com.lxp.api.tag.port.dto.result.TagResult;
import com.lxp.common.application.cqrs.CommandWithResultHandler;

public interface CreatedTagUseCase extends CommandWithResultHandler<CreatedTagCommand, TagResult> {
}
