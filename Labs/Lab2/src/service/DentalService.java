package service;

import domain.Appointment;
import domain.Patient;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;
import repository.AppointmentRepository;
import repository.PatientRepository;

import java.time.LocalDate;

public class DentalService {
    private PatientRepository patientsRepository;
    private AppointmentRepository appointmentsRepository;

    public DentalService(PatientRepository patientsRepository, AppointmentRepository appointmentsRepository) {
        this.patientsRepository = patientsRepository;
        this.appointmentsRepository = appointmentsRepository;
    }

    // CRUD Operations for Patient
    public void addPatient(String name, int id, String city, int phoneNumber) throws DuplicateItemException {

        this.patientsRepository.addItem(new Patient(name, id, city, phoneNumber));
    }

    public Patient getPatientByID(int idToSearchBy) throws ItemNotFound {

        return patientsRepository.getItemById(idToSearchBy);
    }

    public Iterable<Patient> getAllPatientsFromThePatientsRepository() {
       return this.patientsRepository.getAllItems();
    }

    public void updatePatient(String newName, int id, String newCity, int newPhoneNumber) throws ItemNotFound {
        Patient patientToUpdate = patientsRepository.getItemById(id);

        if (!newName.equals(patientToUpdate.getName())) {
            patientToUpdate.setName(newName);
        }

        if (newPhoneNumber != patientToUpdate.getPhoneNumber()) {
            patientToUpdate.setPhoneNumber(newPhoneNumber);
        }

        if (!newCity.equals(patientToUpdate.getCity())) {
            patientToUpdate.setCity(newCity);
        }
        this.patientsRepository.updateItemById(id, patientToUpdate);
    }

    public void deletePatientByID(int id) throws ItemNotFound {
        Patient patientToDelete = patientsRepository.getItemById(id);
        this.patientsRepository.deleteItemById(id);
    }

    // CRUD Operations for Appointment

    public void addAppointment(Integer appointmentNumber, Patient patient, LocalDate dateOfAppointment) throws DuplicateItemException {

        this.appointmentsRepository.addItem(new Appointment(appointmentNumber, patient, dateOfAppointment));
    }

    public Appointment getAppointmentByID(int idToSearchBy) throws ItemNotFound {

        return appointmentsRepository.getItemById(idToSearchBy);
    }

    public Iterable<Appointment> getAllAppointmentsFromTheAppointmentsRepository() {
        return this.appointmentsRepository.getAllItems();
    }

    public void updateAppointment(Integer appointmentNumber, LocalDate newDateOfAppointment) throws ItemNotFound {
        Appointment appointmentToUpdate = getAppointmentByID(appointmentNumber);
        if (!newDateOfAppointment.equals(appointmentToUpdate.getDateOfAppointment())) {
            appointmentToUpdate.setDateOfAppointment(newDateOfAppointment);
        }

    }

    public void deleteAppointmentByID(int id) throws ItemNotFound {
        Appointment appointmentToDelete = appointmentsRepository.getItemById(id);
        this.appointmentsRepository.deleteItemById(id);
    }
}
