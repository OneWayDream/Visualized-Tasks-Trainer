<#import "spring.ftl" as spring/>
<#import "docs-content.ftlh" as docs/>

<#macro htmlHeader title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${title}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/css/commons.css'/>">
</#macro>

<#macro pageHeader>
    <div id="pageState" state="<#if pageState ??>${pageState}<#else></#if>"></div>
    <nav class="navbar navbar-light bg-light static-top">
        <div class="container">
            <a href="<@spring.url ''/>">
                <img class="navbar-brand" src="<@spring.url '/images/site-logo.png'/>" alt="Logo">
            </a>
            <div class="options">
                <#if account ??>
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" id="accountDownDrop"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${account.login}
                        </button>
                        <div class="dropdown-menu" aria-labelledby="accountDownDrop">
                            <a class="dropdown-item" href="<@spring.url '/logout'/>">
                                <p>
                                    <img src="<@spring.url '/images/icons/logout.png' />" width="20" height="20" alt="">
                                    <@spring.message 'page-header.account-menu.logout-text'/>
                                </p>
                            </a>
                        </div>
                    </div>
                <#else>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#signUpModal">
                        <@spring.message 'page-header.sign-up-button'/>
                    </button>

                    <div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-labelledby="signUpModalLabel"
                         aria-hidden="true" data-backdrop="false">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content mask d-flex align-items-center h-100 gradient-custom-3">
                                <div class="container h-100">
                                    <div class="row d-flex justify-content-center align-items-center h-100">
                                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                                            <div class="card" style="border-radius: 15px;">
                                                <div class="card-body p-5">
                                                    <div class="modal-header">
                                                        <h2 class="text-uppercase text-center mb-5" id="signUpModalLabel">
                                                            <@spring.message 'sign-up.title'/>
                                                        </h2>
                                                        <button type="button" class="btn-close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <@spring.bind "signUpForm"/>
                                                    <form action="<@spring.url '/sign-up'/>" method="POST">
                                                        <div class="form-outline mb-4">
                                                            <@spring.formInput "signUpForm.login" "id='signUpLoginField'
                                                            class='form-control form-control-lg' minlength='3'
                                                            maxlength='30'"/>
                                                            <label class="form-label" for="signUpLoginField">
                                                                <@spring.message 'sign-up.form.login-label'/>
                                                            </label>
                                                            <div class="form-label">
                                                                <@spring.showErrors "<br>" "text-danger"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-outline mb-4">
                                                            <@spring.formInput "signUpForm.email" "id='signUpEmailField'
                                                            class='form-control form-control-lg' minlength='3'
                                                            maxlength='30'"/>
                                                            <label class="form-label" for="signUpEmailField">
                                                                <@spring.message 'sign-up.form.email-label'/>
                                                            </label>
                                                            <div class="form-label">
                                                                <@spring.showErrors "<br>" "text-danger"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-outline mb-4">
                                                            <@spring.formPasswordInput "signUpForm.password"
                                                            "id='signUpPasswordField' class='form-control form-control-lg'
                                                            required minlength='8' maxlength='50'"/>
                                                            <label class="form-label" for="signUpPasswordField">
                                                                <@spring.message 'sign-up.form.password-label'/>
                                                            </label>
                                                            <div class="form-label">
                                                                <@spring.showErrors "<br>" "text-danger"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-outline mb-4">
                                                            <@spring.formPasswordInput "signUpForm.repeatedPassword"
                                                            "id='signUpPasswordField' class='form-control form-control-lg'
                                                            required minlength='8' maxlength='50'"/>
                                                            <label class="form-label" for="signUpRepeatedPasswordField">
                                                                <@spring.message 'sign-up.form.repeated-password-label'/>
                                                            </label>
                                                            <div class="form-label">
                                                                <@spring.showErrors "<br>" "text-danger"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-check d-flex justify-content-center mb-5">
                                                            <div style="display: flex; flex-direction: row">
                                                                <@spring.formCheckbox "signUpForm.userAccess"
                                                                    "class='form-check-input me-2' required"/>
                                                                <label class="form-check-label" for="termsCheckBox">
                                                                    <@spring.message 'sign-up.form.terms-text'/>
                                                                    <a href="" class="text-body">
                                                                        <u>
                                                                            <@spring.message 'sign-up.form.terms-link-text'/>
                                                                        </u>
                                                                    </a>
                                                                </label>
                                                                <div class="form-label">
                                                                    <@spring.showErrors "<br>" "text-danger"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <#if _csrf??>
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                   value="${_csrf.token}">
                                                        </#if>
                                                        <#if message ??>
                                                            <div class="form-outline mb-4">
                                                                <p class="text-danger">${message}</p>
                                                            </div>
                                                        </#if>
                                                        <#if spring_message ??>
                                                            <div class="form-outline mb-4">
                                                                <p class="text-danger"><@spring.message spring_message/></p>
                                                            </div>
                                                        </#if>
                                                        <div class="d-flex justify-content-center">
                                                            <button type="submit" class="btn btn-success btn-block btn-lg
                                                             gradient-custom-4 text-body">
                                                                <@spring.message 'sign-up.form.registration-button'/>
                                                            </button>
                                                        </div>
                                                        <p class="text-center text-muted mt-5 mb-0">
                                                            <@spring.message 'sign-up.form.sign-in-reference-text'/>
                                                            <a href="<@spring.url '/sign-in'/>" data-toggle="modal"
                                                               data-target="#signInModal" data-dismiss="modal"
                                                               class="fw-bold text-body">
                                                                <u>
                                                                    <@spring.message 'sign-up.form.sign-in-reference-link-text'/>
                                                                </u>
                                                            </a>
                                                        </p>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade" id="signInModal" tabindex="-1" role="dialog" aria-labelledby="signInModalLabel"
                         aria-hidden="true" data-backdrop="false">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content mask d-flex align-items-center h-100 gradient-custom-3">
                                <div class="container h-100">
                                    <div class="row d-flex justify-content-center align-items-center h-100">
                                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                                            <div class="card" style="border-radius: 15px;">
                                                <div class="card-body p-5">
                                                    <div class="modal-header">
                                                        <h2 class="text-uppercase text-center mb-5" id="signInModalLabel">
                                                            <@spring.message 'sign-in.title'/>
                                                        </h2>
                                                        <button type="button" class="btn-close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <@spring.bind "signInForm"/>
                                                    <form action="<@spring.url '/sign-in'/>" method="POST">
                                                        <div class="form-outline mb-4">
                                                            <@spring.formInput "signInForm.login" "id='signInLoginField'
                                                            class='form-control form-control-lg' minlength='3'
                                                            maxlength='30'"/>
                                                            <label class="form-label" for="signInLoginField">
                                                                <@spring.message 'sign-in.form.login-label'/>
                                                            </label>
                                                            <div class="form-label">
                                                                <@spring.showErrors "<br>" "text-danger"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-outline mb-4">
                                                            <@spring.formPasswordInput "signInForm.password"
                                                            "id='signInPasswordField' class='form-control form-control-lg'
                                                            required minlength='8' maxlength='50'"/>
                                                            <label class="form-label" for="signInPasswordField">
                                                                <@spring.message 'sign-in.form.password-label'/>
                                                            </label>
                                                            <div class="form-label">
                                                                <@spring.showErrors "<br>" "text-danger"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-outline mb-4">
                                                            <input type="checkbox" id="remember-me" name="remember-me" class="agree-term">
                                                            <label for="remember-me" class="label-agree-term text-dark">
                                                                <@spring.message 'sign-in.form.remember-me-text'/>
                                                            </label>
                                                        </div>
                                                        <#if _csrf??>
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                   value="${_csrf.token}">
                                                        </#if>
                                                        <#if signInError ??>
                                                            <div class="form-outline mb-4">
                                                                <p class="text-danger"><@spring.message signInError/></p>
                                                            </div>
                                                        </#if>
                                                        <div class="d-flex justify-content-center">
                                                            <button type="submit" class="btn btn-success btn-block btn-lg
                                                             gradient-custom-4 text-body">
                                                                <@spring.message 'sign-in.form.login-button'/>
                                                            </button>
                                                        </div>
                                                        <p class="text-center text-muted mt-5 mb-0">
                                                            <@spring.message 'sign-in.form.sign-up-reference-text'/>
                                                            <a href="<@spring.url '/sign-up'/>" data-toggle="modal"
                                                               data-target="#signUpModal" data-dismiss="modal"
                                                               class="fw-bold text-body">
                                                                <u>
                                                                    <@spring.message 'sign-in.form.sign-up-reference-link-text'/>
                                                                </u>
                                                            </a>
                                                        </p>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade" id="successRegistrationModal" tabindex="-1" role="dialog" aria-labelledby="successRegistrationModalLabel"
                         aria-hidden="true" data-backdrop="false">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content mask d-flex align-items-center h-100 gradient-custom-3">
                                <div class="container h-100">
                                    <div class="row d-flex justify-content-center align-items-center h-100">
                                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                                            <div class="card" style="border-radius: 15px;">
                                                <div class="card-body p-5">
                                                    <div class="modal-header">
                                                        <h2 class="text-uppercase text-center mb-5" id="successRegistrationModalLabel">
                                                            <@spring.message 'success-registration.title'/>
                                                        </h2>
                                                        <button type="button" class="btn-close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                </div>
                                                <p class="text-center text-muted mt-5 mb-0">
                                                    <button type="button" class="btn btn-success"
                                                            data-toggle="modal" data-target="#signInModal"
                                                            data-dismiss="modal">
                                                        <@spring.message 'success-registration.button-text'/>
                                                    </button>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </#if>

                <div class="dropdown">
                    <button class="btn btn-success dropdown-toggle" type="button" id="language_down_drop" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <@spring.message 'page-header.language-selector.button-title'/>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="language_down_drop">
                        <a class="dropdown-item" href="?lang=ru">
                            <p>
                                <img src="<@spring.url '/images/languages/ru-flag.png' />" width="20" height="20" alt="">
                                <@spring.message 'page-header.language-selector.ru-title'/>
                            </p>
                        </a>
                        <hr class="dropdown-divider">
                        <a class="dropdown-item" href="?lang=en">
                            <p>
                                <img src="<@spring.url '/images/languages/en-flag.png'/>" width="20" height="20" alt="">
                                <@spring.message 'page-header.language-selector.en-title'/>
                            </p>
                        </a>
                    </div>
                </div>

            </div>
        </div>
    </nav>
</#macro>

<#macro pageFooter>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type ="text/javascript" src="<@spring.url '/js/commons.js' />"></script>
</#macro>

<#macro documentationContent pageKey=''>
    <#if pageKey ??>
        <#switch pageKey>
            <#case 'developing/visualization'>
                <@docs.developing_visualization/>
                <#break>
            <#case 'developing/task-upload'>
                <@docs.developing_taskUpload/>
                <#break>
            <#case 'developing/wrapping'>
                <@docs.developing_wrapping/>
                <#break>
            <#case 'education/task-download'>
                <@docs.education_taskDownload/>
                <#break>
            <#case 'education/solution'>
                <@docs.education_solution/>
                <#break>
            <#default>
                <@docs.mainConcepts/>
                <#break>
        </#switch>
    <#else>
        <@docs.mainConcepts/>
    </#if>
</#macro>