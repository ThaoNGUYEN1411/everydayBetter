package co.simplon.everydaybetterbusiness.dtos;

import java.util.List;

public class ApiErrorResponse {
    private List<ErrorDetail> errors;

    public static class ErrorDetail {
        private String code;
        private String field;
        private String message;

        public String getCode() {
            return code;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setField(String field) {
            this.field = field;
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
}
/*
{
    "errors": [
        {
            "code": "UniqueEmail",
            "field": "email",
            "message": "Email is already exists"
        },
        {
            "code": "Size",
            "field": "nickname",
            "message": "la taille doit être comprise entre 0 et 5"
        }
    ]
}
 */
