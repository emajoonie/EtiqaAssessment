package com.etiqa.assesment.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_Id")
    private int customerId = 0;

    @NotNull(message="Cannot be null")
    @NotBlank(message="Cannot be blank")
    @Column(name = "First_Name")
    private String firstName="";

    @Column(name = "Last_Name")
    private String lastName="";

    @Column(name = "Email_Office")
    private String emailOffice = "";
    @Column(name = "Email_Personal")
    private String emailPersonal = "";
    @Column(name = "Family_Members")
    private int familyMembers;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailOffice() {
        return emailOffice;
    }

    public void setEmailOffice(String emailOffice) {
        this.emailOffice = emailOffice;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public int getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(int familyMembers) {
        this.familyMembers = familyMembers;
    }


}
