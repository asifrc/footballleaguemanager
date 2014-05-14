$(function () {
    $('.score-team select').on('change', function () {
        $('.score-team select option').attr('disabled', false);
        $('.score-team select:not("#' + this.id + '") :nth-child(' + (this.selectedIndex + 1) + ')').attr('disabled', 'disabled');
        $('.score-team select option:first-child').attr('disabled', false);
    });
});