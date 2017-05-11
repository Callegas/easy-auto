package controller;

import model.Car;
import database.DAOCar;
import valitadors.ValidateFactory;
import view.ColorFactory;
import view.ItemMenuFactory;

import java.util.Locale;
import java.util.Scanner;

public class RegisterCar {
    ItemMenuFactory itemMenuFactory = new ItemMenuFactory();
    ColorFactory colorFactory = new ColorFactory();
    Scanner input = new Scanner(System.in).useLocale(Locale.US);
    DAOCar daoCar = new DAOCar();
    Car car = new Car();
    ValidateFactory validateFactory = new ValidateFactory();

    public void registerNewCar() {
        itemMenuFactory.facadeRegisterCar();

        registerCarManufacturerInput("");

        registerCarModelInput( "" );

        registerCarLicencePlateInput( "" );

        registerCarYearInput("");

        registerCarEngineInput("");

        registerCarKmInput("");

        System.out.print("Enter the rent day price for this car: ");
        registerCarRentPrice(input.nextDouble());

        daoCar.save(car);
    }

    private void errorTest(String error){
        if (error != "")
            System.out.println(colorFactory.ANSI_RED + error + colorFactory.ANSI_RESET);

        System.out.print(colorFactory.ANSI_WHITE_BACKGROUND + colorFactory.ANSI_BLACK);
    }

    public String registerCarManufacturer(String manufacturer) {
        if (validateFactory.validateName.isValid( manufacturer )) {
            car.setManufacturer(manufacturer);
            return car.getManufacturer();
        }
        return "error";
    }

    public void registerCarManufacturerInput(String error) {
        Scanner input = new Scanner(System.in);
        errorTest(error);

        System.out.print("Enter the manufacturer:  ");
        String manufacturer;
        manufacturer = input.nextLine();
        registerCarManufacturer(manufacturer);

        if (registerCarManufacturer(manufacturer) == "error")
            registerCarManufacturerInput("Name isn't valid, try again: ");

    }

    public String registerCarModel(String model) {
        if (validateFactory.validateName.isValid( model )) {
            car.setModel( model );
            return car.getModel();
        }
        return "error";
    }

    public void registerCarModelInput(String error) {
        Scanner input = new Scanner(System.in);
        errorTest(error);

        System.out.print("Enter the model:         ");
        String model;
        model = input.nextLine();
        registerCarModel(model);

        if(registerCarModel(model) == "error")
            registerCarModelInput( "Name isn't valid, try again: " );
    }

    public String registerCarLicencePlate(String licencePlate) {
        if (validateFactory.validateLicencePlate.isValid( licencePlate )) {
        car.setLicencePlate( licencePlate );
        return car.getLicencePlate();
        }
        return "error";
    }

    public void registerCarLicencePlateInput(String error){
        Scanner input = new Scanner(System.in);
        errorTest(error);

        System.out.print("Enter the licence plate: ");
        String licencePlate;
        licencePlate = input.nextLine();
        registerCarLicencePlate(licencePlate);

        if(registerCarLicencePlate(licencePlate) == "error")
            registerCarLicencePlateInput( "Licence plate isn't valid, try again: " );

    }

    public Integer registerCarYear(Integer year){
        if (validateFactory.validateCarYear.isValid(year)) {
            car.setYear(year);
            return car.getYear();
        }
        return 0;
    }

    public void registerCarYearInput(String error){
        Scanner input = new Scanner(System.in);
        errorTest(error);

        System.out.print("Enter the car year:      ");
        Integer year = input.nextInt();
        registerCarYear(year);

        if(registerCarYear(year) == 0)
            registerCarYearInput( "Car year isn't valid, try again: " );
    }

    public Double registerCarEngine(Double engine){
        if (validateFactory.validateEngine.isValid(engine)) {
            car.setEngine(engine);
            return car.getEngine();
        }
        return 0.0;
    }

    public void registerCarEngineInput(String error){
        Scanner input = new Scanner(System.in);
        errorTest(error);

        System.out.print("Enter the car engine:    ");
        Double engine = input.nextDouble();
        registerCarEngine(engine);

        if(registerCarEngine(engine) == 0)
            registerCarYearInput( "Engine isn't valid, try again: " );
    }

    public Long registerCarKM(Long km){
        if (km >= 0) {
            car.setKM(km);
            return car.getKM();
        }
        return -1L;
    }

    public void registerCarKmInput(String error){
        Scanner input = new Scanner(System.in);
        errorTest(error);

        System.out.print("Enter the car KM:        ");
        Long km = input.nextLong();
        registerCarKM(km);

        if (registerCarKM(km) == -1)
            registerCarKmInput("KM isn't valid, try again: ");

    }

    public Double registerCarRentPrice(Double price){
        car.setRentPrice(price);
        return car.getRentPrice();
    }

    public void removeCar() {
        System.out.println("D E L E T E  C A R");
        System.out.print("Enter the licence plate for DELETE car: ");
        daoCar.remove(input.next());
    }

    public void findCar() {
        System.out.println("F I N D  C U S T O M E R:");
        System.out.print("Enter the licence plate for find car: ");
        daoCar.find(input.next());
    }
}
