package Springboot.Api.Exception;

import java.time.LocalDateTime;

public class SuccessResponse {
    private int status;
    private LocalDateTime timestamp;
    private String message;

    public SuccessResponse(int value, LocalDateTime now, String message) {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SuccessResponse(int status) {
        this.status = status;
    }

    public SuccessResponse(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public SuccessResponse(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SuccessResponse{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
