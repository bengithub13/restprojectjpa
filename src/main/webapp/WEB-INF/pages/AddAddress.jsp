<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Add Address using ajax</title>
        <script type="text/javascript"
   		 src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
        <script type="text/javascript">
        function doAjaxPost() {
        // get the form values
        var street = $('#street').val();
        var zipcode = $('#zipcode').val();

        var o1first = $('#o1first').val();
        var o1last = $('#o1last').val();

        var o2first = $('#o2first').val();
        var o2last = $('#o2last').val();

        
        
        $.ajax({
        type: "POST",
        url: "/springmvc/AddUser.htm",
        data: {street:street, zipcode:zipcode, o1firstname:o1first, o1lastname:o1last,o2firstname:o2first, o2lastname:o2last},
        success: function(response){
        // we have the response
        $('#info').html(response);
        $('#name').val('');
        $('#education').val('');
        },
        error: function(e){
        alert('Error: ' + e);
        }
        });
        }
        </script>
    </head>
    <body>
        <h1>Add Users using Ajax ........</h1>
        <table>
            <tr><td>Enter Street: </td><td> <input type="text" id="street"><br/></td></tr>
            <tr><td>Enter zip code : </td><td> <input type="text" id="zipcode"><br/></td></tr>
            <tr><td>Enter Owner1 first name: </td><td> <input type="text" id="o1first"><br/></td></tr>
            <tr><td>Enter Owner1 last name: </td><td> <input type="text" id="o1last"><br/></td></tr>
            <tr><td>Enter Owner2 first name: </td><td> <input type="text" id="o2first"><br/></td></tr>
            <tr><td>Enter Owner2 last name: </td><td> <input type="text" id="o2last"><br/></td></tr>
            
            <tr><td>Enter zip code : </td><td> <input type="text" id="zipcode"><br/></td></tr>
            <tr><td colspan="2"><input type="button" value="Add Address" onclick="doAjaxPost()"><br/></td></tr>
            <tr><td colspan="2"><div id="info" style="color: green;"></div></td></tr>
        </table>
        <a href="/AjaxWithSpringMVC2Annotations/ShowUsers.htm">Show All Users</a>
    </body>
</html>