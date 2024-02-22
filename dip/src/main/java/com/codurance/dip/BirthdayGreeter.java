package com.codurance.dip;

import java.time.MonthDay;


public class BirthdayGreeter {

    private final EmployeeRepository employeeRepository;
    private final ClockRepository clockRepository;
    private final EmailSenderRepository emailSenderRepository;

    public BirthdayGreeter(
        EmployeeRepository employeeRepository, 
        ClockRepository clockRepository,
        EmailSenderRepository emailSenderRepository        
    ) {
        this.employeeRepository = employeeRepository;
        this.clockRepository = clockRepository;
        this.emailSenderRepository = emailSenderRepository;
        
    }

    public void sendGreetings() {
        MonthDay today = this.clockRepository.monthDay();
        this.employeeRepository.findEmployeesBornOn(today)
                .stream()
                .map( employee -> emailFor(employee) )
                .forEach( email -> this.emailSenderRepository.send(email) );
    }

    private Email emailFor(Employee employee) {
        String message = String.format("Happy birthday, dear %s!", employee.getFirstName());
        return new Email(employee.getEmail(), "Happy birthday!", message);
    }

}