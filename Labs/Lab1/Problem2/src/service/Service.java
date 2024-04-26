package service;

import domain.Patient;
import repository.Repository;

import java.util.ArrayList;

public class Service {
    private Repository patientsRepository;

    public Service(Repository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    public void addPatient(String name, int id, String city, int phoneNumber)
    {
        Patient existingPatient = patientsRepository.getPatientByID(id);
        if(existingPatient != null)
        {
            System.out.println("A patient with id " + id +" already exists");
            return;
        }
        Patient patientToAdd = new Patient(name, id, city, phoneNumber);
        this.patientsRepository.addPatient(patientToAdd);
    }

    public Patient getPatientByID(int idToSearchBy)
    {
        Patient patientToSearchById = patientsRepository.getPatientByID(idToSearchBy);
        if(patientToSearchById == null)
        {
            System.out.println("Patient not found");
            return null;

        }
        return patientToSearchById;
    }

    public ArrayList<Patient> getAllPatientsFromThePatientsRepository() {
       return this.patientsRepository.getAllPatientsFromTheList();
    }

    public void updatePatient(String newName, int id, String newCity, int newPhoneNumber){
        Patient patientToUpdate = patientsRepository.getPatientByID(id);
        if(patientToUpdate == null)
        {
            System.out.println("Patient not found");
            return;
        }

        if (!newName.equals(patientToUpdate.getName())) {
            patientToUpdate.setName(newName);
        }

        if (newPhoneNumber != patientToUpdate.getPhoneNumber()) {
            patientToUpdate.setPhoneNumber(newPhoneNumber);
        }

        if (!newCity.equals(patientToUpdate.getCity())) {
            patientToUpdate.setCity(newCity);
        }
        this.patientsRepository.updatePatient(patientToUpdate);
    }

    public void deletePatientByID(int id) {
        Patient patientToDelete = patientsRepository.getPatientByID(id);
        if(patientToDelete == null)
        {
            System.out.println("Patient not found");
            return;
        }
        this.patientsRepository.deletePatientByID(id);
    }

}
