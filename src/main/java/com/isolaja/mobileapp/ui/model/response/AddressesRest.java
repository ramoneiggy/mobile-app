package com.isolaja.mobileapp.ui.model.response;

import lombok.Data;

@Data
public class AddressesRest {
    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;
}
