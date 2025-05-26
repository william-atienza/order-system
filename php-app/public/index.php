<?php
#phpinfo();
$headers = [
    'User-Agent: Sample Web App'
    #'Content-Type' => 'application/json',
    #'Accept' => 'application/json'
];
$url = curl_init('http://localhost:8081/silverspin/api/accounts');
curl_setopt($url, CURLOPT_HTTPHEADER, $headers);
curl_setopt($url, CURLOPT_CUSTOMREQUEST, "POST");
curl_setopt($url, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($url);
curl_close($url);
$data = json_decode($response);
$id = (string) $data-> id;
#var_dump($data);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="color-scheme" content="light dark">
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
    >
    <title>Order System</title>
</head>
<body>
    <main class="container">
        <h2><!-- Add grocery icon here--> Super Grocery</h2>
        <nav>

            <ul>
                <li><a href="#">Categories</a></li>
                <li><a href="#">Promotions</a></li>
                <li><a href="#">More</a></li>
                <li><input type="search" id="search" name="search" placeholder="Search products here"></li>
                <li><!-- Cart icon here --> Cart</li>
            </ul>

            <ul>


                <!--
                <li><a href="#">Services</a></li>

                <li><button class="secondary">Place An Order</button></li>
                -->
            </ul>
        </nav>
        <h2>Account: <?php echo $id ?></h2>
        <section id="promotionBanner"><!-- Scrollable promotional banner here--></section>
        <section id="flashDeals">
            <hgroup>
                <h4> Flash deals </h4>
                <h5> While stocks last! </h5>
            </hgroup>
            <button class="contrast" data-target="modal-example" onclick="toggleModal(event)">
                Add to Cart
            </button>
        </section>
        <section id="recommended">
            <h4> Recommended for you </h4>
        </section>
        <section id="whatshot">
            <h4> What's hot now </h4>
        </section>

    </main>
</body>
</html>
