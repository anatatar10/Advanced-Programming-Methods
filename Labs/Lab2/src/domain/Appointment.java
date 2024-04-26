package domain;

import java.time.LocalDate;


public class Appointment implements Identifiable<Integer> {
    private int appointmentNumber;
    private Patient patient;
    private LocalDate dateOfAppointment;

    public Appointment(int appointmentNumber, Patient patient, LocalDate dateOfTheAppointment) {
        this.appointmentNumber = appointmentNumber;
        this.patient = patient;
        this.dateOfAppointment = dateOfTheAppointment;
    }

    @Override
    public Integer getId() {
        return appointmentNumber;
    }

    @Override
    public void setId(Integer appointmentNumberToSet) {
        this.appointmentNumber = appointmentNumberToSet;
    }

    public int getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(int appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    @Override
    public String toString() {


        return "Appointment ID: " + appointmentNumber + "\n" +
                "Patient: " + patient.getName() + "\n" +
                "Date: " + dateOfAppointment + "\n";

    }

    @Override
    public boolean equals(Object objectToCompare)
    {
        if (this == objectToCompare)
            return true;
        if (objectToCompare.getClass() != Appointment.class)
            return false;
        Appointment d = (Appointment) objectToCompare;
        return d.appointmentNumber == this.appointmentNumber;
    }

}
