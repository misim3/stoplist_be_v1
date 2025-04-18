package d.stoplist_be.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final int status;

    private final String message;

    private final T data;

    @Generated
    public int getStatus() {
        return this.status;
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
    public ApiResponse(final int status, final String message, final T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
