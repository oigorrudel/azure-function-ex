package br.xksoberbado;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerExamples {

    @FunctionName("HttpTriggerGetExample")
    public HttpResponseMessage get(@HttpTrigger(name = "req", methods = HttpMethod.GET, authLevel = AuthorizationLevel.FUNCTION) final HttpRequestMessage<Optional<String>> request,
                                   final ExecutionContext context) {
        context.getLogger().info("Received. [method: " + request.getHttpMethod() + "]");

        String name = request.getQueryParameters().get("name");

        if (name == null) {
            return request
                .createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("Please pass a name on the query string or in the request body")
                .build();
        } else {
            return request
                .createResponseBuilder(HttpStatus.OK)
                .body("Hello, " + name)
                .build();
        }
    }

    @FunctionName("HttpTriggerPostExample")
    public HttpResponseMessage post(@HttpTrigger(name = "req", methods = HttpMethod.POST, authLevel = AuthorizationLevel.FUNCTION) final HttpRequestMessage<PersonBody> request,
                                    final ExecutionContext context) {
        context.getLogger().info("Received. [method: " + request.getHttpMethod() + "]");

        PersonBody body = request.getBody();

        return request
            .createResponseBuilder(HttpStatus.CREATED)
            .body(body)
            .build();
    }

    private static class PersonBody {
        private String name;
        private String gender;

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public void setGender(final String gender) {
            this.gender = gender;
        }
    }
}
