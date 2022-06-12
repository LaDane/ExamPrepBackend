package entities;

import dtos.BoatDTO;
import dtos.HarbourDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Harbour {
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
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Column(name = "capacity")
    private Long capacity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="harbour")
    private List<Boat> boats = new ArrayList<>();

    public Harbour() {}

    public Harbour(String name, Long capacity, Address address) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

    public Harbour(String name, Long capacity, Address address, List<Boat> boats) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
        this.boats = boats;
    }

    public Harbour(HarbourDTO h) {
        if (h.getId() != null) {
            this.id = h.getId();
        }
        this.name = h.getName();
        this.capacity = h.getCapacity();
        this.address = new Address(h.getAddress());
        for (BoatDTO b : h.getBoats()) {
            this.boats.add(new Boat(b));
        }
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public Long getCapacity() {return capacity;}
    public Address getAddress() {return address;}
    public List<Boat> getBoats() {return boats;}

    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCapacity(Long capacity) {this.capacity = capacity;}
    public void setAddress(Address address) {this.address = address;}
    public void setBoats(List<Boat> boats) {this.boats = boats;}
    public void addBoat(Boat boat) {this.boats.add(boat);}
}
