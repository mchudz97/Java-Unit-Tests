package proj2.myMocks;

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
    public void sendMessage(String sender, Client receiver) {

        if(!checkIsActive(sender)){

            throw new IllegalArgumentException(sender + " is not valid adress.");

        }

        if(!checkIsActive(receiver.getEmailAdress())){

            throw new IllegalArgumentException(receiver.getEmailAdress() + " is not valid adress.");

        }



    }

    @Override
    public void sendBroadcastMessage(String sender, List<Client> clients) {

        for(int i = 0; i < clients.size(); i++){


            sendMessage(sender, clients.get(i));

        }


    }

    @Override
    public boolean checkIsActive(String email) {

        return this.emailChecker.isActive(email);

    }


}
