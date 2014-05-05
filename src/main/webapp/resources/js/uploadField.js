$(document).ready(function(){
    $('input[type=file]').change(function(){
        var hasNoFiles = this.files.length == 0;
        $(this).closest('form') /* Select the form element */
            .find('input[type=submit]') /* Get the submit button */
            .prop('disabled', hasNoFiles); /* Disable the button. */
    });
});
