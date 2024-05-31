package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class RequestedOutDto {
    private boolean isRequested;

    public boolean isRequested() {
        return isRequested;
    }

    public void setRequested(boolean requested) {
        isRequested = requested;
    }

    @Override
    public String toString() {
        return "RequestedOutDto{"
                + "isRequested=" + isRequested
                + '}';
    }
}
