package co.simplon.everydaybetterbusiness.dtos;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.util.List;

public record JwtProvider(Algorithm algorithm, long exp, String issuer) {
    public String create(final String subject, final List<String> roles) {
        final Instant issuedAt = Instant.now();
        final JWTCreator.Builder builder = JWT.create()
                .withIssuedAt(issuedAt)
                .withSubject(subject)
                .withIssuer(issuer)
                .withClaim("roles", roles);

        if (exp > 0) {
            final Instant expiresAt = issuedAt.plusSeconds(exp);
            builder.withExpiresAt(expiresAt);
        }
        return builder.sign(algorithm);
    }
}
