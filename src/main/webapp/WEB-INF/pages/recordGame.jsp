<%@include file="partials/libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Football League Manager"/>
<%@include file="partials/header.jsp" %>

<h1>Record Game Results</h1>

<p id="flash">${gameResult}</p>

<form id="record-game-form" action="/record" method="POST">
    <div class="score-form">
        <div class="score-team">
            <label for="team1-dropdown">Team 1</label>
            <select id="team1-dropdown" name="team0">
                <%@include file="partials/teamOptionList.jsp" %>
            </select>

            <label for="team1-score">Score</label>
            <input type="number" id="team1-score" name="team0score"/>
        </div>
        <div class="score-team">
            <label for="team2-dropdown">Team 2</label>
            <select id="team2-dropdown" name="team1">
                <%@include file="partials/teamOptionList.jsp" %>
            </select>

            <label for="team2-score">Score</label>
            <input type="number" id="team2-score" name="team1score"/>
        </div>
        <div class="score-submit"><input type="submit" value="Record Game" id="submit"></div>
    </div>
</form>

<%@include file="partials/footer.jsp" %>
