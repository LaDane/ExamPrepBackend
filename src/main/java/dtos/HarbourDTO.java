package dtos;

import entities.Boat;
import entities.Harbour;

import java.util.List;

public class HarbourDTO {
    private Long id;
    private String name;
    private Long capacity;
    private AddressDTO address;
    private List<BoatDTO> boats;

    public HarbourDTO(String name, Long capacity, AddressDTO address, List<BoatDTO> boats) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
        this.boats = boats;
    }

    public HarbourDTO(Harbour h) {
        if (h.getId() != null) {
            this.id = h.getId();
        }
        this.name = h.getName();
        this.capacity = h.getCapacity();
        this.address = new AddressDTO(h.getAddress());
        for (Boat b : h.getBoats()) {
            this.boats.add(new BoatDTO(b));
        }
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public Long getCapacity() {return capacity;}
    public AddressDTO getAddress() {return address;}
    public List<BoatDTO> getBoats() {return boats;}

    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCapacity(Long capacity) {this.capacity = capacity;}
    public void setAddress(AddressDTO address) {this.address = address;}
    public void setBoats(List<BoatDTO> boats) {this.boats = boats;}
    public void addBoat(BoatDTO b) {this.boats.add(b);}
}
