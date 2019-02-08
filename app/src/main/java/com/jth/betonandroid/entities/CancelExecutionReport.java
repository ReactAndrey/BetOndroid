package com.jth.betonandroid.entities;

import java.util.List;

import com.jth.betonandroid.entities.*;
import com.jth.betonandroid.enums.ExecutionReportStatus;
import com.jth.betonandroid.enums.ExecutionReportErrorCode;

public class CancelExecutionReport {
    public void setCustomerRef(String customerRef) {
        this.customerRef = customerRef;
    }
    
    public String getCustomerRef() {
        return customerRef;
    }

    public void setStatus(ExecutionReportStatus status) {
        this.status = status;
    }
    
    public ExecutionReportStatus getStatus() {
        return status;
    }

    public void setErrorCode(ExecutionReportErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    public ExecutionReportErrorCode getErrorCode() {
        return errorCode;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
    
    public String getMarketId() {
        return marketId;
    }

    public void setInstructionReports(List<CancelInstructionReport> instructionReports) {
        this.instructionReports = instructionReports;
    }
    
    public List<CancelInstructionReport> getInstructionReports() {
        return instructionReports;
    }

    private String customerRef;
    private ExecutionReportStatus status;
    private ExecutionReportErrorCode errorCode;
    private String marketId;
    private List<CancelInstructionReport> instructionReports;
}
