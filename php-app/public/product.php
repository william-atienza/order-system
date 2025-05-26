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
    <meta name="viewport" content="width=device-width, initial-scale=1 shrink-to-fit=no, viewport-fit=cover">
    <meta name="color-scheme" content="light dark">
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
    >
    <title>Super Grocery</title>
    <style>
        [role="tabs"] {
            display: flex;
        }

        [role="tabs"] section {
            display: flex;
            flex-wrap: wrap;
            width: 100%;
        }

        [role="tabs"] figure {
            flex-grow: 1;
            width: 100%;
            height: 100%;
            display: none;
        }

        [role="tabs"] [type="radio"]:checked + figure {
            display: block;
        }

        nav[role="tab-control"] label.active {
            color: var(--primary);
            cursor: pointer;
        }

        .decimal{
            height:50px;
            width:280px;
            font-size:20px;
            padding-left:50px;
            font-family:sans-serif;
        }
    </style>
</head>
<body>

<header class="main-header" id="main-header"></header>
<main>
    <div class="container">
        <hgroup>
            <h1><!--Add grocery icon here -->Super Grocery</h1>
            <h2>Inventory Management</h2>
        </hgroup>
        <nav role="tab-control">
            <ul>
                <li><a href="#"><label for="tab1">Search</label></a></li>
                <li><a href="#"><label for="tab2">Product</label></a></li>
                <li><a href="#"><label for="tab3">Upload Excel file</label></a></li>
            </ul>
        </nav>
        <div role="tabs">
            <section>
                <input hidden="hidden" type="radio" name="tabs" id="tab1" checked="checked" />
                <figure>
                    <h3>Search Product</h3>
                    <input type="search" id="search" name="search" placeholder="Search">
                </figure>

                <input hidden="hidden" type="radio" name="tabs" id="tab2" />

                <figure>
                    <h3>Add Product</h3>
                    <form>
                        <fieldset>
                            <input type="text" name="name" placeholder="Product name" aria-label="Product name"
                                   required>
                            <textarea name="description" placeholder="Description" aria-label="description"
                                      required></textarea>
                            <div class="grid">
                                <input  class="decimal" type="text" name="price" placeholder="0.00" aria-label="Price"
                                        required>
                                <input type="text" name="quantity" placeholder="0" aria-label="quantity"
                                       required>
                            </div>
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
                            <button type="submit">Save</button>
                        </fieldset>
                    </form>
                </figure>
                <input hidden="hidden" type="radio" name="tabs" id="tab3" />
                <figure>
                    <h3>Upload file</h3>
                    <input type="file">
                </figure>
            </section>
        </div>
    </div>
</main>
<footer id="react-pen-footer" class="site-footer editor-footer">
    <div class="footer-left"></div>
    <div class="footer-right"></div>
</footer>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script>
    const nodeList = document.querySelectorAll('nav[role="tab-control"] label');
    const eventListenerCallback = setActiveState.bind(null, nodeList);

    nodeList[0].classList.add('active'); /** add active class to first node  */

    nodeList.forEach((node) => {
        node.addEventListener("click", eventListenerCallback); /** add click event listener to all nodes */
    });

    /** the click handler */
    function setActiveState(nodeList, event) {
        nodeList.forEach((node) => {
            node.classList.remove("active"); /** remove active class from all nodes */
        });
        event.target.classList.add("active"); /* set active class on current node */
    }

    $('.decimal').keydown(function (e) {
        //Get the occurence of decimal operator
        var match = $(this).val().match(/\./g);
        if(match!=null){
            // Allow: backspace, delete, tab, escape and enter
            if ($.inArray(e.keyCode, [46,8, 9, 27, 13, 110]) !== -1 ||
                // Allow: Ctrl+A
                (e.keyCode == 65 && e.ctrlKey === true) ||
                // Allow: home, end, left, right
                (e.keyCode >= 35 && e.keyCode <= 39)) {
                // let it happen, don't do anything
                return;
            }  // Ensure that it is a number and stop the keypress
            else if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105 )&&(e.keyCode==190)) {
                e.preventDefault();
            }
        }
        else{
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                // Allow: Ctrl+A
                (e.keyCode == 65 && e.ctrlKey === true) ||
                // Allow: home, end, left, right
                (e.keyCode >= 35 && e.keyCode <= 39)) {
                // let it happen, don't do anything
                return;
            }
            // Ensure that it is a number and stop the keypress
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
        }
    });
    //Allow Upto Two decimal places value only
    $('.decimal').keyup(function () {
        if ($(this).val().indexOf('.') != -1) {
            if ($(this).val().split(".")[1].length > 2) {
                if (isNaN(parseFloat(this.value))) return;
                this.value = parseFloat(this.value).toFixed(2);
            }
        }
    });
</script>
</body>
</html>
