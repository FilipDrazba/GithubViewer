package drazba.filip.githubviewer.dtos.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawGithubCommitDto(
        @JsonProperty("sha")
        String sha
) {
}