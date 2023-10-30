import java.util.Date;
public class Main {
    public static void main(String[] args) {
        SouvenirStore store = new SouvenirStore();

        Souvenir souvenir1 = new Souvenir("Фірмова чашка", "Національний університет кораблебудування", new Date(), 10.99);
        Souvenir souvenir2 = new Souvenir("Фірмова чашка", "Приватбанк", new Date(), 9.99);
        Souvenir souvenir3 = new Souvenir("Біла футболка з логотипом", "Rozetka", new Date(), 9.99);
        Manufacturer manufacturer1 = new Manufacturer("Національний університет кораблебудування", "Україна");
        Manufacturer manufacturer2 = new Manufacturer("Приватбанк", "Україна");
        Manufacturer manufacturer3 = new Manufacturer("Rozetka", "Україна");


        store.addSouvenir(souvenir1);
        store.addSouvenir(souvenir2);
        store.addSouvenir(souvenir3);
        store.addManufacturer(manufacturer1);
        store.addManufacturer(manufacturer2);
        store.addManufacturer(manufacturer3);

        store.saveSouvenirsToJSON("souvenirs.json");
        store.loadSouvenirsFromJSON("souvenirs.json");

        System.out.println("\nВсі сувеніри:");
        store.viewAllSouvenirs();

        System.out.println("\nВсі виробники:");
        store.viewAllManufacturers();

        System.out.println("\nСувеніри виготовлені заданим виробником:");
        store.viewSouvenirsByManufacturer("Національний університет кораблебудування");

        System.out.println("\nСувеніри виготовлені в заданій країні:");
        store.viewSouvenirsByCountry("Україна");

        System.out.println("\nВиробники, чиї ціни на сувеніри менше заданої:");
        store.viewManufacturersByPrice(10.0);

        System.out.println("\nВсі виробникаи та їх сувеніри:");
        store.viewManufacturersAndTheirSouvenirs();

        System.out.println("\nВиробники заданого сувеніру, виробленого у заданому році:");
        store.viewManufacturersBySouvenirAndYear("Фірмова чашка", 2023);

        System.out.println("\nВидалення заданого виробника та його сувенірів");
        store.deleteManufacturer("Національний університет кораблебудування");
        
        store.saveSouvenirsToJSON("souvenirs.json");
    }
}
