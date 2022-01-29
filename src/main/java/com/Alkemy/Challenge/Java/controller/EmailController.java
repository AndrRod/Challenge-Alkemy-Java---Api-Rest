package com.Alkemy.Challenge.Java.controller;

import com.Alkemy.Challenge.Java.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


import com.sendgrid.Response;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EmailController {

//    CONTROLLER CREADO PARA PRUEBA DEL ENVIO DE CORREO
//    @Autowired
//    private EmailService emailService;
//
//    @PostMapping("/sendEmail")
//    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest){
//        Response response= emailService.sendEmail(emailRequest);
//
//        if(response.getStatusCode() == 200 || response.getStatusCode()== 202){
//            return new ResponseEntity<>("envio de email exitoso", HttpStatus.OK);
//        }
//        System.out.println(emailService.sendEmail(emailRequest).getBody());
//        return new ResponseEntity<>("falla en el envio del email", HttpStatus.NOT_FOUND);
//    }

}
