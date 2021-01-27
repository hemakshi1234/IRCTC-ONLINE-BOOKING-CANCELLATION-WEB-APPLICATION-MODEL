<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {box-sizing: border-box;}
body {font-family: Verdana, sans-serif;}
.mySlides {display: none;}
img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;
}

/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 30px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active {
  background-color: #717171;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 2s;
  animation-name: fade;
  animation-duration: 2s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {font-size: 11px}
}

p.uppercase {
  text-transform: uppercase;
}

p.lowercase {
  text-transform: lowercase;
}

p.capitalize {
  text-transform: capitalize;
  margin: 20px;
}

h1 {
  text-align: center;
}

</style>
</head>
<body >

<jsp:include page="header.jsp" />

<div class="slideshow-container">

<div class="mySlides fade">
  <div class="numbertext">1 / 7</div>
  <img src="./image/TRAIN11.jpg" style="width:100%">
  <div class="text"><h5><b><i>By plane, by train, by boat it doesn't matter as long as I can travel.</i></b></h5></div>
</div>

<div class="mySlides fade">
  <div class="numbertext">2 / 7</div>
  <img src="./image/TRAIN22.jpg" style="width:100%">
  <div class="text"><h5><b><i>All aboard!</i></b></h5></div>
</div>


<div class="mySlides fade">
  <div class="numbertext">3/ 7</div>
  <img src="./image/TRAIN33.jpg" style="width:100%">
  <div class="text"><h5><b><i>With a view like this, I know I'm heading in the right direction.</i></b></h5></div>
</div>

<div class="mySlides fade">
  <div class="numbertext">4 / 7</div>
  <img src="./image/TRAIN44.jpg" style="width:100%">
  <div class="text"><h5><b><i>Wishing this was the Hogwarts Express.</i></b></h5></div>
</div>

<div class="mySlides fade">
  <div class="numbertext">5 / 7</div>
  <img src="./image/TRAIN55.jpg" style="width:100%">
  <div class="text"><h5><b><i>I love you to the moon and back, and all the way down the railroad track.</i></b></h5></div>
</div>

<div class="mySlides fade">
  <div class="numbertext">6 / 7</div>
  <img src="./image/TRAIN66.jpg" style="width:100%">
  <div class="text"><h5><b><i>It is good to have an end to journey toward; but it is the journey that matters, in the end. </i></b></h5></div>
</div>

<div class="mySlides fade">
  <div class="numbertext">7 / 7</div>
  <img src="./image/TRAIN77.jpg" style="width:100%">
  <div class="text"><h5><b><i>We travel, some of us forever, to seek other states, other lives, other souls.</i></b></h5></div>
</div>

<div class="mySlides fade">
  <div class="numbertext">7 / 7</div>
  <img src="./image/TRAIN88.jpg" style="width:100%">
  <div class="text"><h5><b><i>We travel, some of us forever, to seek other states, other lives, other souls.</i></b></h5></div>
</div>



</div>
<br>

<div style="text-align:center">
  <span class="dot"></span> 
  <span class="dot"></span> 
  <span class="dot"></span> 
   <span class="dot"></span> 
    <span class="dot"></span> 
     <span class="dot"></span> 
      <span class="dot"></span> 
       <span class="dot"></span>
       
      
</div>

<script>
var slideIndex = 0;
showSlides();

function showSlides() {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}    
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
  setTimeout(showSlides, 2000); // Change image every 2 seconds
}
</script>

<br>
<br>
<br>
<h1> <font size="30px" color="blue">WELCOME TO IRCTC !!!</font></h1>


<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">If you are living in India and anyone asks you that which one is the biggest e commerce site in the whole Asia then definitely you will tell Flipkart.
 But you will be surprised to know that the website of Indian Railways IRCTC sells to over 4 lakhs products on daily basis.
  Rather it is another fact that these products are tickets only. 
  If we talk about the revenue, then websites like Flipkart and Amazon seems to be only a dwarf in front of IRCTC.
   Here, in a day on an average, more than 4.15 lakhs people book tickets here and approx. 10 lakhs people checks their PNR status. 
   Then from this view, it can be said that more than 10 lakhs people come online daily over IRCTC website.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"><strong>Today we are going to tell you some interesting facts about the Indian Railways IRCTC</strong></font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">IRCTC started with small capital base of 20 crore. TCS designed the ERP for IRCTC in 2006 to 07.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> IRCTC website is amongst the most viewed websites in the world, rankings under 750 top sites worldwide and under 50 top sites in India.</font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">On an average, 4.15 lakhs tickets are booked daily.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> Annual booking is of 31 crores in which 55 percent tickets are sold only through ticket window, 37 percent through online mode and rest 8 percent through venders and ticketing agents.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> The record for highest number of ticket booking is for March 1, 2013. On this day, 5.02 lakhs tickets got booked through the online mode.</font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">IRCTC website was supposed to be the slowest among the category but after some crucial changes made in it in the year 2014, it became the largest and fastest developing website of the Asia Pacific region.</font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">In 2013, there were more than 6 lakhs registered users on IRCTC website.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> One user among the five online users in India visits the site of IRCTC in which there are over 120 lakhs unique visitors each month.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> More than 11.57 lakhs berths and seats are booked on daily basis among which 1.71 lakhs seats and 2677 berths are from Tatkal Quota.</font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">In the financial year 2011 to 12, Indian Railways had yielded 847 crores from Tatkal Quota booking only.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> The highest traffic on the website has been seen in between 10 am to 12 pm and after several changes in the rules, between 10 am to 11 am, around 40000 to 50000 tickets are sold on average now on daily basis.</font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">Many records are on the name of IRCTC like :<br />
On September 2, 2013, approx. 572000 tickets were booked in a single day.<br />
On March 19, 2014, approx. 580000 tickets were booked in a single day.<br />
In the year 2002, IRCTC recorded 5.8 lakhs booked ticket that was 27 on number on the very first day.</font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">It has more than 21million customers.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> The earning of IRCTC is more than the total value of Flipkart and Amazon.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> More than 1.2 lakhs users can operate the site at any time. IRCTC can generate 7200 tickets per minute.</font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">The bandwidth of the server is 775MBPS 1GBPS.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> On an average, 432826 tickets are generated on daily basis.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> Nearly 42 percent of customers pay for IRCTC booking through Internet banking  24 percent use Credit/Debit cards.</font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">IRCTC was listed top in most searched term of the year 2014 by Google India.</font></p>
<p class="capitalize" style="font-size:15px"> <font face="verdana" color="green">IRCTC also provide I Ticket facility (like E Ticket) which means your ticket will be send by postal after booking through online.</font></p>
<p class="capitalize" style="font-size:15px"><font face="verdana" color="green"> Here is the number of tickets generated in the last few years......<br />
2010 to 2011  9,69,11,000<br/>
2011 to 2012  11,61,77,000<br/>
2012 to 2013  14,06,88,000<br/>
2013 to 2014  15,79,81,713</font></p>

</body>
</html> 
