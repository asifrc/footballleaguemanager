$(document).ready(function () {
    $('input[type=file]').change(function () {
        var hasNoFiles = this.files.length == 0;
        $(this).closest('form')/* Select the form element */
            .find('input[type=submit]')/* Get the submit button */
            .prop('disabled', hasNoFiles);
        /* Disable the button. */
    });
});

$(document).ready(function () {
    $('#age-filter-dropdown').change(function () {
        var defaultSelected = this.value == 0;
        $(this).closest('form')
            .find('input[type=submit]')
            .prop('disabled', defaultSelected);
    });
});

// number validator (doesn't allow text to be input)
//$('#number')[0].onkeypress = function (e) {
//    if (isNaN(String.fromCharCode(e.keyCode))) {
//        return false;
//    }
//};