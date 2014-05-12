<%@include file="partials/libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Error!"/>
<%@include file="partials/header.jsp" %>

<div id="error">
    <p>Sorry, there was a problem with your request.</p>
    <p>Please ensure your file is a .txt in the following format: </p>
    <div class="text-box">
        ${exampleText}<br>
        ${exampleText}<br>
        etc.
    </div>
</div>

<br>
<a href="/" class="menuItem">Return Home</a>

<%@include file="partials/footer.jsp" %>
