import java.util.Date;

public class ConcreteFactory implements SouvenirFactory {
    @Override
    public Souvenir createSouvenir(String name, String manufacturerDetails, Date releaseDate, double price) {
        return new Souvenir(name, manufacturerDetails, releaseDate, price);
    }

    @Override
    public Manufacturer createManufacturer(String name, String country) {
        return new Manufacturer(name, country);
    }
}
