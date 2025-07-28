package drazba.filip.githubviewer.controllers;

import drazba.filip.githubviewer.dtos.ResponseDto;
import drazba.filip.githubviewer.services.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/github")
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/users/{username}/repos")
    public ResponseEntity<List<ResponseDto>> getUserRepositories(@PathVariable String username) {
        return ResponseEntity.ok(githubService.getNonForkRepositories(username));
    }
}
