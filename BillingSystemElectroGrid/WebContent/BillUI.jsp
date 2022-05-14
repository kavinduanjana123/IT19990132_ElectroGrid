<%@ page import="com.Billing"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing UI</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/billing.js"></script> 

<nav class="navbar navbar-expand-md navbar-dark" style="background-color:#154c79">
                   

                    <ul class="navbar-nav">
                        <h1>ElectroGrid Online System<h1>
                    </ul>
                 </nav>




</head>
<body style="background-color:powderblue">
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
						 <caption>
                            <h2>
                                Bill Management
                            </h2>
                        </caption>
                        <hr>
				<form id="formBilling" name="formBilling" method="post" action="BillUI.jsp"> 
				
					Account No:  
 	 				<input id="bAcc" name="bAcc" type="text"  class="form-control form-control-sm">
 	 				
					<br>Customer Name:   
  					<input id="bName" name="bName" type="text" class="form-control form-control-sm"> 
  					 
					<br>Customer Address:   
  					<input id="bAddress" name="bAddress" type="text" class="form-control form-control-sm"> 
  					  
					<br>Month:   
  					<input id="bMonth" name="bMonth" type="text" class="form-control form-control-sm"> 
  					  
  					<br>Used Unit:   
  					<input id="bUnit" name="bUnit" type="text"  class="form-control form-control-sm">
  					
					<br>Unit Price:   
  					<input id="bUnitP" name="bUnitP" type="text" class="form-control form-control-sm"> 
  					
  					  
  					<br>Total Price:   
  					<input id="bAmount" name="bAmount" type="text"  class="form-control form-control-sm">
					<br>  
					
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidBillingIDSave" name="hidBillingIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				 <h3 class="text-center">Bill Details</h3>
				 <hr>
			   <br>
				<div id="divBillingGrid">
					<%
					    Billing billingObj = new Billing();
						out.print(billingObj.readBilling());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>

</html>