<?php
$servername = "localhost";
$username = "mathewba_admin";
$password = "Df54d64fd";
$dbname = "mathewba_NBgardens";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
	die("Connection failed: " . $conn->connect_error);
}

/* Instantiate the variables used to add an entry to the database */
$productID = $_GET['ID'];

/* Find the Product with the matching ID */
if ($result = $conn->query("SELECT * FROM Products WHERE ID = '$productID'")) {

	/* Find the sum of the current stock and the amount to alter it by */
	if ($result->num_rows > 0) {
	
		while($row = $result->fetch_assoc()) {
		echo "" . $row["ID"] . ":" . $row["Name"] . ":" . $row["Stock"];
		}
	} else {
		echo "No entry found";
	}
	
	$result->close();
}

$conn->close();
?>