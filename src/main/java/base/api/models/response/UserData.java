package base.api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
    int id;
    String email;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    URL avatar;
}