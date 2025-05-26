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
                <input hidden="hidden" type="radio" name="tabs" id="tab5" checked="checked" />
                <figure>
                    <h4>Add Category</h4>
                    <form>
                        <fieldset>
                            <input type="text" name="name-cat" placeholder="Category name" aria-label="Category name"
                                   required>
                            <textarea name="description-cat" placeholder="Description" aria-label="description"
                                      required></textarea>
                            <button type="submit">Save</button>
                        </fieldset>
                    </form>
                </figure>

                <input hidden="hidden" type="radio" name="tabs" id="tab6" />
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
            </section>
        </div>
    </div>
</main>
</body>
</html>
