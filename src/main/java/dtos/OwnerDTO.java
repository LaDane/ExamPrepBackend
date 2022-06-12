package dtos;

import entities.Boat;
import entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerDTO {
    private Long id;
    private String name;
    private String phone;
    private AddressDTO address;
    private List<BoatDTO> boats = new ArrayList<>();

    public OwnerDTO(String name, String phone, AddressDTO address, List<BoatDTO> boats) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.boats = boats;
    }

    public OwnerDTO(Owner o) {
        if (o.getId() != null) {
            this.id = o.getId();
        }
        this.name = o.getName();
        this.phone = o.getPhone();
        this.address = new AddressDTO(o.getAddress());
        for (Boat b : o.getBoats()) {
            boats.add(new BoatDTO(b));
        }
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public String getPhone() {return phone;}
    public AddressDTO getAddress() {return address;}
    public List<BoatDTO> getBoats() {return boats;}

    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setAddress(AddressDTO address) {this.address = address;}
    public void setBoats(List<BoatDTO> boats) {this.boats = boats;}
    public void addBoat(BoatDTO b) {this.boats.add(b);}
}
