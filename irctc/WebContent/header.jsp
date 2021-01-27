<!doctype html>
<html lang="en-US">
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html">
  <title>IRCTC Home Page - Welcome <%=session.getAttribute("loginId") %></title>
  <meta name="author" content="Jake Rocheleau">
  <link rel="stylesheet" type="text/css" media="all" href="./css/menu_style.css">
  <script type="text/javascript" src="./js/jquery-1.11.1.min.js"></script>


<script language = "javascript">

function showUserTypeMaster()
{
	homeForm.masterType.value="UserTypeMaster";
	homeForm.submit();
}



function showUserMaster()
{
	homeForm.masterType.value="UserMaster";
	homeForm.submit();
}

function showStationMaster()
{

	homeForm.masterType.value="StationMaster";
	homeForm.submit();

}

function showTrainMaster()
{

	homeForm.masterType.value="TrainMaster";
	homeForm.submit();

}


function showTrainFare()
{
	homeForm.masterType.value="TrainFare";
	homeForm.submit();
}


function showBooking()
{
	homeForm.masterType.value="booking";
	homeForm.submit();
}

function showBookingHistory()
{
	homeForm.masterType.value="bookingHistory";
	homeForm.submit();
}


function showUserMasterReport()
{
	homeForm.masterType.value="userMasterReport";
	homeForm.submit();
}


function showTrainMasterReport()
{
	homeForm.masterType.value="showTrainMasterReport";
	homeForm.submit();
}






</script>
</head>
<form name="homeForm" action="MasterController" method="post">
<input type="hidden" name="masterType">

<body>
  <nav>
    <div class="wrapper">
      <ul id="menu" class="clearfix">
        <li><a href="home.jsp">Home</a></li>
        
        
        <li><a href="#">Master</a>
          <ul>
            <li onclick="showUserMaster();"><a href="#">User Master</a></li>
            <li onclick="showUserTypeMaster();"><a href="#">User Type Master</a></li>
            <li onclick="showTrainMaster()"><a href="#">Train Master</a></li>
            <li onclick="showTrainFare();"><a href="#">Train Fare</a></li>
            <li onclick="showStationMaster()"><a href="#">Station Master</a></li>
          </ul>
        </li>
        
        <li><a href="#">Booking</a>
          <ul>
          <li onclick="showBooking()"><a href="#">Book New Ticket</a></li>
          <li onclick="showBookingHistory()"><a href="#">Booking History</a></li>
          </ul>
        </li>
        
        
        <li><a href="#">Reports</a>
          <ul>
            <li onclick="showUserMasterReport()"><a href="#">User Master</a></li>
            <li onclick="showTrainMasterReport()"><a href="#">Train Master</a></li>
          </ul>
        </li>
        
        
        <li><a href="#">Queries</a>
          <ul>
            <li><a href="faq.jsp">FAQs</a></li>
            
          </ul>
        </li>
        
        <li><a href="logout.jsp">Logout</a></li>
        
        
     <!--    <li><a href="#">Booking</a>
          <ul>
            <li class="purple"><a href="#">Design</a>
              <ul>
                <li><a href="#">Photoshop</a></li>
                <li><a href="#">Illustrator</a></li>
                <li><a href="#">InDesign</a></li>
              </ul>
            </li>
            <li class="green"><a href="#">Writing</a>
              <ul>
                <li><a href="#">Copywriting</a></li>
                <li><a href="#">Journalism</a></li>
                <li><a href="#">Poetry</a></li>
                <li><a href="#">Storytelling</a></li>
              </ul>
            </li>
            <li class="aqua"><a href="#">Accounting</a>
              <ul>
                <li><a href="#">Taxes</a></li>
                <li><a href="#">Credit</a></li>
                <li><a href="#">Asset Management</a></li>
              </ul>
            </li>
            <li class="red"><a href="#">Marketing</a>
              <ul>
                <li><a href="#">Print</a></li>
                <li><a href="#">Digital</a></li>
                <li><a href="#">Branding</a></li>
                <li><a href="#">Presenting</a></li>
                <li><a href="#">Social Media</a></li>
              </ul>
            </li>
            <li class="blue"><a href="#">Development</a>
              <ul>
                <li><a href="#">HTML5/CSS3</a></li>
                <li><a href="#">jQuery</a></li>
                <li><a href="#">PHP</a></li>
                <li><a href="#">Ruby on Rails</a></li>
              </ul>
            </li>
            <li class="gold"><a href="#">Photography</a>
              <ul>
                <li><a href="#">Mechanics</a></li>
                <li><a href="#">Composition</a></li>
              </ul>
            </li>
          </ul>
        </li>
        <li><a href="#">Social</a>
          <ul>
            <li><a href="#">Facebook</a></li>
            <li><a href="#">Twitter</a></li>
            <li><a href="#">YouTube</a></li>
            <li><a href="#">Instagram</a></li>
            <li><a href="#"></a></li>
          </ul>
        </li>
         -->
         
      </ul>
    </div>
  </nav>
<script type="text/javascript">
$(function(){
  $('a[href="#"]').on('click', function(e){
    e.preventDefault();
  });
  
  $('#menu > li').on('mouseover', function(e){
    $(this).find("ul:first").show();
    $(this).find('> a').addClass('active');
  }).on('mouseout', function(e){
    $(this).find("ul:first").hide();
    $(this).find('> a').removeClass('active');
  });
  
  $('#menu li li').on('mouseover',function(e){
    if($(this).has('ul').length) {
      $(this).parent().addClass('expanded');
    }
    $('ul:first',this).parent().find('> a').addClass('active');
    $('ul:first',this).show();
  }).on('mouseout',function(e){
    $(this).parent().removeClass('expanded');
    $('ul:first',this).parent().find('> a').removeClass('active');
    $('ul:first', this).hide();
  });
});
</script>
</body>
</form>
</html>