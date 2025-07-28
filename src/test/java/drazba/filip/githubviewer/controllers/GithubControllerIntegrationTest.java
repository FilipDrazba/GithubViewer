/*
package drazba.filip.githubviewer.controllers;

import drazba.filip.githubviewer.dtos.BranchResponseDto;
import drazba.filip.githubviewer.dtos.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseDto[] repos;
    private final String username = "octocat";

    @BeforeEach
    void setup() {
        String url = "/api/github/users/" + username + "/repos";
        ResponseEntity<ResponseDto[]> response = restTemplate.getForEntity(url, ResponseDto[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected HTTP 200 OK but got " + response.getStatusCode() + ". Check if the user exists and API is available.");
        repos = response.getBody();
        assertNotNull(repos, "Response body should not be null");
        assertTrue(repos.length > 0, "Expected at least one non-fork repository for user " + username);
    }

    @Test
    void shouldReturnRepositoriesForUser() {
        assertNotNull(repos, "Repositories list is null");
        assertTrue(repos.length > 0, "Repositories list is empty");
    }

    @Test
    void repositoryNameShouldBeNotNullOrBlank() {
        for (ResponseDto repo : repos) {
            String repoName = repo.repositoryName();
            assertNotNull(repoName, "Repository name is null");
            assertFalse(repoName.isBlank(), "Repository name is blank");
        }
    }

    @Test
    void ownerLoginShouldMatchRequestedUsername() {
        for (ResponseDto repo : repos) {
            assertEquals(username, repo.ownerLogin(),
                    "Owner login mismatch: expected '" + username + "', but was '" + repo.ownerLogin() + "'");
        }
    }

    @Test
    void branchesShouldNotBeNullOrEmpty() {
        for (ResponseDto repo : repos) {
            List<BranchResponseDto> branches = repo.branches();
            assertNotNull(branches, "Branches list is null for repository " + repo.repositoryName());
            assertFalse(branches.isEmpty(), "Branches list is empty for repository " + repo.repositoryName());
        }
    }

    @Test
    void branchNamesShouldBeUniquePerRepository() {
        for (ResponseDto repo : repos) {
            List<BranchResponseDto> branches = repo.branches();
            Set<String> seenNames = new HashSet<>();
            for (BranchResponseDto branch : branches) {
                String name = branch.name();
                assertTrue(seenNames.add(name), "Duplicate branch name found: '" + name + "' in repository " + repo.repositoryName());
            }
        }
    }

    @Test
    void branchNameShouldBeNotNullOrBlank() {
        for (ResponseDto repo : repos) {
            for (BranchResponseDto branch : repo.branches()) {
                String name = branch.name();
                assertNotNull(name, "Branch name is null in repository " + repo.repositoryName());
                assertFalse(name.isBlank(), "Branch name is blank in repository " + repo.repositoryName());
            }
        }
    }

    @Test
    void lastCommitShaShouldBeValid() {
        for (ResponseDto repo : repos) {
            for (BranchResponseDto branch : repo.branches()) {
                String sha = branch.lastCommitSha();
                assertNotNull(sha, "Last commit SHA is null in branch '" + branch.name() + "' of repository " + repo.repositoryName());
                assertTrue(sha.matches("[0-9a-f]{40}"), "Invalid SHA format '" + sha + "' in branch '" + branch.name() + "' of repository " + repo.repositoryName());
            }
        }
    }
}
 */
