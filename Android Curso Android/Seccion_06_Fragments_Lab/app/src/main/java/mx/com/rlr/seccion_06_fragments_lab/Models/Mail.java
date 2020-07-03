package mx.com.rlr.seccion_06_fragments_lab.Models;

import mx.com.rlr.seccion_06_fragments_lab.Utils.Util;

public class Mail {

    private String subject;
    private String message;
    private String email;
    private String color;

    public Mail(String subject, String message, String email) {
        this.subject = subject;
        this.message = message;
        this.email = email;
        this.color = Util.getRandomColor();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String senderName) {
        this.email = senderName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
