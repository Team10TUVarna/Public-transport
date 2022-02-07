package bg.tu_varna.sit.oop_project_demo.presentation.models;

import bg.tu_varna.sit.oop_project_demo.data.entities.Company;
import bg.tu_varna.sit.oop_project_demo.data.entities.Distributor;
import bg.tu_varna.sit.oop_project_demo.data.entities.Trip;

public class RequestListViewModel {
    private int ticketCount;
    private String status;
    private Trip tripId;
    private Distributor distributorId;
    private Company companyId;

    public RequestListViewModel() {
    }

    public RequestListViewModel(int ticketCount, String status, Trip tripId, Distributor distributorId, Company companyId) {
        this.ticketCount = ticketCount;
        this.status = status;
        this.tripId = tripId;
        this.distributorId = distributorId;
        this.companyId = companyId;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Trip getTripId() {
        return tripId;
    }

    public void setTripId(Trip tripId) {
        this.tripId = tripId;
    }

    public Distributor getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Distributor distributorId) {
        this.distributorId = distributorId;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s | %s");
        /*"RequestListViewModel{" +
                "ticketCount=" + ticketCount +
                ", status='" + status + '\'' +
                ", tripId=" + tripId +
                ", distributorId=" + distributorId +
                ", companyId=" + companyId +
                '}';*/
    }
}
