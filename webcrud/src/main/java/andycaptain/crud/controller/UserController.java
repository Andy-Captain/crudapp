package andycaptain.crud.controller;

import andycaptain.crud.model.User;
import andycaptain.crud.model.UserQuery;
import andycaptain.crud.service.UserQueryService;
import andycaptain.crud.service.UserService;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by AndyCaptain on 19.08.2016.
 */
@Controller
public class UserController {
    private UserService userService;
    private UserQueryService userQueryService;
    private final int NUM_LINES_PERPAGE = 5;
    private int numLines = 0;
    private int numPages = 0;
    private int currPage = 1;
    private String errorMessage = "";
    private int erorrMessageNeedShow=0;


    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "userQueryService")
    public void setUserService(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }


    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    public String listUsers(@RequestParam(value = "page", required = false) Long page, Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("userquery", new UserQuery());

        if (page == null || numLines == 0) {
            numLines = this.userService.countUsers();
            numPages = numLines / NUM_LINES_PERPAGE + (numLines % NUM_LINES_PERPAGE == 0 ? 0 : 1);

        }

        currPage = Math.abs(page == null ? 1 : page.intValue());
        if (currPage > numPages)
            currPage = numPages;

        List<User> ul = this.userService.listUsers(NUM_LINES_PERPAGE * (currPage - 1), NUM_LINES_PERPAGE);

        model.addAttribute("startpage", 1);
        model.addAttribute("currpage", currPage);
        model.addAttribute("endpage", numPages);
        model.addAttribute("listUsers", ul);
        if (erorrMessageNeedShow == 1){

            erorrMessageNeedShow++;
        } else if (erorrMessageNeedShow >1){
            erorrMessageNeedShow=0;
            errorMessage="";
        }

        model.addAttribute("errorMessage", errorMessage);

        return "users";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String listQueryUsers(@ModelAttribute("userquery") UserQuery query, Model model) {
        String pattern = query.getSearchString();
        userQueryService.setQueryPattern(query);
        String resultQuery = userQueryService.getQuery();

        int numSearchLines = this.userService.countUsers(resultQuery);
        int numSeachPages = numLines / NUM_LINES_PERPAGE + (numLines % NUM_LINES_PERPAGE == 0 ? 0 : 1);


        List<User> ul = this.userService.listUsers(resultQuery, 0, numSearchLines);

        model.addAttribute("numrecords", numSearchLines);
        model.addAttribute("listUsers", ul);
        model.addAttribute("searchstring", pattern);
        model.addAttribute("currentPage",currPage);

        return "searchdata";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (!userService.isUserExist(user.getName())) {
            this.userService.addUser(user);
            errorMessage = "";
        } else {
            errorMessage ="Пользователь с таким именем ___"+user.getName()+"___ уже заведен !";
            erorrMessageNeedShow=1;
        }
        return "redirect:/users";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String userEdit(@ModelAttribute("user") User user) {
        this.userService.updateUser(user);
        return "redirect:/users?page=" + currPage;
    }


    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);
        numLines = this.userService.countUsers();
        numPages = numLines / NUM_LINES_PERPAGE + (numLines % NUM_LINES_PERPAGE == 0 ? 0 : 1);
        if (currPage > numPages)
            currPage = numPages;

        return "redirect:/users?page=" + currPage;
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("currentPage",currPage);

        return "userdata";
    }


}

