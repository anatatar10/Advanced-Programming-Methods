package repository;

import domain.Patient;

import java.util.ArrayList;

public class Repository {
    private ArrayList<Patient> listOfPatients = new ArrayList<>();
    public void addPatient(Patient p) {
        listOfPatients.add(p);
    }

    public Patient getPatientByID(int idToSearchBy) {
        for (Patient patient : listOfPatients) {
            if (patient.getId() == idToSearchBy) {
                return patient;
            }
        }
        return null;
    }


    public ArrayList<Patient> getAllPatientsFromTheList() {
        return listOfPatients;
    }

    public void updatePatient(Patient patientToUpdate)
    {
        for(Patient patient : listOfPatients)
        {
            if(patient.getId() == patientToUpdate.getId())
            {
                listOfPatients.set(listOfPatients.indexOf(patient), patientToUpdate);
            }
        }
    }
    public void deletePatientByID(int idToDeleteBy)
    {
        for(Patient patient : listOfPatients)
        {
            if(patient.getId() == idToDeleteBy)
            {
                listOfPatients.remove(patient);
                break;
            }
        }
    }

}
