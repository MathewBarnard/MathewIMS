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
$productID = null;
$productNameArray = explode("+", $_GET['Name']);
$productName = "";

for ($i; $i < count($productNameArray)) {
	$productName += $productNameArray[$i];
}

$productStock = $_GET['Stock'];
$productStockAsInt = (int)$productStock;

/* We count the number of products currently in the database so that we can assign a unique ID */
if ($result = $conn->query("SELECT * FROM Products")) {

    /* fetch the first row as result */
    $productID = mysqli_num_rows($result) + 1;
	
	echo "Entries: " . $productID . "<br>";

   /* close result set */
    $result->close();
} else {
	echo "Query failed!" . "<br>";
}
 
$sql = "INSERT INTO Products (ID, Name, Stock)
VALUES ('$productID', '$productName', '$productStockAsInt')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>