package co.simplon.everydaybetterbusiness.dtos;

import java.util.List;

public class ApiErrorResponse {
    private List<ErrorDetail> errors;

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetail> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse{" +
               "errors=" + errors +
               '}';
    }

    public static class ErrorDetail {
        private String code;
        private String field;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ErrorDetail{" +
                   "code='" + code + '\'' +
                   ", field='" + field + '\'' +
                   ", message='" + message + '\'' +
                   '}';
        }
    }
}
