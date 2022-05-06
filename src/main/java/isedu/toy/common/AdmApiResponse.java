package isedu.toy.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdmApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public static <T> AdmApiResponse<T> ok(T data) {
        return AdmApiResponse.<T>builder().code("200").message("OK").data(data).build();
    }

    public static <T> AdmApiResponse<T> ok() {
        return AdmApiResponse.<T>builder()
                .code("200")
                .message("OK")
                .build();
    }

    public static <T> AdmApiResponse<T> notFound() {
        return AdmApiResponse.<T>builder()
                .code("404")
                .message("데이터 없음")
                .build();
    }

    public static <T> AdmApiResponse<T> forbidden() {
        return AdmApiResponse.<T>builder()
                .code("403")
                .message("잘못된 요청")
                .build();
    }

    public static <T> AdmApiResponse<T> badRequestWithData(T data) {
        return AdmApiResponse.<T>builder()
                .code("400")
                .message("잘못된 요청")
                .data(data)
                .build();
    }

    public static <T> AdmApiResponse<T> badRequest(String message) {
        return AdmApiResponse.<T>builder()
                .code("400")
                .message(message)
                .build();
    }

    public static AdmApiResponse<List<String>> validateErrors(List<String> errorCodes) {
        return AdmApiResponse.<List<String>>builder()
                .code("400")
                .message("검증에러")
                .data(errorCodes)
                .build();
    }

    public static AdmApiResponse<Object> conflict(String message) {
        return AdmApiResponse.<Object>builder()
                .code("409")
                .message(message)
                .build();
    }

    public static <T> AdmApiResponse<T> serverError(String message) {
        return AdmApiResponse.<T>builder()
                .code("500")
                .message(message)
                .build();
    }
}
