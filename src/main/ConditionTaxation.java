package main;

public class ConditionTaxation {
    double taxePortDu;
    int idClient;
    double taxePortPaye;
    boolean useTaxePortDuGenerale;
    boolean useTaxePortPayeGenerale;

    public ConditionTaxation(double taxePortDu, int idClient, double taxePortPaye, boolean useTaxePortDuGenerale, boolean useTaxePortPayeGenerale) {
        this.taxePortDu = taxePortDu;
        this.idClient = idClient;
        this.taxePortPaye = taxePortPaye;
        this.useTaxePortDuGenerale = useTaxePortDuGenerale;
        this.useTaxePortPayeGenerale = useTaxePortPayeGenerale;
    }

    public double getTaxePortDu() {
        return taxePortDu;
    }

    public int getIdClient() {
        return idClient;
    }

    public double getTaxePortPaye() {
        return taxePortPaye;
    }

    public boolean isUseTaxePortDuGenerale() {
        return useTaxePortDuGenerale;
    }

    public boolean isUseTaxePortPayeGenerale() {
        return useTaxePortPayeGenerale;
    }
}
