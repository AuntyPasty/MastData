package daveho.co.auntypasty.mastdata.models;

/**
 * Model for a mast data item
 */
public class MastDataItem {

    private String propertyName;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String unitName;
    private String tenantName;
    private String leaseStart;
    private String leaseEnd;
    private String leaseYears;
    private String currentRent;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getLeaseStart() {
        return leaseStart;
    }

    public void setLeaseStart(String leaseStart) {
        this.leaseStart = leaseStart;
    }

    public String getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(String leaseEnd) {
        this.leaseEnd = leaseEnd;
    }

    public String getLeaseYears() {
        return leaseYears;
    }

    public void setLeaseYears(String leaseYears) {
        this.leaseYears = leaseYears;
    }

    public String getCurrentRent() {
        return currentRent;
    }

    public void setCurrentRent(String currentRent) {
        this.currentRent = currentRent;
    }
}
