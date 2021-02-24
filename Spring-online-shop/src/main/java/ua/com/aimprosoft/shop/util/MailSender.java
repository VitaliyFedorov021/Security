package ua.com.aimprosoft.shop.util;

import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import ua.com.aimprosoft.shop.dto.AddressDto;
import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Component
public class MailSender
{
	@Resource(name = "mailProperties")
	private Properties mailProperties;

	public void sendOrderConfirmationEmail(final String email, final CartDto cartDto, final AddressDto addressDto)
	{
		try
		{
			final InternetAddress receiver = new InternetAddress(email);
			final String from = ApplicationConstant.SHOP_MAIL;
			final Session session = Session.getDefaultInstance(mailProperties);
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, receiver);
			message.setSubject(ApplicationConstant.MAIL_SUBJECT);
			message.setText(ApplicationConstant.MAIL_TEXT + "\n"
					+ ApplicationConstant.CODE + " - " + cartDto.getCode() + "\n"
					+ ApplicationConstant.PLACED_DATE_DB + " - " + cartDto.getPlacedDate() + "\n"
					+ ApplicationConstant.ADDRESS + " : " + addressDto.getCountry() + "\n"
					+ addressDto.getRegion() + ", " + addressDto.getTown() + ", " + addressDto.getStreet() + ", "
					+ addressDto.getPostalCode() + "\n"
					+ ApplicationConstant.TOTAL_PRICE_DB + " - " + cartDto.getTotalPrice());
			message.setSentDate(new Date());
			Transport.send(message);
		}
		catch (final MessagingException e)
		{
			e.printStackTrace();
		}
	}
}
