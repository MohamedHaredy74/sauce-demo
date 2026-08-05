package models;

public record AddressData(
        String firstName,
        String lastName,
        String company,
        String address1,
        String address2,
        String country,
        String state,
        String city,
        String zipCode,
        String mobileNumber
) {}