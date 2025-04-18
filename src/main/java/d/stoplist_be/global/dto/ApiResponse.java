package d.stoplist_be.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final int statusCode;

    private final String message;

    private final T data;

    @Generated
    public int getStatus() {
        return this.statusCode;
    }

    @Generated
    public String getMessage() {
        return this.message;
    }

    @Generated
    public T getData() {
        return this.data;
    }

    @Generated
    public ApiResponse(final int statusCode, final String message, final T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
