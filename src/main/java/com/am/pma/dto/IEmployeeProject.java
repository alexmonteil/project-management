package com.am.pma.dto;

public interface IEmployeeProject {

    // Need to have the property names begin with get so Spring data can map them
    public String getFirstName();
    public String getLastName();
    public String getEmail();
    public int getProjectCount();
}
