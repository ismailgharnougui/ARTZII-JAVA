/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;
 
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.time.LocalDate;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
 
/**
 *
 * @author rassa
 */
public class MailSender {
     public static void sendMail(String mail ,String contenu) throws Exception {
        String mailContent = "<!DOCTYPE html>\n"
                + "\n"
                + "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n"
                + "\n"
                + "<head>\n"
                + "	<title></title>\n"
                + "	<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n"
                + "	<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\n"
                + "	<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n"
                + "	<!--[if !mso]><!-->\n"
                + "	<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\" />\n"
                + "	<!--<![endif]-->\n"
                + "	<style>\n"
                + "		* {\n"
                + "			box-sizing: border-box;\n"
                + "		}\n"
                + "\n"
                + "		body {\n"
                + "			margin: 0;\n"
                + "			padding: 0;\n"
                + "		}\n"
                + "\n"
                + "		a[x-apple-data-detectors] {\n"
                + "			color: inherit !important;\n"
                + "			text-decoration: inherit !important;\n"
                + "		}\n"
                + "\n"
                + "		#MessageViewBody a {\n"
                + "			color: inherit;\n"
                + "			text-decoration: none;\n"
                + "		}\n"
                + "\n"
                + "		p {\n"
                + "			line-height: inherit\n"
                + "		}\n"
                + "\n"
                + "		@media (max-width:670px) {\n"
                + "			.icons-inner {\n"
                + "				text-align: center;\n"
                + "			}\n"
                + "\n"
                + "			.icons-inner td {\n"
                + "				margin: 0 auto;\n"
                + "			}\n"
                + "\n"
                + "			.row-content {\n"
                + "				width: 100% !important;\n"
                + "			}\n"
                + "\n"
                + "			.column .border {\n"
                + "				display: none;\n"
                + "			}\n"
                + "\n"
                + "			.stack .column {\n"
                + "				width: 100%;\n"
                + "				display: block;\n"
                + "			}\n"
                + "		}\n"
                + "	</style>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"background-color: #F5F5F5; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n"
                + "	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n"
                + "		style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #F5F5F5;\" width=\"100%\">\n"
                + "		<tbody>\n"
                + "			<tr>\n"
                + "				<td>\n"
                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\"\n"
                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                + "						<tbody>\n"
                + "							<tr>\n"
                + "								<td>\n"
                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "										class=\"row-content stack\" role=\"presentation\"\n"
                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;\"\n"
                + "										width=\"650\">\n"
                + "										<tbody>\n"
                + "											<tr>\n"
                + "												<td class=\"column column-1\"\n"
                + "													style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n"
                + "													width=\"100%\">\n"
                + "													<div class=\"spacer_block\"\n"
                + "														style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n"
                + "												</td>\n"
                + "											</tr>\n"
                + "										</tbody>\n"
                + "									</table>\n"
                + "								</td>\n"
                + "							</tr>\n"
                + "						</tbody>\n"
                + "					</table>\n"
                + "					<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\"\n"
                + "						role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                + "						<tbody>\n"
                + "							<tr>\n"
                + "								<td>\n"
                + "									<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "										class=\"row-content stack\" role=\"presentation\"\n"
                + "										style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #D6E7F0; color: #000000; width: 650px;\"\n"
                + "										width=\"650\">\n"
                + "										<tbody>\n"
                + "											<tr>\n"
…

[Message tronqué]  Afficher l'intégralité du message