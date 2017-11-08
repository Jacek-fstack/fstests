package testpackage;
import javax.mail.*;
import javax.mail.search.SubjectTerm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

/* Messy class found on the interWebZ, we should probably rewrite it since we're going to use it more often to check mails */
public class MailHelper {
	
	public static String registrationURL;

	
	public static void confirmAccoubtWithEmail() throws Exception {
		Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "jacektestingacc@gmail.com",
                    "NewTestingAccPassword");

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);

            System.out.println("Total Message:" + folder.getMessageCount());
            System.out.println("Unread Message:"
                    + folder.getUnreadMessageCount());
            
            Message[] messages = null;
            boolean isMailFound = false;
            Message mailFromGod= null;

            //Search for mail from God
            for (int i = 0; i < 5; i++) {
                messages = folder.search(new SubjectTerm(
                        "Welcome to Formstack: Verify Your Account"),
                        folder.getMessages());
                //Wait for 10 seconds
                if (messages.length == 0) {
                    Thread.sleep(10000);
                }
            }

            //Search for unread mail from God
            //This is to avoid using the mail for which 
            //Registration is already done
            for (Message mail : messages) {
                if (!mail.isSet(Flags.Flag.SEEN)) {
                    mailFromGod = mail;
                    System.out.println("Message Count is: "
                            + mailFromGod.getMessageNumber());
                    isMailFound = true;
                }
            }

            //Test fails if no unread mail was found from God
            if (!isMailFound) {
                throw new Exception(
                        "Could not find new mail from FS :-(");
            
            //Read the content of mail and launch registration URL                
            } else {
                String line;
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(mailFromGod
                                .getInputStream()));
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                //System.out.println(buffer);

                //Your logic to split the message and get the Registration URL goes here
                registrationURL = buffer.toString().split("href=3D\"")[1].split("\"")[0].replace("=", "");
                System.out.println(registrationURL);                            
            }
   
	}

	public static String getString(){
		return registrationURL;
	}
}        