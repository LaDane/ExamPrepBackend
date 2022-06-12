package entities;

import dtos.BoatDTO;
import dtos.OwnerDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner implements Serializable {
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
    @Size(min = 1, max = 255)
    @Column(name = "phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "owner_boat",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_id"))
    private List<Boat> boats = new ArrayList<>();

    public Owner() {}

    public Owner(String name, String phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Owner(String name, String phone, Address address, List<Boat> boats) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.boats = boats;
    }

    public Owner(OwnerDTO o) {
        if (o.getId() != null) {
            this.id = o.getId();
        }
        this.name = o.getName();
        this.phone = o.getPhone();
        this.address = new Address(o.getAddress());
        for (BoatDTO b : o.getBoats()) {
            this.boats.add(new Boat(b));
        }
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public String getPhone() {return phone;}
    public Address getAddress() {return address;}
    public List<Boat> getBoats() {return boats;}

    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setAddress(Address address) {this.address = address;}
    public void setBoats(List<Boat> boats) {this.boats = boats;}
    public void addBoat(Boat boat) {this.boats.add(boat);}
}
