package drazba.filip.githubviewer.controllers;

import drazba.filip.githubviewer.dtos.BranchResponseDto;
import drazba.filip.githubviewer.dtos.ResponseDto;
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
class AllInOneGithubControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void fullGithubApiIntegrationTest() {
        String username = "octocat";
        String url = "/api/github/users/" + username + "/repos";
        ResponseEntity<ResponseDto[]> response = restTemplate.getForEntity(url, ResponseDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected HTTP 200 OK but got " + response.getStatusCode() + ". Check if the user exists and API is available.");

        ResponseDto[] repos = response.getBody();
        assertNotNull(repos, "Response body should not be null");
        assertTrue(repos.length > 0, "Expected at least one non-fork repository for user " + username);

        for (ResponseDto repo : repos) {
            String repoName = repo.repositoryName();
            assertNotNull(repoName, "Repository name is null");
            assertFalse(repoName.isBlank(), "Repository name is blank");

            assertEquals(username, repo.ownerLogin(),
                    "Owner login mismatch: expected '" + username + "', but was '" + repo.ownerLogin() + "'");

            List<BranchResponseDto> branches = repo.branches();
            assertNotNull(branches, "Branches list is null for repository " + repoName);
            assertFalse(branches.isEmpty(), "Branches list is empty for repository " + repoName);

            Set<String> seenNames = new HashSet<>();
            for (BranchResponseDto branch : branches) {
                String name = branch.name();
                assertNotNull(name, "Branch name is null in repository " + repoName);
                assertFalse(name.isBlank(), "Branch name is blank in repository " + repoName);
                assertTrue(seenNames.add(name), "Duplicate branch name found: '" + name + "' in repository " + repoName);

                String sha = branch.lastCommitSha();
                assertNotNull(sha, "Last commit SHA is null in branch '" + name + "' of repository " + repoName);
                assertTrue(sha.matches("[0-9a-f]{40}"),
                        "Invalid SHA format '" + sha + "' in branch '" + name + "' of repository " + repoName);
            }
        }
    }
}
