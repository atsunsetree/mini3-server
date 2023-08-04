package com.fc.mini3server._core.handler;

public interface Message {
    String INVALID_ID_PARAMETER = "해당 아이디가 존재하지 않습니다.";
    String INVALID_USER_STATUS_NOT_APPROVED = "미승인 상태의 계정만 승인 가능합니다.";
    String INVALID_USER_STATUS_APPROVED = "재직 중인 계정만 변경 가능합니다.";
    String INVALID_DATE_FORMAT_PARAMETER = "올바른 날짜 형식이 아닙니다.";
}
