package tads.jwtConfiguration;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import tads.util.JwTokenHelper;

@Provider
@JsonTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JsTokenFilterNeeded implements ContainerRequestFilter {

    private static final String PRIVATE_KEY = "privateKey";
    private JwTokenHelper jwTokenHelper = JwTokenHelper.getInstance();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();
		
        try {
            jwTokenHelper.claimKey(token);
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException)
                throw new WebApplicationException(getUnAuthorizeResponse(PRIVATE_KEY + " is expired."));
            else if (e instanceof MalformedJwtException)
                throw new WebApplicationException(getUnAuthorizeResponse(PRIVATE_KEY + " is not correct."));
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private Response getUnAuthorizeResponse(String message) {
        return Response.ok(message)
                .status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}