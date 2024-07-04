/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdxsoft.testing;

import com.fdxsoft.mail.FDXMessage;
import com.fdxsoft.mail.FDXTransport;

/**
 *
 * @author federico
 */
public class Tester {

    public static void main(String[] args) {
        FDXMessage myMsg = new FDXMessage();
        FDXTransport myTR = new FDXTransport();

        try {
            myMsg.setFromName("TesterMail FDXSOFT SA de CV");
            //Parametros del envio
            myMsg.setTo("fedemtzesc@hotmail.com");
            myMsg.setCc("fdx.soft.mx@aol.com");
            myMsg.setCc("fedemtzesc@yahoo.com.mx");
            myMsg.setBcc("fdx.soft.mx@gmail.com");
            myMsg.setSubject("Descripcion del Asunto");
            myMsg.setBody("<h1 style='color:red'>Este es el cuerpo del mi mensaje en HTML</h1>");
            myMsg.addAttachment("/home/federico/fdxsoft.txt");
            
            myTR.sendMail(myMsg);
            System.out.println("Correo enviado con exito!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
