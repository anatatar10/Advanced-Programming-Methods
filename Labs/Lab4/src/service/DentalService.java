package service;

import domain.Appointment;
import domain.AppointmentValidator;
import domain.Patient;
import domain.PatientValidator;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;
import repository.AppointmentRepository;
import repository.PatientRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DentalService {
    protected PatientRepository patientsRepository;
    protected AppointmentRepository appointmentsRepository;

    public DentalService(PatientRepository patientsRepository, AppointmentRepository appointmentsRepository) {
        this.patientsRepository = patientsRepository;
        this.appointmentsRepository = appointmentsRepository;
    }

    // CRUD Operations for Patient
    public void addPatient(String name, int id, String city, int phoneNumber) throws DuplicateItemException {
        Patient patient = new Patient(name, id, city, phoneNumber);
        try {
            PatientValidator.validatePatient(patient);
            this.patientsRepository.addItem(patient);

        }catch (IllegalArgumentException e)
        {
            System.out.println("Incorrect input");
        }

    }

    public Patient getPatientByID(int idToSearchBy) throws ItemNotFound {

        return patientsRepository.getItemById(idToSearchBy);
    }

    public Iterable<Patient> getAllPatientsFromThePatientsRepository() {
       return this.patientsRepository.getAllItems();
    }

    public void updatePatient(String newName, int id, String newCity, int newPhoneNumber) throws ItemNotFound {
        Patient patientToUpdate = patientsRepository.getItemById(id);

        Patient updatedPatient = new Patient(newName, id, newCity, newPhoneNumber);
        try {
            PatientValidator.validatePatient(updatedPatient);
        }catch (IllegalArgumentException e)
        {
            System.out.printf("Incorrect arguments");
            return;
        }
        patientToUpdate.setName(newName);
        patientToUpdate.setPhoneNumber(newPhoneNumber);
        patientToUpdate.setCity(newCity);
        this.patientsRepository.updateItemById(id, patientToUpdate);
    }

    public void deletePatientByID(int id) throws ItemNotFound {
        Patient patientToDelete = patientsRepository.getItemById(id);
        this.patientsRepository.deleteItemById(id);
    }

    // CRUD Operations for Appointment

    public void addAppointment(Integer appointmentNumber, Patient patient, LocalDate dateOfAppointment) throws DuplicateItemException {

        Appointment appointment = new Appointment(appointmentNumber, patient, dateOfAppointment);
        try {
            AppointmentValidator.validateAppointment(appointment);
            this.appointmentsRepository.addItem(appointment);
        }catch (IllegalArgumentException e)
        {
            System.out.printf("Incorrect arguments");
        }
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

    public ArrayList<Patient> showPatientsFromAGivenCityOrderedById(String givenCity)
    {
        List<Patient> listOfPatients = new ArrayList<>((Collection) patientsRepository.getAllItems());
        ArrayList<Patient> patientsFromAGivenCity;
        patientsFromAGivenCity = (ArrayList<Patient>) listOfPatients.stream()
                .filter(patient -> patient.getCity().equalsIgnoreCase(givenCity))
                .sorted((patient1, patient2)->{
                    return (patient1.getId().compareTo(patient2.getId()));
                })
                .collect(Collectors.toList());
        return patientsFromAGivenCity;
    }

    public ArrayList<Patient> showPatientsEndingWithAGivenLetter(String letter)
    {
        List<Patient> listOfPatients = new ArrayList<>((Collection) patientsRepository.getAllItems());
        ArrayList<Patient> patientsEndingWithALetter;
        patientsEndingWithALetter = (ArrayList<Patient>) listOfPatients.stream()
                .filter(patient -> patient.getName().endsWith(String.valueOf(letter)))
                .collect(Collectors.toList());
        return patientsEndingWithALetter;
    }

    public Integer getPhoneNumberById(int patientId) {
        List<Patient> listOfPatients = new ArrayList<>((Collection) patientsRepository.getAllItems());
        int foundPhoneNumber;
        foundPhoneNumber = listOfPatients.stream()
                .filter(patient -> patient.getId() == patientId)
                .map(Patient::getPhoneNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalThreadStateException("Patient with ID " + patientId + " not found"));
        return foundPhoneNumber;
    }

    public ArrayList<Appointment> showAllAppointmentsOfAPatientByAGivenId(int id)
    {
        List<Appointment> listOfAppointments = new ArrayList<>((Collection) appointmentsRepository.getAllItems());
        ArrayList<Appointment> listOfAppointmentsOfAPatient;
        listOfAppointmentsOfAPatient = (ArrayList<Appointment>) listOfAppointments.stream()
                .filter(appointment -> appointment.getPatient().getId() == id)
                .collect(Collectors.toList());
        return listOfAppointmentsOfAPatient;
    }

    public ArrayList<Appointment> filteringAppointmentsBeforeDate(LocalDate date)
    {
        List<Appointment> listOfAppointments = new ArrayList<>((Collection) appointmentsRepository.getAllItems());
        ArrayList<Appointment> listOfAppointmentsBetweenRange;
        listOfAppointmentsBetweenRange = (ArrayList<Appointment>) listOfAppointments.stream()
                .filter(appointment -> appointment.getDateOfAppointment().isBefore(date))
                .collect(Collectors.toList());
        return listOfAppointmentsBetweenRange;
    }

}
