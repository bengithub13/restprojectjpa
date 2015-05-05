<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="<c:url value="/resources/themes/css/homeAddress.css" />" rel="stylesheet">
<script type="text/javascript"
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script src="<c:url value="/resources/themes/js/homeAddress.js" />"></script>
<h1>Spring MVC Address example</h1>
<%@ page isELIgnored="false" %>


    <title>Spring MVC address</title>

 
 
 <!-- http://www.dineshonjava.com/2013/10/jstl-foreach-tag.html#.VTstCpNQ1WE -->
 
<h2>Show Addresses</h2>     
  <div id="msg"></div>
<table border="1" width="50%">        

        
        
    <tbody><tr>
        <th>street</th>
        <th>zipcode</th>
    </tr>
<c:forEach items="${addressesObject.homeAddresses}" var="address" >
    <tr>
            <td>${address.street}</td>
            <td>${address.zipCode}</td>
        </tr>
     </c:forEach>
</tbody></table>    
<br>
<!--  cann javascript function inside tag via javascript: keyword -->
<input value="Back" onclick="javascript:history.back()" type="button">
<input value="forward" onclick="javascript:history.forward()" type="button">