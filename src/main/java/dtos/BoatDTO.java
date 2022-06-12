package dtos;

import entities.Boat;
import entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class BoatDTO {
    private Long id;
    private String brand;
    private String make;
    private String name;
    private String image;
    private HarbourDTO harbour;
    private List<OwnerDTO> owners = new ArrayList<>();

    public BoatDTO(String brand, String make, String name, String image, HarbourDTO harbour, List<OwnerDTO> owners) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
        this.harbour = harbour;
        this.owners = owners;
    }

    public BoatDTO(Boat b) {
        if (b.getId() != null) {
            this.id = b.getId();
        }
        this.brand = b.getBrand();
        this.make = b.getMake();
        this.name = b.getName();
        this.image = b.getImage();
        this.harbour = new HarbourDTO(b.getHarbour());
        for (Owner o : b.getOwners()) {
            this.owners.add(new OwnerDTO(o));
        }
    }

    public Long getId() {return id;}
    public String getBrand() {return brand;}
    public String getMake() {return make;}
    public String getName() {return name;}
    public String getImage() {return image;}
    public HarbourDTO getHarbour() {return harbour;}
    public List<OwnerDTO> getOwners() {return owners;}

    public void setId(Long id) {this.id = id;}
    public void setBrand(String brand) {this.brand = brand;}
    public void setMake(String make) {this.make = make;}
    public void setName(String name) {this.name = name;}
    public void setImage(String image) {this.image = image;}
    public void setHarbour(HarbourDTO harbour) {this.harbour = harbour;}
    public void setOwners(List<OwnerDTO> owners) {this.owners = owners;}
    public void addOwner(OwnerDTO owner) {this.owners.add(owner);}
}
