Hi all! this is my first java project! here i will push the code of my home medial player who stream .mp4 files over your private home network!
in that way you will be able to load your .mp4 file and watch them with any device connected on your wi-fi! 

Below you'll find a guide on how to use this media server. If you are not a technical user, follow the first guide; if you know what you are doing, just go below, you'll find a short version that just tells you briefly what you have to do.

DISCLAMER: The purpose of this project is to give the user the possibility to customise and add any kind of functionality; this is why this guide focuses on creating the environment to be able to work on the project.

Currently, I’ve set a maximum of 10 active users at the same time. You can modify this from the application.properties file.

I’ve also imposed a maximum of 1 hour before the server stops if it doesn’t receive any requests from the HTTP connection. You can change this from the application.properties file.

You can change the port from the application.properties file, the default one is port 8080.

******Home Media Player – Easy Installation Guide (For Non-Technical Users)******
This guide helps you download, set up, and run the Home Media Player on your computer – whether you’re using Windows, macOS, or Linux (In my case, Mint).
Once installed, the app will stream your .mp4 video files over your home Wi-Fi. You’ll be able to watch your movies from any phone, tablet, or computer on the same network!

Base Assumption: In this guide, I’ll assume that the folder is located on your desktop.

Step 1.
Option A: Download ZIP (Easiest)
- Go to the GitHub page: https://github.com/skyjager51/Home-Media-Player
- Click the green Code button → Download ZIP.
- Extract the ZIP to your desktop. You’ll now see a folder called Home-Media-Player.

Option B: Clone with Git (Advanced)
If you already have Git installed, open your terminal and run: 
- git clone https://github.com/skyjager51/Home-Media-Player.git


Step 2.
Install Java 17 (JDK 17) via Terminal (This project has been developed using jdk 17).

On Windows (using Chocolatey)
- Open PowerShell as Administrator
- Install Chocolatey (only once):
- 	Set-ExecutionPolicy Bypass -Scope Process -Force; `
- 	[System.Net.ServicePointManager]::SecurityProtocol = `
- 	[System.Net.ServicePointManager]::SecurityProtocol -bor 3072; `
- 	iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
- Install JDK 17:
- 	choco install temurin17 -y


On macOS (using Homebrew)
- Open the Terminal
- Install Homebrew (only once):
- 	/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
- Install Java 17:
- 	brew install temurin@17


On Linux (Mint)
- sudo apt update
- sudo apt install openjdk-17-jdk -y


Check Java is Installed:
- java -version

It should say something like: openjdk version "17..."


Step 3.
Install Maven

On Windows:
run in the powershell:
- choco install maven -y


On macOs:
run in the terminal:
- brew install maven

  
On Linux (Mint):
run in the terminal:
- sudo apt install maven -y


Step 4.
Install VS Code.
Go to: https://code.visualstudio.com/ and download the installer for your OS. Follow the installation steps.


Step 5.
Install Java & Spring Boot Extensions in VS Code.
- Open VS Code
- Go to the Extensions tab (left sidebar or press Ctrl+Shift+X)
- Search and install:
- 	Java Extension Pack
- 	Spring Boot Extension Pack


Step 6.
Open the Project in VS Code

Option A: From VS Code
- Click File → Open Folder
- Select the Home-Media-Player folder

* A notification may pop up in the bottom-right asking to configure Java or Maven. Just click Yes or Allow on anything it shows.

Option B: From Terminal

In your terminal:
- cd Desktop/Home-Media-Player
- code .


Step 7.
Adding .mp4 files to the project to run them.

Now, in Visual Studio Code on the right, you can see the folder of the project with all its components. To add an MP4 file, navigate to the src folder, then the main folder, then the resources folder, and finally the videos folder. Here, you can add your files. As you can see, there’s a sample black video. If you still haven’t got videos to load, don’t delete it. You should use it to test if the server works correctly once running in the network.


Step 8.
Running your application.

The esiest way is to just click the triagle in the top right of the vscode page, it should pop a new termial window that shows what it the server doing.
but you can also:
- Press Ctrl + Shift + P (or Cmd + Shift + P on macOS), and type: Spring Boot: Run
or:
- in the terminal (you has to be in the project folder): ./mvnw spring-boot:run -> (If that doesn’t work, try mvn spring-boot:run)


Step 9.
Access Your Media Player

Once the app is running, open a browser on any device connected to your home Wi-Fi.
In the address bar, type:
 - http://<your-computer-ip>:8080

example -> http://192.168.1.56:8080

You can find your IP by opening the terminal and typing:
- macOS/Linux: ifconfig | grep inet
- Windows: ipconfig

if you want connect to the same device that is hositng the server, you can just type localhost:8080


We are all done! of you go to the page, you should see a list of all the mp4 files in the videos folder and a video player under them, if you click one of them, you should 
be able to see the video.


******Home Media Player – Easy Installation Guide (For Technical Users)******
To run this, you can clone the project in any folder in your pc.
If you don't have them, download the packages for jdk 17 and maven, then open the project in your ide (if you use Intellij maven is pre-installed)
To see videos, add mp4 files in the vidoes folder and then run the program. Then, in your browser, search for the ip of your hosting device and the port.




