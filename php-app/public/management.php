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
        [x-cloak] {
            display: none !important;
        }
        nav[role=tablist] {
            justify-content: flex-start;
            user-select: none;
            border-bottom: 1px solid var(--muted-border-color);
            margin-bottom: var(--spacing);
        }
        a[role=tab] {
            color: var(--form-element-disabled-border-color);
            border-bottom: 1px solid transparent;
            transition: all var(--transition);
            min-width: 120px;
            text-align: center;
            user-select: none;
        }
        a[role=tab].active {
            color: var(--contrast);
            border-radius: 0;
            border-bottom: 1px solid var(--contrast);
        }
        a[role=tab]:focus {
            background: transparent;
        }
        div.grid div {
            min-height: 200px;
            animation-duration: 0.45s;
            -webkit-animation-duration: 0.45s;
            animation-fill-mode: both;
            -webkit-animation-fill-mode: both;
            -webkit-animation-name: slideIn;
            animation-name: slideIn;
        }
        @keyframes slideIn {
            0% {
                transform: translateY(1rem);
                opacity: 0;
            }
            100% {
                transform:translateY(0rem);
                opacity: 1;
            }
            0% {
                transform: translateY(1rem);
                opacity: 0;
            }
        }
        @-webkit-keyframes slideIn {
            0% {
                -webkit-transform: transform;
                -webkit-opacity: 0;
            }
            100% {
                -webkit-transform: translateY(0);
                -webkit-opacity: 1;
            }
            0% {
                -webkit-transform: translateY(1rem);
                -webkit-opacity: 0;
            }
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
<main class="container">
    <hgroup>
        <h1><!--Add grocery icon here -->Super Grocery</h1>
        <h2>Inventory Management</h2>
    </hgroup>
    <section x-data="slider()">
        <article x-ref="slider" data-interval="6000">
            <nav role="tablist">
                <ul>
                    <li><a href="#" role="tab">Search product</a></li>
                    <li><a href="#" role="tab">Category</a></li>
                    <li><a href="#" role="tab">Product</a></li>
                    <li><a href="#" role="tab">Upload Excel file</a></li>
                </ul>
            </nav>
            <div class="grid">
                <div x-show="tab == 0" x-cloak>
                    <input type="search" id="search" name="search" placeholder="Search product">
                </div>
                <div x-show="tab == 1" x-cloak>
                    <?php require 'category.php';?>
                </div>
                <div x-show="tab == 2" x-cloak>
                    <?php require 'product.php';?>
                </div>
                <div x-show="tab == 3" x-cloak>
                    <input type="file">
                </div>
            </div>
        </article>
    </section>
</main>
<footer id="react-pen-footer" class="site-footer editor-footer">
    <div class="footer-left"></div>
    <div class="footer-right"></div>
</footer>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script defer src="https://unpkg.com/alpinejs@3.x.x/dist/cdn.min.js"></script>
<script>
    document.addEventListener('alpine:init', () => {
        Alpine.data('slider', () => ({

            // set initial tab
            tab: 0,

            // slider tabs
            tabs: [...document.querySelectorAll('nav[role=tablist] a[role=tab]')],

            init() {
                // initialize main function
                this.changeSlide()
            },

            // main function
            changeSlide() {
                let timeInterval = this.$refs.slider.dataset.interval;
                this.tabs[this.tab].setAttribute('class', 'active')

                // set interval to change slide
                let startInterval = () => {
                    this.tab = (this.tab < this.tabs.length - 1)? this.tab + 1 : 0;
                    this.tabs.forEach( (tab)=> {
                        (this.tab == this.tabs.indexOf(tab)) ?  tab.setAttribute('class', 'active') : tab.removeAttribute('class')
                    })
                }

                // start interval to change slide
                let slideInterval = setInterval(startInterval, timeInterval);

                // mouse over slider stops slide
                this.$refs.slider.onmouseover = () => {
                    if (slideInterval) {
                        clearInterval(slideInterval)
                        slideInterval = null;
                    }
                }

                // mouse out slider starts again slide
                this.$refs.slider.onmouseout = () => {
                    if (slideInterval === null) {
                        slideInterval = setInterval(startInterval, timeInterval);
                    }
                }

                // slider tabs click event
                this.tabs.forEach( (tab)=> {
                    tab.addEventListener('click', (e)=> {
                        e.preventDefault()
                        this.tab = this.tabs.indexOf(e.target)
                        this.tabs.forEach( (tab)=> {
                            (this.tab == this.tabs.indexOf(tab)) ?  tab.setAttribute('class', 'active') : tab.removeAttribute('class')
                        })
                    })
                })
            }
        }))
    })

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
