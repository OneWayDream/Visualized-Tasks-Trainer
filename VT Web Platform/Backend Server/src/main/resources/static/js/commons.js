$(window).on('load', function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    if (urlParams.get('sign-in') != null){
        $('#signInModal').modal('show');
    }
    if (urlParams.get('success') != null){
        $('#successRegistrationModal').modal('show');
    }
    if ($('#pageState').attr('state') === 'sign-up'){
        $('#signUpModal').modal('show');
    }
});