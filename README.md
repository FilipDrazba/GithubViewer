# GithubViewer

**GithubViewer** is a Spring Boot-based REST API application that allows you to retrieve public GitHub repositories of a given user. The application filters out forked repositories and returns only original ones, along with detailed branch information.

## Features

- Retrieve a list of a GitHub user's repositories (excluding forks)
- For each repository:
    - Repository name
    - Owner's login
    - List of branches
        - Branch name
        - Last commit SHA
- Return a 404 error if the user does not exist

## Tech Stack

- Java 21
- Spring Boot 3.5
- REST API
- `RestTemplate` for GitHub API communication

### Getting Started
1. Clone the repository:
```bash
    git clone https://github.com/filipdrazba/GithubViewer.git
    cd GithubViewer
```
2. Build the project using Maven:
```bash
    ./mvnw spring-boot:run
```
3. Test the API:
```bash
    curl http://localhost:8080/api/github/users/octocat/repos
```

## API Specification

### GET `/api/github/users/{username}/repos`

#### Successful response (HTTP 200)

```json
[
  {
    "repositoryName": "sample-repo",
    "ownerLogin": "john-doe",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "a1b2c3d4e5..."
      },
      {
        "name": "dev",
        "lastCommitSha": "f6g7h8i9j0..."
      }
    ]
  }
]
```

#### Error response (HTTP 404)
```json
{
    "status": 404,
    "message": "User not found: non-existing-user-login"
}
```

## Testing

The application includes a single integration test that covers the happy path scenario using real GitHub data.  
The test is written without mocks to reflect the real behavior of the system.

## Author
Filip Drażba

GitHub: [https://github.com/FilipDrazba](https://github.com/FilipDrazba)
