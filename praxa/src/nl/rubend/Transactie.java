package nl.rubend;

public class Transactie {
    private int transactienr;
    private Date datum;
    private LocalTime tijd = LocalTime.now();
    private String plaats;
    private double totaal;
    private double btwTotaal;
    private String transactieType;
}
