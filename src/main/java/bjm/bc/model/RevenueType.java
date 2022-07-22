package bjm.bc.model;

public enum RevenueType {
	
	CORPORATION_TAXES("Corporation Tax"),
    PROPERTY_TAXES("Property Tax"),
    CAPITAL_TAXES("Capital Tax"),
    OTHER_TAXES ("Other Tax"),
    INCOME_TAX ("Income Tax"),
    NIC ("National Insurance Contributions"),
    VAT ("Value Added Tax"),
    GST ("Goods and Services Tax"),
    FUEL_DUTIES ("Fuel Duties"),
    OTHER_INDIRECT_TAXES("Other Indirect Tax");
	
	public final String value;

    private RevenueType(String value) {
        this.value = value;
    }
    
    
}
