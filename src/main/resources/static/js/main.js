$(document).ready(function() {
    $.getJSON('/account', function (data) {
        $('#login').text(data.email);
        $('#avatar').attr("src", data.pictureUrl);
    });
});
