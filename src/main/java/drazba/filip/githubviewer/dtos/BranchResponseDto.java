package drazba.filip.githubviewer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record BranchResponseDto(
        String name,

        String lastCommitSha
) {
}
