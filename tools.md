# How install the following tools on linux?

## curl
		-sudo apt install curl
## vim 
		-sudo apt update
		-sudo apt search vim
		-sudo apt install vim
## git  
		-sudo apt update
		-sudo apt install git

###		To update the last version of git

		-sudo add-apt-repository -y ppa:git-core/ppa
		-sudo apt update
		-sudo apt install git -y
## zsh 
		-sudo apt update
		-sudo apt install zsh
## tmux 
		-sudo apt update
		-sudo apt install tmux
## jq
		-sudo apt update
		-sudo apt install -y jq


## java and maven
	
### 	Java
		sudo apt update
		sudo apt install default-jdk

### Maven

		//next, download maven from sourcepage https://maven.apache.org/index.html
		//After, extract the content int the /opt directory 

		sudo tar xf carpeta/archivo.tar -C /opt

###		setup environment variables
		sudo nano /etc/profile.d/maven.sh

		//Copy on maven.sh

		export JAVA_HOME=/usr/lib/jvm/default-java
		export M2_HOME=/opt/maven
		export MAVEN_HOME=/opt/maven
		export PATH=${M2_HOME}/bin:${PATH}

		//Make executable maven.sh
		
		sudo chmod +x /etc/profile.d/maven.sh

		//Load envirnment variables
		source /etc/profile.d/maven.sh

		mvn -version
