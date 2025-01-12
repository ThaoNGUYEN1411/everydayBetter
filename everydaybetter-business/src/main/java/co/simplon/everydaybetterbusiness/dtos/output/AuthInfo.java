package co.simplon.everydaybetterbusiness.dtos.output;

import java.util.List;

public record AuthInfo(String token, List<String> roles) {
    @Override
    public String toString() {
        return "{token=[PROTECTED]" + ", roles=" + roles + "}";
    }
}
