<?php
if (isset($_POST['name-cat'])) {
    var_dump($_POST['name-cat']);
    $headers = [
        'Content-Type' => 'application/json'
        #'Accept' => 'application/json'
    ];
    $postParameter = array(
        'name' => $_POST['name-cat'],
        'description' => $_POST['description-cat']
    );
    $category = json_encode($postParameter);

    $cUrl = curl_init('http://localhost:8083/silverspin/api/categories');
    curl_setopt($cUrl, CURLOPT_HTTPHEADER, array("Content-type: application/json"));
    curl_setopt($cUrl, CURLOPT_CUSTOMREQUEST, "POST");
    curl_setopt($cUrl, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($cUrl, CURLOPT_POSTFIELDS, $category);

    $response = curl_exec($cUrl);
    curl_close($cUrl);
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1 shrink-to-fit=no, viewport-fit=cover">
    <meta name="color-scheme" content="light dark">
    <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
    >
    <title>Super Grocery - Category</title>

</head>
<body>

<header class="main-header" id="main-header"></header>
<main>
    <div class="container">
        <div role="tabs">
            <section>
                <figure>
                    <h4>Add Category</h4>
                    <form action="" method="POST">
                        <fieldset>
                            <input type="text" id="name-cat" name="name-cat" placeholder="Category name" aria-label="Category name"
                                   required>
                            <textarea  id="description-cat" name="description-cat" placeholder="Description" aria-label="description"
                                      required></textarea>
                            <button type="submit">Save</button>
                        </fieldset>
                    </form>
                </figure>

                <!--
                <figure>
                    <h4>Add Sub-Category</h4>
                    <form>
                        <fieldset>
                            <input type="text" name="name-subcat" placeholder="Sub-category name" aria-label="Product name"
                                   required>
                            <textarea name="description-subcat" placeholder="Description" aria-label="description"
                                      required></textarea>
                            <button type="submit">Save</button>
                        </fieldset>
                    </form>
                </figure>
                -->
            </section>
        </div>
    </div>
</main>
</body>
</html>
