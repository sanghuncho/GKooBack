package com.gkoo.service.impl;

import java.util.HashMap;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gkoo.exception.EmailSendException;
import com.gkoo.service.EmailSendService;
import util.DateUtil;
import util.TimeStamp;

@Service
public class EmailSendServiceImpl implements EmailSendService {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static final String SMTP_UERNAME = "gkoosoft@gmail.com";
    private static final String MANAGER_EMAIL = "moondrive81@naver.com";
    private static final String SMTP_PASSWORD = "fvcmbdxqrmrzdjpy";
    private static final char LINE_BREKER = '\n';
    
    @Override
    public ResponseEntity<?> requestAuctionDeposit(HashMap<String, Object>[] data) {
        String subject = "GKoo 경매 보증금 신청";
        String userid = data[0].get("userid").toString();
        
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("ID:" + userid + " 경매 보증금 확인요망." + LINE_BREKER);
        sendEmailManager(subject, textBuilder.toString());
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> requestAuctionBid(HashMap<String, Object>[] data) {
        String userid = data[0].get("userid").toString();
        String productUrl = data[1].get("productUrl").toString();
        Double bidValue = Double.parseDouble(data[2].get("bidValue").toString());
        String message = data[3].get("auctionMessage").toString();
        
        String formattedLocalDate = DateUtil.formattedLocalDate(TimeStamp.getRequestDate());

        String subject = "GKoo 경매 비딩 신청 : " + userid + ", " + formattedLocalDate;
        
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("1. ID: " + userid);
        textBuilder.append(LINE_BREKER);
        
        textBuilder.append("2. Product URL: " + productUrl);
        textBuilder.append(LINE_BREKER);
        
        textBuilder.append("3. Maximal bidding: " + bidValue);
        textBuilder.append(LINE_BREKER);
        
        textBuilder.append("4. auction message: " + message);
        textBuilder.append(LINE_BREKER);
        
        sendEmailManager(subject, textBuilder.toString());
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

    
    private static void sendEmailManager(String subject, String text) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.mime.charset", "utf-8");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SMTP_UERNAME, SMTP_PASSWORD);
                    }
                });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(SMTP_UERNAME));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(MANAGER_EMAIL)
            );
            message.setSubject(subject, "UTF-8");
            message.setText(text, "UTF-8");

            Transport.send(message);
        } catch (MessagingException e) {
            String messageErroe = "Error sending Email: subject: " + subject + "/ text: " + text;
            LOGGER.error(message, e);
            throw new EmailSendException(messageErroe, e);
        }
    }
    
    //이메일 테스트
    public static void main(String[] args) {
        String subject = "GKoo 이베이 경매 신규 보증금 신청";
        String userid = "m";
        String text = "회원: " + userid + " 경매 보증금 신청을 했습니다. 보증금 확인후 경매권한을 업데이트해주세요.";
        sendEmailManager(subject, text);
    }
}