package com.isolaja.mobileapp.ui.model.request;

import lombok.Data;

import java.util.List;

@Data
public class UserDetailsRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressRequestModel> addresses;

}
