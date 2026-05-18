<%@ page language="java" contentType="text/html" isELIgnored="false"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    <title>Shortify</title>
  </head>
  <body style="background-color: #0b5fb8d1">
    
    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

    <div class="d-flex justify-content-center align-items-center" style="height:100vh;">
      <div class="container">
        <div class="card">
          <div class="card-header bg-primary text-white">
            Shortify
          </div>
          <div class="card-body">
            <h3 class="card-title">Url Shortener</h3> 
            <form:errors path="url.*" cssClass="alert alert-danger" element="div"/>           
            <form action="urlShortener" method="POST" modelAttribute="url">
                <div class="form-group">
                    <label for="exampleFormControl1">Please paste your url here...</label>
                    <input type="text" class="form-control" id="exampleFormControl1" 
                                name="originalUrl" placeholder="https://www.google.com" />
                    
                </div>
                <button type="submit" class="btn btn-outline-info">Generate Short Url</button>
            </form>
          </div>
          <!-- This(below) is how we commect tags from taglib uri -->
          <%-- <c:if test="${not empty originalUrl}"> --%>

            <c:if test="${not empty shortUrl}">  
              <div class="card-footer text-muted">    
                <a href="${shortUrl}" class="btn btn-outline-primary btn-lg btn-block" target="_blank">${shortUrl}</a>  
              </div>     
            </c:if>
        </div>
      </div>  
    </div>
  </body>
</html>
