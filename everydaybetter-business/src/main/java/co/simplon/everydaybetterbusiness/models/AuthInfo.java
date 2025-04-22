package co.simplon.everydaybetterbusiness.models;

import java.util.List;

public record AuthInfo(String nickname, List<String> roles) {
    @Override
    public String toString() {
        return "{token=[PROTECTED]" + ", roles=" + roles + "}";
    }
}
