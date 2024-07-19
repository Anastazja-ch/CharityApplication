package pl.coderslab.charity.model;


import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Getter
@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer quantity;

    @ManyToMany
    @JoinTable(name = "donation_category",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    private String street;

    private String city;

    private String zipCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    private String pickUpComment;


    public Donation() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }
}
