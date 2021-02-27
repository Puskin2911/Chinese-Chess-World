package com.doubleat.ccw.usermanagement.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -2086029685287023489L;

    @NotNull
    @JsonProperty(value = "username")
    private String username;

    @NotNull
    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "remember_me")
    private boolean rememberMe;

}
