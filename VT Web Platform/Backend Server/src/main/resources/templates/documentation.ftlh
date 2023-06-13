<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="Documentation"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/documentation.css'/>">
    </head>
    <body>
        <@m.pageHeader/>
        <div class="wrapper">
            <nav id="sidebar">
                <div class="sidebar-header">
                    <a href="<@spring.url '/docs'/>">
                        <h3><@spring.message 'docs.navigation.header'/></h3>
                    </a>
                </div>

                <ul class="list-unstyled components">
                    <li class="active" id="mainConcepts">
                        <a href="<@spring.url '/docs/'/>"><@spring.message 'docs.navigation.main-concepts'/></a>
                    </li>
                    <li>
                        <a href="#developingSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                            <@spring.message 'docs.navigation.developing.title'/>
                        </a>
                        <ul class="collapse list-unstyled" id="developingSubmenu">
                            <li id="developing_visualization">
                                <a href="<@spring.url '/docs/developing/visualization'/>">
                                    <@spring.message 'docs.navigation.developing.visualization.title'/>
                                </a>
                            </li>
                            <li id="developing_wrapping">
                                <a href="<@spring.url '/docs/developing/wrapping'/>">
                                    <@spring.message 'docs.navigation.developing.wrapping.title'/>
                                </a>
                            </li>
                            <li id="developing_taskUpload">
                                <a href="<@spring.url '/docs/developing/task-upload'/>">
                                    <@spring.message 'docs.navigation.developing.task-upload.title'/>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#educationSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                            <@spring.message 'docs.navigation.education.title'/>
                        </a>
                        <ul class="collapse list-unstyled" id="educationSubmenu">
                            <li id="education_taskDownload">
                                <a href="<@spring.url '/docs/education/task-download'/>">
                                    <@spring.message 'docs.navigation.education.task-download.title'/>
                                </a>
                            </li>
                            <li id="education_solution">
                                <a href="<@spring.url '/docs/education/solution'/>">
                                    <@spring.message 'docs.navigation.education.solution.title'/>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>

            <div id="content">
                <@m.documentationContent pageKey/>
            </div>
        </div>
        <@m.pageFooter/>
        <script type ="text/javascript" src="<@spring.url '/js/documentation.js' />"></script>
    </body>
</html>