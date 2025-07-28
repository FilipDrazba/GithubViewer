package drazba.filip.githubviewer.dtos;

public record ErrorResponseDto(
        int status,
        String message
) {
}
