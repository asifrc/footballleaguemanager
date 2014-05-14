$(function () {
    $('.score-team select').on('change', function () {
        $('.score-team select option').attr('disabled', false);
        var optionInOtherDropdown = $('.score-team select').not($(this))[0].options[this.selectedIndex];
        $(optionInOtherDropdown).attr('disabled', 'disabled');
        $('.score-team select option:first-child').attr('disabled', false);
    });
});