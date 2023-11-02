package toyproject.shop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private CustomErrorCode customErrorCode;
//    private HttpStatus status;
    private String message;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getMessage());
        this.customErrorCode = customErrorCode;
//        this.status = customErrorCode.getStatus();
        this.message = customErrorCode.getMessage();
    }
}
