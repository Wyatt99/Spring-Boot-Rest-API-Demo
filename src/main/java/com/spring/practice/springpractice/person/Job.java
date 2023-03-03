package com.spring.practice.springpractice.person;

public class Job {
    private String companyName;
    private String role;
    private int yearsEmployed;

    public Job(String companyName, String role, int yearsEmployed) {
        this.companyName = companyName;
        this.role = role;
        this.yearsEmployed = yearsEmployed;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getYearsEmployed() {
        return yearsEmployed;
    }

    public void setYearsEmployed(int yearsEmployed) {
        this.yearsEmployed = yearsEmployed;
    }

    @Override
    public String toString() {
        return "Job{" +
                "companyName='" + companyName + '\'' +
                ", role='" + role + '\'' +
                ", yearsEmployed=" + yearsEmployed +
                '}';
    }
}
