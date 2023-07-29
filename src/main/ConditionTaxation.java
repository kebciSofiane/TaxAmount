package main;

public class ConditionTaxation {
    String taxePortDu;
    String idClient;
    String taxePortPaye;
    String useTaxePortDuGenerale;
    String useTaxePortPayeGenerale;


    public ConditionTaxation(String taxePortDu, String idClient, String taxePortPaye, String useTaxePortDuGenerale, String useTaxePortPayeGenerale) {
        this.taxePortDu = taxePortDu;
        this.idClient = idClient;
        this.taxePortPaye = taxePortPaye;
        this.useTaxePortDuGenerale = useTaxePortDuGenerale;
        this.useTaxePortPayeGenerale = useTaxePortPayeGenerale;
    }

    public String getTaxePortDu() {
        return taxePortDu;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getTaxePortPaye() {
        return taxePortPaye;
    }

    public String getUseTaxePortDuGenerale() {
        return useTaxePortDuGenerale;
    }

    public String getUseTaxePortPayeGenerale() {
        return useTaxePortPayeGenerale;
    }
}
