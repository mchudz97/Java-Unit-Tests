package DB;

import DB.AdminTools;
import DB.EmailChecker;
import proj2.entities.Client;

import java.util.List;

public class AdminToolsFake implements AdminTools {

    private EmailChecker emailChecker;

    public AdminToolsFake(EmailChecker emailChecker){

        this.emailChecker = emailChecker;

    }
    @Override
    public void sendMessage(String sender, Client receiver, String description) {

        if(!checkIsActive(sender)){

            throw new IllegalArgumentException(sender + " is not valid adress.");

        }

        String receiverMail =receiver.getEmailAdress();
        if(!checkIsActive(receiverMail)){

            throw new IllegalArgumentException(receiverMail + " is not valid adress.");

        }

        if(description == null || description == ""){


            throw new IllegalArgumentException("Invalid description.");

        }



    }

    @Override
    public void sendBroadcastMessage(String sender, List<Client> clients, String description) {

        for(int i = 0; i < clients.size(); i++){

            sendMessage(sender, clients.get(i), description);

        }


    }

    @Override
    public boolean checkIsActive(String email) {

        return this.emailChecker.isActive(email);

    }


}
