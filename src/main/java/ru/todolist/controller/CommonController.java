package ru.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.todolist.entity.dto.RecordsContainerDTO;
import ru.todolist.service.RecordService;

@Controller
public class CommonController {

    private final RecordService recordService;

    @Autowired
    public CommonController(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping("/")
    public String redirectToHomePage(){
       return "redirect:/home";
    }
    @RequestMapping("/home")
    public String getMainPage(Model model, @RequestParam(name ="filter",required = false) String filterMode){
        RecordsContainerDTO container = recordService.findAllRecords(filterMode);
        model.addAttribute("records",container.getRecords());
        model.addAttribute("numDoneRecords",container.getNumberOfDoneRecords());
        model.addAttribute("numActiveRecords",container.getNumberOfActiveRecords());
        return "main-page";
    }

    @RequestMapping(value = "/add-record", method = RequestMethod.POST)
    public String addRecord(@RequestParam String title){
        recordService.saveRecord(title);
        return "redirect:/home";
    }
    @RequestMapping(value = "/update-record",method= RequestMethod.POST)
    public String updateRecord(@RequestParam int id,
                               @RequestParam(name = "filter", required = false) String filterMode){
        recordService.updateRecordStatus(id);
        return "redirect:/home"+ (filterMode!=null && !filterMode.isBlank()?"?filter=" + filterMode:"");
    }
    @RequestMapping(value = "/delete-record",method= RequestMethod.POST)
    public String deleteRecord(QueryParameters queryParameters){
        recordService.deleteRecord(queryParameters.getId());
        return "redirect:/home" + (queryParameters.getFilter()!=null && !queryParameters.getFilter().isBlank()?"?filter="
                + queryParameters.getFilter():"");
    }
}

class QueryParameters{
    private int id;
    private String filter;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}