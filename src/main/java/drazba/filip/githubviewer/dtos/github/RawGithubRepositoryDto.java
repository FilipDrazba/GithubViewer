package drazba.filip.githubviewer.dtos.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.util.List;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record RawGithubRepositoryDto(
        String name,
        RawGithubOwnerDto owner,
        List<RawGithubBranchDto> branches,
        boolean fork
) {
}
