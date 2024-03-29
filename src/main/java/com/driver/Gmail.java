package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Gmail extends Email {

    public class MailContent{
        Date date;
        String sender;
        String message;

        public MailContent(Date date,String sender,String message){
            this.date=date;
            this.sender=sender;
            this.message=message;
        }
    }
    int inboxCapacity; //maximum number of mails inbox can store
    int currentMailCount=0;
    ArrayList<MailContent> inboxList;
    ArrayList<MailContent> trash;

    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        inboxList=new ArrayList<>();
        trash=new ArrayList<>();
        this.inboxCapacity=inboxCapacity;
    }

    public Gmail(){

    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(currentMailCount==inboxCapacity)
        {
            MailContent oldestMail=inboxList.remove(0);
            trash.add(oldestMail);
            currentMailCount--;
        }
        inboxList.add(new MailContent(date,sender,message));
        currentMailCount++;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int index=-1;
        for(MailContent mail:inboxList)
        {
            if(mail.message.equals(message))
                break;
            index++;
        }
        if(index>=0){
            MailContent trashEmail=inboxList.remove(index);
            currentMailCount--;
            trash.add(trashEmail);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inboxList.size()==0)
            return null;
        return inboxList.get(inboxList.size()-1).message;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inboxList.size()==0)
            return null;
        return inboxList.get(0).message;

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        for(MailContent mail:inboxList)
        {
            Date currentDate=mail.date;
            if(currentDate.compareTo(start)>=0 && currentDate.compareTo(end)<=0)
                count++;
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inboxList.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash=new ArrayList();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;

    }
}
