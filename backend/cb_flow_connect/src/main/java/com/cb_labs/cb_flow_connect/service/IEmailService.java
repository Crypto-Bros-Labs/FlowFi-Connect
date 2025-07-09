package com.cb_labs.cb_flow_connect.service;

public interface IEmailService {
    void sendEmail(String to, String subject, String text);
}
