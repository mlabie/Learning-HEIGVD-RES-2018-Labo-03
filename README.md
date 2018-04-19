# FORGED E-MAIL SMTP CLIENT

## Description

The Forged e-mail SMTP client is a client that sends forged e-mail to a Group of Victims from a sender that himsilf is a Victim aswell.

## Set up a mock SMTP server (with Docker)
If you want to test the client before using it for a real campaign, please read the following section to set up a mock SMTP server on Docker.

### What is a mock SMTP server ?
A mock SMTP server is a server who make a SMTP server simulation. The mock server doesn't forward the received emails. All the emails are kept in the server and the administrator can read the structure of the mail. A mock server is used by developers to see what their application send with no impact.

### Setting up
In our case, we choose to use a [MockMock](https://github.com/tweakers-dev/MockMock) server. We choose it because it is easy to use and there is a GUI very user-friendly.

First, download the java file of the MockMock server [here](https://github.com/tweakers-dev/MockMock/blob/master/release/MockMock.jar?raw=true).

Then, you can run the mock server by running : ``java -jar MockMock.jar``. By default, MockMock use the port **25** (for SMTP) and **8282** (for web interface). If you want to change it, you can run the following command : ``java -jar MockMock.jar -p 2525 -h 8080``. This will change the SMTP port to **2525** and the port of the web interface to **8282**.

## Running a Prank campaign
To run a Prank campaign, make the following instructions :

- Edit the ``config.properties`` file and update the parameters as you want. Make sure to have the same value for the parameter ``smtpServerPort`` and your SMTP port of your mock server
- Edit the ``victims.utf8`` file to add all your victims list. The sender of the mails is a victim too.
- Edit the ``message.utf8`` file to add all your mail to spam. 
  - Make sure to start the subject of the mail by ``Subject: ``
  - Make sure to seperate your mails by ``==``
- Execute the file and prank them all !