<!DOCTYPE html>
<html>
<head>
    <title>Webchat Playground</title>
    <script type="text/javascript">
		function chat() {
		    window.location = "clientchat.html?name=" + encodeURI(document.getElementById("name").value) 
		}
	</script>
</head>
<body>
Start a Chat
<input type="text" id="name" length="100" value="">
<button type="button" onclick="chat()">Chat</button>
<br>
or
<br>
<br>

<a href="listen.html">Listen for Chat requests</a>


</body>
</html>