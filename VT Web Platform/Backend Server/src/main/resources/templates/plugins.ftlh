<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="Plugins"/>
    </head>
    <body>
        <@m.pageHeader/>
        <div class="container position-relative">
            <div class="row justify-content-center">
                <div class="row">
                    <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                        <div class="features-icons-icon d-flex">
                            <img class="m-auto text-primary" src="<@spring.url '/images/icons/hammer.png'/>"
                                 width="200px" height="200px" alt="Tasks"/>
                        </div>
                        <div class="text-center text-black">
                            <h1 class="mb-5"><@spring.message 'plugins.title'/></h1>
                        </div>
                        <a class="text-center text-black" href="<@spring.url ''/>">
                            <h3 class="not-bold" ><@spring.message 'plugins.description'/></h3>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <@m.pageFooter/>
    </body>
</html>