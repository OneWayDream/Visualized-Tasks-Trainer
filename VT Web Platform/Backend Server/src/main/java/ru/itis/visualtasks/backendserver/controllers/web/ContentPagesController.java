package ru.itis.visualtasks.backendserver.controllers.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.visualtasks.backendserver.dto.search.SearchAttributes;
import ru.itis.visualtasks.backendserver.models.TaskInfo;
import ru.itis.visualtasks.backendserver.services.TaskInfoService;

@Controller
public class ContentPagesController extends WebController{

    private final TaskInfoService taskInfoService;
    private final String tasksServiceUrl;

    public ContentPagesController(
            TaskInfoService taskInfoService,
            @Value("${tasks-server.task-download-url}") String tasksServiceUrl
    ) {
        this.taskInfoService = taskInfoService;
        this.tasksServiceUrl = tasksServiceUrl;
    }

    @GetMapping("/docs/**")
    public ModelAndView documentationPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("documentation");
        addPageKey(request, modelAndView);
        addAuthenticationAttributes(modelAndView);
        return modelAndView;
    }

    private void addPageKey(HttpServletRequest request, ModelAndView modelAndView) {
        String requestURL = request.getRequestURL().toString();
        if (!requestURL.endsWith("/docs/")){
            String pageKey = requestURL.split("/docs/")[1];
            modelAndView.addObject("pageKey", pageKey);
        }
    }

    @GetMapping("/tasks")
    public ModelAndView tasksSearchPage(
            @RequestParam(name = "task_name", required = false) String taskName,
            @RequestParam(name = "author_name", required = false) String authorName,
            @RequestParam(name = "order", required = false) String order,
            @RequestParam(name = "order_by", required = false) String orderBy,
            @RequestParam(name = "languages", required = false) String languages,
            @RequestParam(name = "page", required = false) Integer page,
            HttpServletRequest request
    ){
        ModelAndView modelAndView = new ModelAndView("tasks-search");
        SearchAttributes searchAttributes = SearchAttributes.builder()
                .taskName(taskName)
                .authorName(authorName)
                .order(order)
                .orderBy(orderBy)
                .languages(languages)
                .page(page)
                .build();
        Page<TaskInfo> searchResult = taskInfoService.search(searchAttributes);
        addPageAttributes(modelAndView, searchAttributes);
        addSearchResults(modelAndView, searchResult);
        addQueryStringAttribute(modelAndView, request);
        addAuthenticationAttributes(modelAndView);
        System.out.println(modelAndView.getModel());
        return modelAndView;
    }

    private void addPageAttributes(ModelAndView modelAndView, SearchAttributes searchAttributes) {
        modelAndView.addObject("pageNumber", searchAttributes.getPage());
        modelAndView.addObject("tasksUrl", tasksServiceUrl);
    }

    private void addSearchResults(ModelAndView modelAndView, Page<TaskInfo> searchResult) {
        modelAndView.addObject("hasNextPage", searchResult.hasNext());
        modelAndView.addObject("hasPreviousPage", searchResult.hasPrevious());
        modelAndView.addObject("tasks", searchResult.toList());
    }

    private void addQueryStringAttribute(ModelAndView modelAndView, HttpServletRequest request){
        String queryString = "";
        if (request.getQueryString() != null){
            queryString = request.getQueryString().replaceAll("&page=\\d+", "") + "&page=";

        }
        modelAndView.addObject("queryString", queryString);
    }

    @GetMapping("/plugins")
    public ModelAndView pluginsPage(){
        ModelAndView modelAndView = new ModelAndView("plugins");
        addAuthenticationAttributes(modelAndView);
        return modelAndView;
    }

}
