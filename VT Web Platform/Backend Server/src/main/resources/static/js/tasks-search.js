$(document).ready(function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    if (urlParams.get('task_name') != null){
        $('#taskNameSearchField').val(urlParams.get('task_name'));
    }
    if (urlParams.get('author_name') != null){
        $('#authorNameSearchField').val(urlParams.get('author_name'));
    }
    if (urlParams.get('order') != null){
        $('#' + urlParams.get('order') + 'OrderOption').prop('selected', true);
    }
    if (urlParams.get('order_by') != null){
        $('#' + urlParams.get('order_by') + '-option').prop('selected', true);
    }
    if (urlParams.get('languages') != null){
        const languages = (queryString.split('languages='))
        for (let i = 1; i < languages.length; i++){
            languages[i] = languages[i].replace('&', '').toLowerCase()
            $('#' + languages[i] + 'Option').prop('selected', true);
        }
    } else {
        $('#javaOption').prop('selected', true);
        $('#pythonOption').prop('selected', true);
    }
    $('#languagesSelect').multiselect();
});
