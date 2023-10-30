import java.util.Date;

public interface SouvenirFactory {
    Souvenir createSouvenir(String name, String manufacturerDetails, Date releaseDate, double price);
    Manufacturer createManufacturer(String name, String country);
}
