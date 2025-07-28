package drazba.filip.githubviewer.services;

import drazba.filip.githubviewer.dtos.BranchResponseDto;
import drazba.filip.githubviewer.dtos.ResponseDto;
import drazba.filip.githubviewer.dtos.github.RawGithubBranchDto;
import drazba.filip.githubviewer.dtos.github.RawGithubRepositoryDto;
import drazba.filip.githubviewer.exceptions.UserNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GithubService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GITHUB_API = "https://api.github.com";

    public List<ResponseDto> getNonForkRepositories(String username) {
        List<RawGithubRepositoryDto> repos = fetchUserRepositories(username);
        return repos.stream()
                .filter(repo -> !repo.fork())
                .map(repo -> mapToResponseDto(repo, username))
                .toList();
    }

    private List<RawGithubRepositoryDto> fetchUserRepositories(String username) {
        try {
            return fetchFromGithubApi(
                    "/users/" + username + "/repos",
                    new ParameterizedTypeReference<>() {}
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException("User not found: " + username);
        }
    }

    private List<RawGithubBranchDto> fetchBranches(String owner, String repoName) {
        return fetchFromGithubApi(
                "/repos/" + owner + "/" + repoName + "/branches",
                new ParameterizedTypeReference<>() {}
        );
    }

    private <T> List<T> fetchFromGithubApi(String url,
                                           ParameterizedTypeReference<List<T>> typeReference) {
        URI uri = URI.create(GITHUB_API + url);
        RequestEntity<Void> request = RequestEntity
                .get(uri)
                .header(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
                .build();

        return Optional.ofNullable(restTemplate.exchange(
                request,
                typeReference
        ).getBody()).orElse(Collections.emptyList());
    }

    private ResponseDto mapToResponseDto(RawGithubRepositoryDto repo, String username) {
        List<BranchResponseDto> branches = fetchBranches(username, repo.name()).stream()
                .map(branch -> BranchResponseDto.builder()
                        .name(branch.name())
                        .lastCommitSha(branch.commit().sha())
                        .build())
                .toList();

        return ResponseDto.builder()
                .repositoryName(repo.name())
                .ownerLogin(repo.owner().login())
                .branches(branches)
                .build();
    }
}
