package com.isolaja.mobileapp.ui.model.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(exclude = {"addresses"})
public class UserRest {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressesRest> addresses;
}
