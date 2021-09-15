package controlador;

import entidades.Usuarios;
import facades.UsuariosFacade;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import sun.management.snmp.util.MibLogger;

@Named
@RequestScoped
public class emailControlador {

    @EJB
    private UsuariosFacade usuariosFacade;
    private Usuarios usuarios;

    private String para;
    private String asunto;
    private String texto;

    @PostConstruct
    private void init() {
        usuarios = new Usuarios();

    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void correo() throws MessagingException {

//        String user = "dialbega9966@gmail.com";
//        String pass = "esteban4323";
        String user = "maracawasena@gmail.com";
        String pass = "Maracawapro1";
        String host = "smtp.gmail.com";

        try {

            Properties p = new Properties();

            p.setProperty("mail.smtp.host", host);
            p.setProperty("mail.smtp.port", "587");
           // p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.starttls.requiried", "false");

            p.setProperty("mail.smtp.user", user);
            p.setProperty("mail.smtp.password", pass);
            p.setProperty("mail.smtp.auth", "true");

            Session s = Session.getDefaultInstance(p);

            BodyPart estilos = new MimeBodyPart();
            estilos.setContent(texto, "text/html");

            Multipart multi = new MimeMultipart();
            multi.addBodyPart(estilos);

            MimeMessage mime = new MimeMessage(s);

            mime.setFrom(new InternetAddress(para));
            mime.setRecipient(Message.RecipientType.TO, new InternetAddress(para));

            mime.setSubject(asunto);

            mime.setContent(multi, "text/html; charset=utf-8");

            Transport t = s.getTransport("smtp");

            t.connect(host, user, pass);

            t.sendMessage(mime, mime.getAllRecipients());

            t.close();

        } catch (MessagingException e) {
            throw e;
        }

    }

    public void correoMasivo() throws MessagingException {

        String user = "dialbega9966@gmail.com";
        String pass = "esteban4323";
        String host = "smtp.gmail.com";
        List<Usuarios> paraM = usuariosFacade.findAll();

        try {

            Properties p = new Properties();

            p.setProperty("mail.smtp.host", host);
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.starttls.requiried", "false");

            p.setProperty("mail.smtp.user", user);
            p.setProperty("mail.smtp.password", pass);
            p.setProperty("mail.smtp.auth", "true");

            Session s = Session.getDefaultInstance(p);
            texto="<h1 style=\"color:#C7FF33;\">"+texto+"</h1>";
            BodyPart estilos = new MimeBodyPart();
            estilos.setContent(texto, "text/html");

            Multipart multi = new MimeMultipart();
            multi.addBodyPart(estilos);
            MimeMessage mime = new MimeMessage(s);

            mime.setFrom(new InternetAddress(user));
            for (Usuarios listaCM : paraM) {
                mime.addRecipient(Message.RecipientType.TO, new InternetAddress(listaCM.getCorreo()));
            }

            mime.setSubject(asunto);

            mime.setContent(multi,"text/html; charset=utf-8");

            Transport t = s.getTransport("smtp");

            t.connect(host, user, pass);

            t.sendMessage(mime, mime.getAllRecipients());

            t.close();

            texto = null;

            asunto = null;

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Se envió correctamente"));

        } catch (MessagingException e) {
            throw e;
        }

    }

    public void recuperarPass() {

        Usuarios user = null;
        int num;
        String newPass;
        List<Usuarios> lista = null;

        try {

            lista = usuariosFacade.findAll();

            String c;
            for (Usuarios u : lista) {
                c = u.getCorreo();
                if (c.equals(para)) {
                    user = u;
                }
            }

            if (user != null) {

                this.asunto = "Recuperacion";

                num = (int) (Math.random() * 2345);
                newPass = "as" + num + "ds";

                user.setContrasena(newPass);

                usuariosFacade.edit(user);

                this.texto = "<h1 style=\"color:#0C0;\">Nueva contraseña: </h1>" + newPass;

                correo();

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Se envió el correo"));

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "EL usuario no existe"));
            }

        } catch (MessagingException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "ERRRRRORRRR"));
        }

    }

    public void prueba() {

        try {

            this.asunto = "asdasd";

            this.texto = "sdfsdf";

            correo();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Se envió el correo"));

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Error"));
        }

    }

}
