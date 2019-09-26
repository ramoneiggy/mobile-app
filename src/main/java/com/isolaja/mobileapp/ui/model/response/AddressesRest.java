package com.isolaja.mobileapp.ui.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;


@EqualsAndHashCode(callSuper = true)
@Data
public class AddressesRest extends ResourceSupport {
    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;
}
