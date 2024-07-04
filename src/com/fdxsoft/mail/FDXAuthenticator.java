/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdxsoft.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


/**
 *
 * @author fmartinez
 */
public class FDXAuthenticator extends Authenticator {
    String _user = "";
    String _pass = "";

    public FDXAuthenticator(String user, String password) {
        _user = user;
        _pass = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(_user, _pass);
    }
}
