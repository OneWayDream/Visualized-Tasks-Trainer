$(document).ready(function () {
    const url = window.location.href
    if (!url.endsWith('/docs/')){
        const pageKey = url.split('/docs/')[1]
        const pageKeyParts = pageKey.split('/')
        $('#mainConcepts').removeClass('active')
        switch (pageKeyParts[0]){
            case 'developing': {
                $('#developingSubmenu').addClass('show')
                switch (pageKeyParts[1]){
                    case 'visualization': {
                        $('#developing_visualization').addClass('active')
                        break
                    }
                    case 'task-upload': {
                        $('#developing_taskUpload').addClass('active')
                        break
                    }
                    case 'wrapping': {
                        $('#developing_wrapping').addClass('active')
                        break
                    }
                }
                break
            }
            case 'education': {
                $('#educationSubmenu').addClass('show')
                switch (pageKeyParts[1]){
                    case 'task-download': {
                        $('#education_taskDownload').addClass('active')
                        break
                    }
                    case 'solution': {
                        $('#education_solution').addClass('active')
                        break
                    }
                }
                break
            }
        }
    }
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active')
    });
    prepareRegistrationButtons()
    prepareWrappingButtons()
});

function prepareRegistrationButtons(){
    $('#registrationJavaLanguageButton').click(function() {
        $("#registrationJavaCode").css("display", "block");
        $("#registrationPythonCode").css("display", "none");
    });

    $("#registrationPythonLanguageButton").click(function() {
        $("#registrationJavaCode").css("display", "none");
        $("#registrationPythonCode").css("display", "block");
    });

    $('#registrationJavaLanguageButton').click()
}

function prepareWrappingButtons(){
    $('#wrappingJavaLanguageButton').click(function() {
        $("#wrappingJavaCode").css("display", "block");
        $("#wrappingPythonCode").css("display", "none");
    });

    $("#wrappingPythonLanguageButton").click(function() {
        $("#wrappingJavaCode").css("display", "none");
        $("#wrappingPythonCode").css("display", "block");
    });

    $('#wrappingJavaLanguageButton').click()
}