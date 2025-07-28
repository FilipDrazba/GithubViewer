package drazba.filip.githubviewer.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.util.List;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseDto(
        String repositoryName,

        String ownerLogin,

        List<BranchResponseDto> branches
) {
}
