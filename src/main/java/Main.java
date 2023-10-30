import java.util.Date;
public class Main {
    public static void main(String[] args) {
        SouvenirFactory factory = new ConcreteFactory();
        SouvenirStore store = new SouvenirStore(factory);
        //wrap lines 7-14 if we already have a souvenirs.json with data
        store.addSouvenir("Фірмова чашка", "Національний університет кораблебудування", new Date(), 10.99);
        store.addSouvenir("Фірмова чашка", "Приватбанк", new Date(), 9.99);
        store.addSouvenir("Біла футболка з логотипом", "Rozetka", new Date(), 9.99);
        store.addManufacturer("Національний університет кораблебудування", "Україна");
        store.addManufacturer("Приватбанк", "Україна");
        store.addManufacturer("Rozetka", "Україна");
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
