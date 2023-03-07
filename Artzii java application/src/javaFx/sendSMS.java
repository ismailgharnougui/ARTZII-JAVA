/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import models.Livreurs;

/**
 *
 * @author USER
 */
public class sendSMS {
    public static final String ACCOUNT_SID = "AC86ff130f3361a7df2c4ec439d9629689";
    public static final String AUTH_TOKEN = "9001cbbbc684cc6838d1a83685b816a9";

    public static void sendSMS(Livreurs l) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+21655809190"),
                new PhoneNumber("+15074769268"),
                "Commande effectuee a un livreur : nom:" + l.getNom() +" est le livreur disponible ").create();


        System.out.println(message.getSid());
    }
}