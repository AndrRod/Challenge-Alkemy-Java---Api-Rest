package com.Alkemy.Challenge.Java.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {
    @Autowired
    SendGrid sendGrid;

    public Response sendEmail(String email) {
    Mail mail = new Mail(new Email("rodrigueza.federacion@gmail.com"),
            "Saludos, Challenge Alkemy",
            new Email(email),
            new Content("text/plain", "Te quiero saludar y felicitar por registrarte"));

//        Mail mail = new Mail(new Email("rodrigueza.federacion@gmail.com"),
//                emailRequest.getSubject(),
//                new Email(emailRequest.getTo()),
//                new Content("text/plain", emailRequest.getBody()));

    mail.setReplyTo(new Email("rodrigueza.federacion@gmail.com"));
    Request request = new Request();
    Response response = null;
    try {
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        response = this.sendGrid.api(request);

    }catch (IOException ex){
        System.out.println(ex.getMessage());
    }
//        System.out.println(response.getStatusCode());
//        System.out.println(response.getBody());
//        System.out.println(response.getHeaders());
    System.out.println(response.getClass());
    return response;
}

}
