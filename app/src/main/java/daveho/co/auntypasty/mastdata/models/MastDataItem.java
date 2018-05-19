package daveho.co.auntypasty.mastdata.models;

public class MastDataItem {

    private String propertyName;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String unitName;
    private String tenantName;
    private int leaseStart;
    private int leaseEnd;
    private int leaseYears;
    private float currentRent;

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

    public int getLeaseStart() {
        return leaseStart;
    }

    public void setLeaseStart(int leaseStart) {
        this.leaseStart = leaseStart;
    }

    public int getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(int leaseEnd) {
        this.leaseEnd = leaseEnd;
    }

    public int getLeaseYears() {
        return leaseYears;
    }

    public void setLeaseYears(int leaseYears) {
        this.leaseYears = leaseYears;
    }

    public float getCurrentRent() {
        return currentRent;
    }

    public void setCurrentRent(float currentRent) {
        this.currentRent = currentRent;
    }
}
