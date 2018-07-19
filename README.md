# nsfbbt
hackathon project  
https://bbtnsf.mybluemix.net/  

Static html or other assets will go in src/main/webapp.

Any back-end code goes in src/main/java


notes:

to install IBM CLI use:  
`curl -fsSL https://clis.ng.bluemix.net/install/linux | sh`  


Login and use correct region:  
`ibmcloud login -a api.ng.bluemix.net`   
`ibmcloud api https://api.ng.bluemix.net`  

Build:  
`mvn package`  

Push to ibm cloud:    
`ibmcloud target --cf`  
`ibmcloud cf push`  


Turn in:
Zip File - with all files
Readme.md - How to build, deploy, run
Slide deck