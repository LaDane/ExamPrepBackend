package entities;

import dtos.AddressDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "street")
    private String street;

    @Basic(optional = false)
    @NotNull
    @Column(name = "street_number")
    private Long streetNumber;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "additional_info")
    private String additionalInfo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "city")
    private String city;

    @Basic(optional = false)
    @NotNull
    @Column(name = "zip")
    private Long zip;

    @OneToOne(mappedBy = "address")
    private Owner owner;

    @OneToOne(mappedBy = "address")
    private Harbour harbour;

    public Address() {}

    public Address(String street, Long streetNumber, String additionalInfo, String city, Long zip, Owner owner, Harbour harbour) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.additionalInfo = additionalInfo;
        this.city = city;
        this.zip = zip;
        this.owner = owner;
        this.harbour = harbour;
    }

    public Address(AddressDTO a) {
        if (a.getId() != null) {
            this.id = a.getId();
        }
        this.street = a.getStreet();
        this.streetNumber = a.getStreetNumber();
        this.additionalInfo = a.getAdditionalInfo();
        this.city = a.getCity();
        this.zip = a.getZip();
        this.owner = new Owner(a.getOwner());
        this.harbour = new Harbour(a.getHarbour());
    }

    public Long getId() {return id;}
    public String getStreet() {return street;}
    public Long getStreetNumber() {return streetNumber;}
    public String getAdditionalInfo() {return additionalInfo;}
    public String getCity() {return city;}
    public Long getZip() {return zip;}
    public Owner getOwner() {return owner;}
    public Harbour getHarbour() {return harbour;}

    public void setId(Long id) {this.id = id;}
    public void setStreet(String street) {this.street = street;}
    public void setStreetNumber(Long streetNumber) {this.streetNumber = streetNumber;}
    public void setAdditionalInfo(String additionalInfo) {this.additionalInfo = additionalInfo;}
    public void setCity(String city) {this.city = city;}
    public void setZip(Long zip) {this.zip = zip;}
    public void setOwner(Owner owner) {this.owner = owner;}
    public void setHarbour(Harbour harbour) {this.harbour = harbour;}
}
