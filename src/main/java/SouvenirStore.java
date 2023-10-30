import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;

public class SouvenirStore {
    private final SouvenirFactory factory;
    private final List<Souvenir> souvenirs;
    private final List<Manufacturer> manufacturers;


    public SouvenirStore(SouvenirFactory factory) {
        this.factory = factory;
        souvenirs = new ArrayList<>();
        manufacturers = new ArrayList<>();
    }
    public void addSouvenir(String name, String manufacturerDetails, Date releaseDate, double price) {
        Souvenir souvenir = factory.createSouvenir(name, manufacturerDetails, releaseDate, price);
        souvenirs.add(souvenir);
    }


    public void addManufacturer(String name, String country) {
        Manufacturer manufacturer = factory.createManufacturer(name, country);
        manufacturers.add(manufacturer);
    }
    public void saveSouvenirsToJSON(String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(souvenirs, writer);
            System.out.println("Дані збережено у файлі " + fileName);
        } catch (IOException e) {
            System.err.println("Помилка при збереженні даних у JSON файл.");
        }
    }

    public void loadSouvenirsFromJSON(String fileName) {
        try (Reader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            List<Souvenir> loadedSouvenirs = gson.fromJson(reader, new TypeToken<List<Souvenir>>() {}.getType());
            if (loadedSouvenirs != null) {
                souvenirs.clear();
                souvenirs.addAll(loadedSouvenirs);
                System.out.println("Дані завантажено з файлу " + fileName);
            } else {
                System.out.println("Файл не містить жодних даних.");
            }
        } catch (IOException e) {
            System.err.println("Помилка при завантаженні даних з JSON файлу.");
        }
    }
    public void addSouvenir(Souvenir souvenir) {
        souvenirs.add(souvenir);
    }


    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
    }


    public void viewAllSouvenirs() {
        souvenirs.forEach(souvenir -> System.out.println(souvenir.getName()));
    }


    public void viewAllManufacturers() {
        manufacturers.forEach(manufacturer -> System.out.println(manufacturer.getName()));
    }


    public void viewSouvenirsByManufacturer(String manufacturerName) {
        List<Souvenir> souvenirsByManufacturer = souvenirs.stream()
                .filter(souvenir -> souvenir.getManufacturerDetails().equals(manufacturerName)).toList();

        souvenirsByManufacturer.forEach(souvenir -> System.out.println(souvenir.getName()));
    }


    public void viewSouvenirsByCountry(String country) {
        List<Souvenir> souvenirsByCountry = souvenirs.stream()
                .filter(souvenir -> {
                    Manufacturer manufacturer = manufacturers.stream()
                            .filter(m -> m.getName().equals(souvenir.getManufacturerDetails()))
                            .findFirst().orElse(null);
                    return manufacturer != null && manufacturer.getCountry().equals(country);
                }).toList();

        souvenirsByCountry.forEach(souvenir -> System.out.println(souvenir.getName()));
    }


    public void viewManufacturersByPrice(double maxPrice) {
        List<Manufacturer> manufacturersByPrice = manufacturers.stream()
                .filter(manufacturer -> {
                    List<Souvenir> manufacturerSouvenirs = souvenirs.stream()
                            .filter(souvenir -> souvenir.getManufacturerDetails().equals(manufacturer.getName())).toList();
                    return manufacturerSouvenirs.stream().anyMatch(souvenir -> souvenir.getPrice() < maxPrice);
                }).toList();

        manufacturersByPrice.forEach(manufacturer -> System.out.println(manufacturer.getName()));
    }


    public void viewManufacturersAndTheirSouvenirs() {
        manufacturers.forEach(manufacturer -> {
            System.out.println("Manufacturer: " + manufacturer.getName());
            List<Souvenir> manufacturerSouvenirs = souvenirs.stream()
                    .filter(souvenir -> souvenir.getManufacturerDetails().equals(manufacturer.getName())).toList();

            manufacturerSouvenirs.forEach(souvenir -> System.out.println("  Souvenir: " + souvenir.getName()));
        });
    }


    public void viewManufacturersBySouvenirAndYear(String souvenirName, int year) {
        List<Manufacturer> manufacturersBySouvenirAndYear = manufacturers.stream()
                .filter(manufacturer -> {
                    List<Souvenir> manufacturerSouvenirs = souvenirs.stream()
                            .filter(souvenir ->
                                    souvenir.getManufacturerDetails().equals(manufacturer.getName()) &&
                                            souvenir.getName().equals(souvenirName) &&
                                            getYearFromDate(souvenir.getReleaseDate()) == year).toList();
                    return !manufacturerSouvenirs.isEmpty();
                }).toList();

        manufacturersBySouvenirAndYear.forEach(manufacturer -> System.out.println(manufacturer.getName()));
    }


    private int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }


    public void deleteManufacturer(String manufacturerName) {
        manufacturers.removeIf(manufacturer -> manufacturer.getName().equals(manufacturerName));
        souvenirs.removeIf(souvenir -> souvenir.getManufacturerDetails().equals(manufacturerName));
    }
}
