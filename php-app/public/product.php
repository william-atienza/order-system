<?php
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
                <h4>Add Product</h4>
                <form>
                    <fieldset>
                        <input type="text" name="name" placeholder="Product name" aria-label="Product name"
                               required>
                        <textarea name="description" placeholder="Description" aria-label="description"
                                  required></textarea>
                        <input type="text" name="imageLocation" placeholder="Image link/url" aria-label="Image link/url"
                               required>
                        <select name="category" aria-label="Select product category..." required>
                            <option selected disabled value="">
                                Select product category...
                            </option>
                            <option>Bakery</option>
                            <option>Beverage</option>
                            <option>Dairy Product</option>
                            <option>Frozen</option>
                            <option>Fruits & Vegetables</option>
                        </select>
                        <select name="subCategory" aria-label="Select product sub-category..." required>
                            <option selected disabled value="">
                                Select product sub-category...
                            </option>
                            <option>Bakery</option>
                            <option>Beverage</option>
                            <option>Dairy Product</option>
                            <option>Frozen</option>
                            <option>Fruits & Vegetables</option>
                        </select>
                        <div class="grid">
                            <input  class="decimal" type="text" name="price" placeholder="0.00" aria-label="Price"
                                    required>
                            <input type="text" name="quantity" placeholder="0" aria-label="quantity"
                                   required>
                        </div>
                        <button type="submit">Save</button>
                    </fieldset>
                </form>
            </section>
        </div>
    </div>
</main>
</body>
</html>
