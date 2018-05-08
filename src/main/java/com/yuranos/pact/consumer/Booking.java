
package com.yuranos.pact.consumer;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable
{

    final static long serialVersionUID = 9131175439626631000L;
    private Double id;
    @NotNull
    @Size(min = 3, max = 50)
    private String destination;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date departureDate;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date arrivalDate;
    @NotNull
    @Size(min = 3, max = 25)
    private String passengerName;
    @NotNull
    @Size(min = 3, max = 25)
    private String passengerSurname;
    @NotNull
    @DecimalMin("12.0")
    @DecimalMax("100.0")
    private Double age;

    /**
     * Creates a new Booking.
     * 
     */
    public Booking() {
        super();
    }

    /**
     * Creates a new Booking.
     * 
     */
    public Booking(Double id, String destination, Date departureDate, Date arrivalDate, String passengerName, String passengerSurname, Double age) {
        super();
        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.age = age;
    }

    /**
     * Returns the id.
     * 
     * @return
     *     id
     */
    public Double getId() {
        return id;
    }

    /**
     * Set the id.
     * 
     * @param id
     *     the new id
     */
    public void setId(Double id) {
        this.id = id;
    }

    /**
     * Returns the destination.
     * 
     * @return
     *     destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Set the destination.
     * 
     * @param destination
     *     the new destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Returns the departureDate.
     * 
     * @return
     *     departureDate
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * Set the departureDate.
     * 
     * @param departureDate
     *     the new departureDate
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Returns the arrivalDate.
     * 
     * @return
     *     arrivalDate
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Set the arrivalDate.
     * 
     * @param arrivalDate
     *     the new arrivalDate
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Returns the passengerName.
     * 
     * @return
     *     passengerName
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * Set the passengerName.
     * 
     * @param passengerName
     *     the new passengerName
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * Returns the passengerSurname.
     * 
     * @return
     *     passengerSurname
     */
    public String getPassengerSurname() {
        return passengerSurname;
    }

    /**
     * Set the passengerSurname.
     * 
     * @param passengerSurname
     *     the new passengerSurname
     */
    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    /**
     * Returns the age.
     * 
     * @return
     *     age
     */
    public Double getAge() {
        return age;
    }

    /**
     * Set the age.
     * 
     * @param age
     *     the new age
     */
    public void setAge(Double age) {
        this.age = age;
    }

    public int hashCode() {
        return new HashCodeBuilder().append(id).append(destination).append(departureDate).append(arrivalDate).append(passengerName).append(passengerSurname).append(age).toHashCode();
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (this.getClass()!= other.getClass()) {
            return false;
        }
        Booking otherObject = ((Booking) other);
        return new EqualsBuilder().append(id, otherObject.id).append(destination, otherObject.destination).append(departureDate, otherObject.departureDate).append(arrivalDate, otherObject.arrivalDate).append(passengerName, otherObject.passengerName).append(passengerSurname, otherObject.passengerSurname).append(age, otherObject.age).isEquals();
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("destination", destination).append("departureDate", departureDate).append("arrivalDate", arrivalDate).append("passengerName", passengerName).append("passengerSurname", passengerSurname).append("age", age).toString();
    }

}
