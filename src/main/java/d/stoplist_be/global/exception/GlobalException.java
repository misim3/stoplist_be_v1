package d.stoplist_be.global.exception;

import d.stoplist_be.global.enums.ErrorCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

  private final ErrorCode errorCode;

  public GlobalException(ErrorCode errorCode) {
    super(errorCode.getDetail());
    this.errorCode = errorCode;
  }
}

