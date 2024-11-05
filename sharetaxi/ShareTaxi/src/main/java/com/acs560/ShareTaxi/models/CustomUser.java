package com.acs560.ShareTaxi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomUser {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("role")
    private String role;

    public CustomUser(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
