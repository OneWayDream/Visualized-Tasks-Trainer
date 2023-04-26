<#import "macro.ftlh" as m />
<#import "spring.ftl" as spring/>

<html lang="en">
    <head>
        <@m.htmlHeader title="Tasks"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/1.1.2/css/bootstrap-multiselect.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/tasks-search.css'/>">
    </head>
    <body>
        <@m.pageHeader/>
        <form class="search-panel" method="GET" action="<@spring.url '/tasks'/>">
            <div class="search-window">
                <div class="options-row">
                    <div id="taskNameSearch " class="search-text-input">
                        <input type="search" id="taskNameSearchField" name="task_name" class="form-control" />
                        <label class="form-label" for="taskNameSearchField">
                            <@spring.message 'tasks-search.search-options.task-name.label'/>
                        </label>
                    </div>
                    <div id="authorNameSearch" class="search-text-input">
                        <input type="search" id="authorNameSearchField" name="author_name" class="form-control" />
                        <label class="form-label" for="authorNameSearchField">
                            <@spring.message 'tasks-search.search-options.author-name.label'/>
                        </label>
                    </div>
                </div>
                <div class="options-row">
                    <div class="search-option">
                        <label class="search-option-label" for="searchOrderSelect">
                            <@spring.message 'tasks-search.search-options.order.label'/>
                        </label>
                        <select id="searchOrderSelect" name="order" class="selectpicker">
                            <option id="descendingOrderOption" value="descending">
                                <@spring.message 'tasks-search.search-options.order.option-names.descending'/>
                            </option>
                            <option id="ascendingOrderOption" value="ascending">
                                <@spring.message 'tasks-search.search-options.order.option-names.ascending'/>
                            </option>
                        </select>
                    </div>
                    <div class="search-option">
                        <label class="search-option-label" for="orderBySelect">
                            <@spring.message 'tasks-search.search-options.order-by.label'/>
                        </label>
                        <select id="orderBySelect" name="order_by" class="selectpicker">
                            <option id="addition-date-option" value="addition-date">
                                <@spring.message 'tasks-search.search-options.order-by.option-names.addition-date'/>
                            </option>
                        </select>
                    </div>
                    <div class="search-option">
                        <label class="search-option-label" for="languagesSelect">
                            <@spring.message 'tasks-search.search-options.languages.label'/>
                        </label>
                        <select id="languagesSelect" name="languages" multiple="multiple" class="selectpicker">
                            <option id="javaOption" value="JAVA">
                                <@spring.message 'tasks-search.search-options.languages.option-names.java'/>
                            </option>
                            <option id="pythonOption" value="PYTHON">
                                <@spring.message 'tasks-search.search-options.languages.option-names.python'/>
                            </option>
                        </select>
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="search-button">
                        <img src="<@spring.url '/images/icons/search.png'/>" alt="" width="30px" height="30px"/>
                        <@spring.message 'tasks-search.search-button'/>
                    </button>
                </div>
            </div>
        </form>
        <div class="search-results-panel">
            <div class="search-results-window">
                <#if tasks?size == 0>
                    <h3 class="no-tasks-text">
                        <@spring.message 'tasks-search.no-search-results.message'/>
                    </h3>
                <#else>
                    <#list tasks as task>
                        <div class="task-info-block">
                            <div class="information-block">
                                <p><span class="task-name">${task.taskName}</span> <span class="bold"><@spring.message 'tasks-search.search-block.by-user'/></span> ${task.authorName}</p>
                                <p>${task.language.languageName}</p>
                            </div>
                            <div class="task-download-block" >
                                <a href="${tasksUrl}${task.id}">
                                    <div>
                                        <img src="<@spring.url '/images/icons/download.png'/>"
                                             class="download-button btn" alt="" width="70px" height="70px"/>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
        <nav aria-label="Pages navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item <#if !hasPreviousPage>disabled</#if>">
                    <a class="page-link" href="<@spring.url '/tasks?' + queryString + (pageNumber - 1)/>" tabindex="-1">
                        <@spring.message 'tasks-search.pagination-buttons.previous-page'/>
                    </a>
                </li>
                <#if hasPreviousPage>
                    <li class="page-item"><a class="page-link" href="<@spring.url '/tasks?' + queryString + (pageNumber - 1)/>">${pageNumber}</a></li>
                </#if>
                <li class="page-item active">
                    <a class="page-link" href="">${pageNumber + 1}<span class="sr-only">(current)</span></a>
                </li>
                <#if hasNextPage>
                    <li class="page-item"><a class="page-link" href="<@spring.url '/tasks?' + queryString + (pageNumber + 1)/>">${pageNumber + 2}</a></li>
                </#if>
                <li class="page-item <#if !hasNextPage>disabled</#if>">
                    <a class="page-link" href="<@spring.url '/tasks?' + queryString + (pageNumber + 1)/>">
                        <@spring.message 'tasks-search.pagination-buttons.next-page'/>
                    </a>
                </li>
            </ul>
        </nav>
        <@m.pageFooter/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/1.1.2/js/bootstrap-multiselect.min.js" crossorigin="anonymous"></script>
        <script type ="text/javascript" src="<@spring.url '/js/tasks-search.js' />"></script>
    </body>
</html>