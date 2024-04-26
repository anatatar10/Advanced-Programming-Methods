package repository;

import domain.Patient;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;

import static org.junit.jupiter.api.Assertions.*;

class MemoryRepositoryTest{
    private MemoryRepository<Patient,Integer> memoryRepository;

    @BeforeEach
    void setUP() throws DuplicateItemException {
        memoryRepository = new MemoryRepository<Patient,Integer>();
        Patient patient1 = new Patient("Ana",1,"Cluj",12345);
        Patient patient2 = new Patient("Cris",2,"Brasov",354234);
        memoryRepository.addItem(patient1);
        memoryRepository.addItem(patient2);
    }

    @Test
    void addItem() throws DuplicateItemException {
        assert memoryRepository.listOfItems.size() == 2;
        memoryRepository.addItem(new Patient("Maria", 3, "Tg Mures", 42332));
        assert memoryRepository.listOfItems.size() == 3;
        try{
            memoryRepository.addItem(new Patient("Cosmin",1,"cluj",123));
        } catch (DuplicateItemException e)
        {
            System.out.printf("An item with id 1 already exists");
        }
    }

    @Test
    void deleteItemById() throws ItemNotFound {
        try {
            memoryRepository.deleteItemById(4);
        }catch (ItemNotFound e){
            System.out.println("Item not found");
        }
        memoryRepository.deleteItemById(2);
        assert memoryRepository.listOfItems.size() == 1;


    }

    @Test
    void getItemById() throws ItemNotFound, DuplicateItemException {
        Patient patient3 = new Patient("Maria", 3, "Tg Mures", 42332);
        memoryRepository.addItem(patient3);
        assertEquals(patient3, memoryRepository.getItemById(3));
        assertNotEquals(patient3, memoryRepository.getItemById(1));
        try{
            memoryRepository.getItemById(10);
        }catch (ItemNotFound e)
        {
            System.out.println("Item not found");
        }
    }

    @Test
    void updateItemById() throws ItemNotFound, DuplicateItemException {
        memoryRepository = new MemoryRepository<Patient,Integer>();
        Patient patient1 = new Patient("Ana",1,"Cluj",12345);
        Patient patient2 = new Patient("Cris",2,"Brasov",354234);
        memoryRepository.addItem(patient1);
        memoryRepository.addItem(patient2);
        Patient newPatient1 = new Patient("Maria", 1, "Tg Mures", 42332);
        try {
            memoryRepository.updateItemById(1, newPatient1);
        }catch (ItemNotFound e)
        {
            System.out.printf("Item not found");
        }
        assertEquals ("Maria", memoryRepository.getItemById(1).getName());
        try{
            memoryRepository.updateItemById(10,newPatient1);
        }catch (ItemNotFound e)
        {
            System.out.printf("Item not found");
        }
    }

    @Test
    void getAllItems() {
        Iterable<Patient> listOfPatients = memoryRepository.getAllItems();
        int counter = 0;
        for(Patient patient: listOfPatients)
        {
            counter++;
        }
        assert counter == 2;
    }
}