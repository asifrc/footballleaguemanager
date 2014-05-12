<form method="POST" enctype="multipart/form-data" action="/upload-${personType}list">
    <div class="upload-buttons">
        <input class="upload-field" type="file" name="file" />
        <input type="hidden" name="person-type" value="${personType}" />
        <input class="upload-button" type="submit" value="upload" disabled="true"/>
    </div>
</form>