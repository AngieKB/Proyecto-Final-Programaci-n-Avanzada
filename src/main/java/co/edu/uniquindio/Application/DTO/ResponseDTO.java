package co.edu.uniquindio.Application.DTO;

public record ResponseDTO<T>(
        boolean error,
        T content
) {
}
