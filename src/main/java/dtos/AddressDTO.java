package dtos;

import entities.Address;
import entities.Harbour;

public class AddressDTO {
    private Long id;
    private String street;
    private Long streetNumber;
    private String additionalInfo;
    private String city;
    private Long zip;
    private OwnerDTO owner;
    private HarbourDTO harbour;

    public AddressDTO(String street, Long streetNumber, String additionalInfo, String city, Long zip, OwnerDTO owner, HarbourDTO harbour) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.additionalInfo = additionalInfo;
        this.city = city;
        this.zip = zip;
        this.owner = owner;
        this.harbour = harbour;
    }

    public AddressDTO(Address a) {
        if (a.getId() != null) {
            this.id = a.getId();
        }
        this.street = a.getStreet();
        this.streetNumber = a.getStreetNumber();
        this.additionalInfo = a.getAdditionalInfo();
        this.city = a.getCity();
        this.zip = a.getZip();
        if (a.getOwner() != null) {
            this.owner = new OwnerDTO(a.getOwner());
        }
        if (a.getHarbour() != null) {
            this.harbour = new HarbourDTO(a.getHarbour());
        }
    }

    public Long getId() {return id;}
    public String getStreet() {return street;}
    public Long getStreetNumber() {return streetNumber;}
    public String getAdditionalInfo() {return additionalInfo;}
    public String getCity() {return city;}
    public Long getZip() {return zip;}
    public OwnerDTO getOwner() {return owner;}
    public HarbourDTO getHarbour() {return harbour;}

    public void setId(Long id) {this.id = id;}
    public void setStreet(String street) {this.street = street;}
    public void setStreetNumber(Long streetNumber) {this.streetNumber = streetNumber;}
    public void setAdditionalInfo(String additionalInfo) {this.additionalInfo = additionalInfo;}
    public void setCity(String city) {this.city = city;}
    public void setZip(Long zip) {this.zip = zip;}
    public void setOwner(OwnerDTO owner) {this.owner = owner;}
    public void setHarbour(HarbourDTO harbour) {this.harbour = harbour;}
}
