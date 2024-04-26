package ui;

import domain.Patient;
import service.Service;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Service patientsService;

    public UI(Service patientsService) {
        this.patientsService = patientsService;
    }

    public void listAllPatients()
    {
        ArrayList<Patient> listOfPatients = this.patientsService.getAllPatientsFromThePatientsRepository();
        for(Patient patient: listOfPatients)
        {
            System.out.println(patient);
        }
    }

    public void addPatient(String name, int id, String city, int phoneNumber){
        patientsService.addPatient(name, id, city, phoneNumber);
    }

    public void getPatientById(int id)
    {
        Patient patientToSearchById = patientsService.getPatientByID(id);
        if(patientToSearchById != null)
            System.out.println(patientToSearchById);

    }
    public void updatePatient(String newName, int id, String newCity, int newPhoneNumber){
        patientsService.updatePatient(newName, id, newCity, newPhoneNumber);
    }

    public void deletePatientByID(int idToDeleteBy)
    {
        patientsService.deletePatientByID(idToDeleteBy);
    }

    public void printMenu() {
        System.out.println("\n================");
        System.out.println("---Dentistry---");
        System.out.println("================\n");
        System.out.println("0 - Exit");
        System.out.println("1 - List all the patients");
        System.out.println("2 - Add a patient");
        System.out.println("3 - Delete a patient");
        System.out.println("4 - Update a patient");
        System.out.println("5 - Search a patient");
    }

    public void run()
    {
        while(true)
        {
            printMenu();
            System.out.print("Please input your option: ");
            Scanner scanCommand = new Scanner(System.in);
            int command = scanCommand.nextInt();
            switch (command)
            {
                case 0:
                    System.out.println("Exiting the program.");
                    return;
                case 1:
                    System.out.println("\nList of patients:\n");
                    listAllPatients();
                    break;
                case 2:
                    Scanner addScanner = new Scanner(System.in);
                    System.out.println("Enter patient name: ");
                    String addName = addScanner.nextLine();

                    int addId;
                    while (true) {
                        System.out.println("Enter patient id: ");
                        if (addScanner.hasNextInt()) {
                            addId = addScanner.nextInt();
                            addScanner.nextLine();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            addScanner.nextLine();
                        }
                    }

                    System.out.println("Enter patient city: ");
                    String readCity = addScanner.nextLine();


                    int addPhoneNumber;
                    while (true) {
                        System.out.println("Enter patient phone number: ");
                        if (addScanner.hasNextInt()) {
                            addPhoneNumber = addScanner.nextInt();
                            addScanner.nextLine();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid phone number.");
                            addScanner.nextLine();
                        }
                    }
                    addPatient(addName, addId, readCity, addPhoneNumber);
                    break;
                case 3:
                    Scanner deleteScanner = new Scanner(System.in);
                    int deleteId;
                    while (true) {
                        System.out.println("Enter patient id: ");
                        if (deleteScanner.hasNextInt()) {
                            deleteId = deleteScanner.nextInt();
                            deleteScanner.nextLine();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            deleteScanner.nextLine();
                        }
                    }
                    deletePatientByID(deleteId);
                    break;
                case 4:
                    Scanner updateScanner = new Scanner(System.in);
                    int updateById;
                    while (true) {
                        System.out.println("Enter patient id: ");
                        if (updateScanner.hasNextInt()) {
                            updateById = updateScanner.nextInt();
                            updateScanner.nextLine();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            updateScanner.nextLine();
                        }
                    }
                    System.out.println("Enter the new patient name: ");
                    String updateName = updateScanner.nextLine();

                    System.out.println("Enter the new patient city: ");
                    String updateCity = updateScanner.nextLine();

                    int updatePhoneNumber;
                    while (true) {
                        System.out.println("Enter patient phone number: ");
                        if (updateScanner.hasNextInt()) {
                            updatePhoneNumber = updateScanner.nextInt();
                            updateScanner.nextLine();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid phone number.");
                            updateScanner.nextLine();
                        }
                    }
                    updatePatient(updateName, updateById, updateCity, updatePhoneNumber);
                    break;
                case 5:
                    Scanner searchScanner = new Scanner(System.in);
                    int searchId;
                    while (true) {
                        System.out.println("Enter patient id: ");
                        if (searchScanner.hasNextInt()) {
                            searchId = searchScanner.nextInt();
                            searchScanner.nextLine();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            searchScanner.nextLine();
                        }
                    }
                    getPatientById(searchId);
                    break;
            }
        }
    }

}
