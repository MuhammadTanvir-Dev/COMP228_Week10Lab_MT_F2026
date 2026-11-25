
package application;

import java.math.BigDecimal;
import java.time.LocalDate;

public class User {

    private String fullName;
    private String currentAddress;
    private String contactNumber;
    private String emailAddress;
    private String education;
    private String gender;
    private LocalDate dateAvailable;
    private String desiredPosition;
    private BigDecimal desiredSalary;
    private String legallyAuthorized;
    private String relativesWorking;
    private String explanation;
    private String signature;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(LocalDate dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public String getDesiredPosition() {
        return desiredPosition;
    }

    public void setDesiredPosition(String desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public BigDecimal getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(BigDecimal desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public String getLegallyAuthorized() {
        return legallyAuthorized;
    }

    public void setLegallyAuthorized(String legallyAuthorized) {
        this.legallyAuthorized = legallyAuthorized;
    }

    public String getRelativesWorking() {
        return relativesWorking;
    }

    public void setRelativesWorking(String relativesWorking) {
        this.relativesWorking = relativesWorking;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}