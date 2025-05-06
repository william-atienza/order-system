<!DOCTYPE html>
<html>
<head>
    <title>PHP &amp; MySQL</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
<h2>OAccountId: 0KM5EP8SSDZAK</h2>
<table>
    <tr>
        <td>Id</td>
        <td>Created_On</td>
        <td>Shipping Details</td>
    </tr>
    <?php
    $orders = file_get_contents('http://localhost:8081/silverspin/api/orders?accountId=0KM6RTM7X72MN&page=1&size=5&sortBy=id&sortDir=DESC;');
    //echo $orders;
    ?>
    <?php foreach ($orders as $order) : ?>
        <tr>
        <td><?= $order['orderId'] ?></td>
        <td><?= $order ?></td>
        <td>Shipping Details</td>
        </tr>";
    <?php endforeach; ?>

</table>
</body>
</html>

<?php


?>

