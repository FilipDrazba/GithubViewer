package drazba.filip.githubviewer.dtos.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawGithubBranchDto(
        @JsonProperty("name")
        String name,

        @JsonProperty("commit")
        RawGithubCommitDto commit
) {
}
