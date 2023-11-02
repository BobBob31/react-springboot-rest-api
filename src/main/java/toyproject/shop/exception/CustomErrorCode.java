package toyproject.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    // 400 BAD REQUEST : 잘못된 요청
    MISMATCH_PW_ERROR(/*BAD_REQUEST, */"비밀번호가 일치하지 않습니다."),

    // 404 NOT FOUND
    EMAIL_NOT_FOUND_ERROR(/*NOT_FOUND, */"해당 이메일을 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND_ERROR(/*NOT_FOUND, */"해당 상품을 찾을 수 없습니다."),

    // 409 CONFLICT
    DUPLICATE_EMAIL_ERROR(/*CONFLICT, */"동일한 이메일이 이미 존재합니다."),
    DUPLICATE_PRODUCT_ERROR(/*CONFLICT, */"동일한 상품이 이미 존재합니다.");

//    private final HttpStatus status;
    private final String message;


}
