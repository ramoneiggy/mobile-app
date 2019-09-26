package com.isolaja.mobileapp.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"userDetails"})
public class AddressDTO {
    private long id;
    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;
    @JsonIgnore
    private UserDto userDetails;
}
