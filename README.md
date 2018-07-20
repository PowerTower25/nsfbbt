# nsfbbt
hackathon project  
Client Experience:
https://bbtnsf.mybluemix.net/  

BB&T employee experience:
https://bbtnsf.mybluemix.net/agentchat.html


Setup notes:

To install IBM CLI use:  
`curl -fsSL https://clis.ng.bluemix.net/install/linux | sh`  

Login and use correct region:  
`ibmcloud login -a api.ng.bluemix.net`   
`ibmcloud api https://api.ng.bluemix.net`  

Build:  
`mvn package`  

Push to ibm cloud:    
`ibmcloud target --cf`  
`ibmcloud cf push`  

To run locally you can use
'mvn spring-boot:run'

Turn in:
Zip File - with all files available at https://github.com/sutherlandpb/nsfbbt/archive/master.zip
Readme.md - This file: How to build, deploy, run
Slide deck - https://bbtnsf.mybluemix.net/ppt.html live or download https://bbtnsf.mybluemix.net/Business-Connect-X-press-Presentation-v4.pptx
