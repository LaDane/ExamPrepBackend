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
@Table(name = "boats")
public class Boat implements Serializable {
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
    @Column(name = "brand")
    private String brand;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "make")
    private String make;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Column(name = "country_svg", columnDefinition="LONGTEXT")
    private String image;

    @ManyToOne()
    @JoinColumn(name = "harbour_id")
    private Harbour harbour;

    @ManyToMany(mappedBy="boats")
    private List<Owner> owners = new ArrayList<>();

    public Boat() {}

    public Boat(String brand, String make, String name, String image) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
    }

    public Boat(String brand, String make, String name, String image, Harbour harbour, List<Owner> owners) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
        this.harbour = harbour;
        this.owners = owners;
    }

    public Boat(BoatDTO b) {
        if (b.getId() != null) {
            this.id = b.getId();
        }
        this.brand = b.getBrand();
        this.make = b.getMake();
        this.name = b.getName();
        this.image = b.getImage();
        this.harbour = new Harbour(b.getHarbour());
        for (OwnerDTO o : b.getOwners()) {
            this.owners.add(new Owner(o));
        }
    }

    public Long getId() {return id;}
    public String getBrand() {return brand;}
    public String getMake() {return make;}
    public String getName() {return name;}
    public String getImage() {return image;}
    public Harbour getHarbour() {return harbour;}
    public List<Owner> getOwners() {return owners;}

    public void setId(Long id) {this.id = id;}
    public void setBrand(String brand) {this.brand = brand;}
    public void setMake(String make) {this.make = make;}
    public void setName(String name) {this.name = name;}
    public void setImage(String image) {this.image = image;}
    public void setHarbour(Harbour harbour) {this.harbour = harbour;}
    public void setOwners(List<Owner> owners) {this.owners = owners;}
    public void addOwner(Owner owner) {this.owners.add(owner);}
}
