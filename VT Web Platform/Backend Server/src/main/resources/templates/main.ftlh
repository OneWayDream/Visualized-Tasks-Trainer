<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="Visual Tasks"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/main.css'/>">
    </head>
    <body>
        <@m.pageHeader/>

        <header class="masthead">
            <div class="container position-relative">
                <div class="row justify-content-center">
                    <div class="col-xl-6">
                        <div class="text-center text-white">
                            <h1 class="mb-5"><@spring.message 'main.title'/></h1>
                        </div>
                    </div>
                    <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                        <div class="features-icons-icon d-flex">
                            <img class="m-auto text-primary" src="<@spring.url '/images/application.png'/>"
                                 width="850px" height="500px" alt="Tasks"/>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section class="features-icons bg-light text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <a class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3" href="<@spring.url '/tasks'/>">
                            <div class="features-icons-icon d-flex">
                                <img class="m-auto text-primary" src="<@spring.url '/images/icons/tasks.png'/>"
                                     alt="Tasks" width="200px" height="200px"/>
                            </div>
                            <h3><@spring.message 'main.tasks.title'/></h3>
                            <p class="lead mb-0"><@spring.message 'main.tasks.description'/></p>
                        </a>
                    </div>
                    <div class="col-lg-4">
                        <a class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3" href="<@spring.url '/docs/'/>">
                            <div class="features-icons-icon d-flex">
                                <img class="m-auto text-primary" src="<@spring.url '/images/icons/documentation.png'/>"
                                     alt="Documentation" width="200px" height="200px"/>
                            </div>
                            <h3><@spring.message 'main.documentation.title'/></h3>
                            <p class="lead mb-0"><@spring.message 'main.documentation.description'/></p>
                        </a>
                    </div>
                    <div class="col-lg-4">
                        <a class="features-icons-item mx-auto mb-0 mb-lg-3" href="<@spring.url '/plugins'/>">
                            <div class="features-icons-icon d-flex">
                                <img class="m-auto text-primary" src="<@spring.url '/images/icons/plugins.png'/>"
                                     alt="Plugins" width="200px" height="200px"/>
                            </div>
                            <h3><@spring.message 'main.plugins.title'/></h3>
                            <p class="lead mb-0"><@spring.message 'main.plugins.description'/></p>
                        </a>
                    </div>
                </div>
            </div>
        </section>
        <@m.pageFooter/>
    </body>
</html>