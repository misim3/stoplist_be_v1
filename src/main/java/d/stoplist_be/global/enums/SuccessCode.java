package d.stoplist_be.global.enums;

import lombok.Generated;
import org.springframework.http.HttpStatus;

public enum SuccessCode {
    // USER
    USER_SIGNUP_SUCCESS(HttpStatus.OK, "회원가입에 성공했습니다."),
    USER_LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공했습니다."),
;

    private final HttpStatus httpStatus;
    private final String detail;

    private SuccessCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    @Generated
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Generated
    public String getDetail() {
        return this.detail;
    }
}
