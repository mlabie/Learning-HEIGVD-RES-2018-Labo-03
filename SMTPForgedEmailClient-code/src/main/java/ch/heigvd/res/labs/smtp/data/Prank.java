package ch.heigvd.res.labs.smtp.data;

import java.security.acl.Group;

public class Prank {
    private GroupOfVictims group;
    private ForgedEmail mail;

    Prank(GroupOfVictims group, ForgedEmail mail){
        this.group = group;
        this.mail = mail;
    }

    // TODO : Getters & Setters

    void PrankThemAll(){}
}
