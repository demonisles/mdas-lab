<html> 
<title> Task List</title> 
<body> 
	<table border='1' cellspacing='2%' width='80%'> 
		<tr><th>Name</th><th>Pattern</th><th>Desc</th> <th>operate</th></tr>
		<#list tasks as task>
		<tr><td>${task.name}</td><td>${task.schedulePattern}</td><td>${task.desc!""}</td><td></td></tr>
		</#list>
	</table> 
</body> 
</html>