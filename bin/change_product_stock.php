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
$productStock = $_GET['Stock'];
$productStockAsInt = (int)$productStock;

/* We update the stock level of the Product with a matching ID */
$sql = "UPDATE Products SET Stock=$productStockAsInt WHERE ID=$productID";

if (mysqli_query($conn, $sql)) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . mysqli_error($conn);
}
$conn->close();
?>