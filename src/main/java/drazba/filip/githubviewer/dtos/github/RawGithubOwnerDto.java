package drazba.filip.githubviewer.dtos.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawGithubOwnerDto(
        String login
) {
}
