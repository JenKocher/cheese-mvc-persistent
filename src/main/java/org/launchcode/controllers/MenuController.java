package org.launchcode.controllers;

import org.launchcode.forms.AddMenuItemForm;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by Jen on 6/6/2017.
 */
@Controller
@RequestMapping(value="menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;
    private AddMenuItemForm form;
    private Errors errors;
    private Model model;

    //write a handler method, index
    //purpose: retrieve all menus, display them in a list
    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("menus",menuDao.findAll());
        model.addAttribute("title", "Display All Menus");
        return "menu/index";
    }

    //add a new, empty menu
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add a Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    //process the adding of a new, empty menu
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute  @Valid Menu newMenu,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Menu");
            return "menu/add";
        }
        menuDao.save(newMenu);
        //The following line comes from the assignment.
        //Should this be "redirect:/menu/view/" + newMenu.getId(); instead?
        return "redirect:view/" + newMenu.getId();
    }

    //accepts GET requests at URLs like view/5, where 5 can be any menu id
    @RequestMapping(value="view/{id}", method=RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int id){
        //retrieve the Menu object with the given id using menuDao
        Menu menu = menuDao.findOne(id);

        //pass the given menu into the view
        model.addAttribute("title", "View a Menu");
        model.addAttribute("menu", menu);
        return "menu/view";
    }

    //create a method named addItem
    //that responds to GET requests like add-item/5, where 5 can be any menu id
    @RequestMapping(value="add-item/{id}", method=RequestMethod.GET)
    public String addItem(Model model, @PathVariable int id){

        //retrieve the Menu with the given Id using menuDao
        Menu menu = menuDao.findOne(id);

        //Create an instance of AddMenuItemForm, with the given
        //Menu object and a list of all the Cheese items in the database
        AddMenuItemForm form = new AddMenuItemForm(menu, cheeseDao.findAll());

        //pass the form object into the view with the name "form"
        model.addAttribute("form", form);

        //along with a title that reads "Add item to menu: MENU NAME"
        //(using the actual menu name)
        model.addAttribute("title", "Add item to menu: " + menu.getName());

        //This handler should render the form "add-item.html"
        return "menu/add-item";
    }

    //create another handler named addItem that responds to POST requests at /menu/add-item
    @RequestMapping(value="add-item", method=RequestMethod.POST)
    public String addItem(@ModelAttribute @Valid AddMenuItemForm form, Errors errors, Model model){
        //Check for errors, rendering the "menu/add-item" template again if there are any.
        if (errors.hasErrors()){
            model.addAttribute("title", "Add item to menu: " + form.getMenu().getName());
            return "menu/add-item";
        }

        Menu theMenu = menuDao.findOne(form.getMenuId());
        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        theMenu.addItem(theCheese);
        menuDao.save(theMenu);

        //Should this be "redirect:/menu/view/" + theMenu.getId(); instead?
        return "redirect:view/" + theMenu.getId();
    }


}
